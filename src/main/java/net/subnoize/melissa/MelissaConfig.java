package net.subnoize.melissa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import net.subnoize.melissa.service.AsyncBatchWorker;
import net.subnoize.melissa.service.PersonatorService;

/**
 * 
 * @author youca
 *
 */
@AutoConfiguration
@EnableScheduling
@EnableAsync
public class MelissaConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	public PersonatorService personatorService(WebClient.Builder webClientBuilder,
			@Value("${melissabatch.service.base}") String base) {
		log.debug("PersonatorService initialized");
		return new PersonatorService(webClientBuilder, base);
	}

	@Bean
	public AsyncBatchWorker batchingWorker() {
		log.debug("AsyncBatchWorker initialized");
		return new AsyncBatchWorker();
	}

	@Bean
	public MelissaBatching melissaDataBatchingService() {
		log.debug("MelissaBatching initialized");
		return new MelissaBatching();
	}

}
