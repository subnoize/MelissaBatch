package net.subnoize.melissa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.subnoize.melissa.dto.MelissaDataBatchFuture;
import net.subnoize.melissa.dto.PersonatorException;
import net.subnoize.melissa.dto.PersonatorRequest;
import net.subnoize.melissa.dto.PersonatorResponse;

/**
 * 
 * @author youca
 *
 */
public class AsyncBatchWorker {
	
	private static final Logger log = LoggerFactory.getLogger(AsyncBatchWorker.class);

   
	@Autowired
	private PersonatorService pserv;

	/**
	 * 
	 * @param records
	 * @throws JsonProcessingException 
	 */
	@Async
	public void batchAsync(List<MelissaDataBatchFuture> records) {
		
		log.debug("Starting batch: {}",records.size());

		PersonatorRequest batch = new PersonatorRequest();
		Map<String, MelissaDataBatchFuture> map = new HashMap<>();

		records.forEach(r -> {
			String uuid = r.getRequest().getRecordID();
			map.put(uuid, r);
			batch.getRecords().add(r.getRequest());
		});

		PersonatorResponse res = pserv.batch(UUID.randomUUID().toString(), batch);
		
		try {
			log.debug(new ObjectMapper().writeValueAsString(res));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (res.getRecords() == null || res.getRecords().isEmpty()) {
			records.forEach(r -> {
				r.completeExceptionally(new PersonatorException("No results"));
			});
		} else {
			log.debug("Batch complete: {}",res.getTotalRecords());
			res.getRecords().forEach(r -> {
				log.debug("Record Request Complete: {}", r.getRecordId());
				map.get(r.getRecordId()).complete(r);
			});
		}
	}
}
