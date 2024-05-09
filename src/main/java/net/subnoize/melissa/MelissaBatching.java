package net.subnoize.melissa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import net.subnoize.melissa.dto.AddressRequest;
import net.subnoize.melissa.dto.MelissaDataBatchFuture;
import net.subnoize.melissa.service.AsyncBatchWorker;

/**
 * @author youca
 *
 */
public class MelissaBatching {

	@Value("${melissadata.batch.size:100}")
	private int batchSize;

	@Value("${melissadata.batch.cache:200}")
	private int batchCacheSize;

	@Value("${melissadata.batch.cacheTimeOut:2500}")
	private long timeout;

	@Autowired
	private AsyncBatchWorker bworker;

	LinkedBlockingQueue<MelissaDataBatchFuture> batchCache;

	@PostConstruct
	public void init() {
		batchCache = new LinkedBlockingQueue<>(batchCacheSize);
	}

	/**
	 * The primary driver to send requests as a batch. Calls the BatchWorker which is
	 * annotated as @Async making this process just loading the internal queue.
	 */
	@Scheduled(fixedRateString = "${melissadata.batch.timer:1000}")
	public void batchDriver() {

		if (batchCache.isEmpty()) {
			return;
		}

		do {
			List<MelissaDataBatchFuture> batch = new ArrayList<>();
			batchCache.drainTo(batch, batchSize);
			bworker.batchAsync(batch);
		}
		while (!batchCache.isEmpty() && batchCache.size() >= batchSize);
	}

	/**
	 * Add request to the batch processor queue. If the queue full then this will block
	 * until the queue has compacity OR it times out with and InterruptException
	 * @param request
	 * @return
	 * @throws InterruptedException
	 */
	public MelissaDataBatchFuture verifyAddress(AddressRequest request) throws InterruptedException {
		MelissaDataBatchFuture fut = new MelissaDataBatchFuture(request);
		batchCache.offer(fut, timeout, TimeUnit.MILLISECONDS);
		return fut;
	}

}
