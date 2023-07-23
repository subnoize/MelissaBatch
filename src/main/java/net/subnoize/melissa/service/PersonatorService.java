package net.subnoize.melissa.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import net.subnoize.melissa.dto.AddressRequest;
import net.subnoize.melissa.dto.PersonatorRequest;
import net.subnoize.melissa.dto.PersonatorResponse;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

/**
 * http://wiki.melissadata.com/index.php?title=Personator_Consumer
 * http://wiki.melissadata.com/index.php?title=Personator_Consumer%3AJSON
 * http://wiki.melissadata.com/index.php?title=Result_Code_Details#Personator_Consumer
 *
 * @author youca
 *
 */
public class PersonatorService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Turn on or off MelissaData features
	 */
	@Value("${melissabatch.options}")
	private String options;

	/**
	 * MelissaData Actions to perform on this response
	 */
	@Value("${melissabatch.actions}")
	private String actions;

	/**
	 * Enables MelissaData groups and individual columns
	 */
	@Value("${melissabatch.columns}")
	private String columns;

	/**
	 * /v3/WEB/ContactVerify/doContactVerify
	 */
	@Value("${melissabatch.service.uri}")
	private String melissaUri;

	/**
	 * The ID issued by MelissaData
	 */
	@Value("${melissabatch.customerId}")
	private String customerID;

	/**
	 * Connection timeout in milliseconds
	 */
	@Value("${melissabatch.connection.timeout:20000}")
	private long connectionTimeout;

	/**
	 * Maximum number of times to retry the response
	 */
	@Value("${melissabatch.connection.retry:5}")
	private int retries;

	/**
	 * The time to back off between each retry attempt
	 */
	@Value("${melissabatch.connection.retry.time:1000}")
	private int retryBackoff;

	private WebClient webClient;

	/**
	 * Constructor takes a WebClient builder
	 * @param webClientBuilder
	 */
	public PersonatorService(WebClient.Builder webClientBuilder, String base) {
		log.info("Starting MelissaData Personator Service: {}", base);
		this.webClient = webClientBuilder.baseUrl(base)
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	/**
	 * Returns the status code from the HTTP Get and is used to provide health statistics
	 * @return
	 */
	public int melissaDataHealth() {
		final int[] statusCode = new int[1];
		webClient.get().uri(melissaUri).exchangeToMono(r -> {
			statusCode[0] = r.statusCode().value();
			return r.bodyToMono(String.class);
		}).block(Duration.ofMillis(connectionTimeout));
		return statusCode[0];
	}

	/**
	 * Batch can take up to 100 records
	 * @param mreq
	 * @return
	 */
	public PersonatorResponse batch(String melissaTransactionId, PersonatorRequest request) {

		if (request.getRecords().size() > 100 || request.getRecords().isEmpty()) {
			throw new IllegalArgumentException("Batch size must be between 1 and 100");
		}

		request.setCustomerID(customerID);
		request.setActions(actions);
		request.setOptions(options);
		request.setColumns(columns);
		request.setTransmissionReference(melissaTransactionId);

		return webClient.post()
			.uri(uriBuilder -> uriBuilder.path(melissaUri).build())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body(Mono.just(request), PersonatorRequest.class)
			.retrieve()
			.bodyToFlux(PersonatorResponse.class)
			.timeout(Duration.ofMillis(connectionTimeout))
			.retryWhen(Retry.fixedDelay(retries, Duration.ofMillis(retryBackoff)).filter(this::is5xxServerError))
			.blockFirst();
	}

	/**
	 * Filter which server side exceptions we should retry on
	 * @param throwable
	 * @return
	 */
	private boolean is5xxServerError(Throwable throwable) {
		return throwable instanceof WebClientResponseException
				&& ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
	}

	/**
	 * Using the single record, "real-time" GET call
	 * @param mreq the PersonatorRequest
	 * @return
	 */
	public PersonatorResponse single(String melissaTransactionId, PersonatorRequest request) {

		AddressRequest record = request.getRecords().get(0);

		MultiValueMap<String, String> req = new LinkedMultiValueMap<>();
		req.add("t", melissaTransactionId);
		req.add("id", customerID);
		req.add("act", actions);
		req.add("opt", options);
		req.add("cols", columns);
		req.add("first", record.getFirstName());
		req.add("last", record.getLastName());
		req.add("a1", record.getAddressLine1());
		req.add("postal", record.getPostalCode());

		log.debug("PersonatorRequest: {}", req);

		return webClient.get()
			.uri(uriBuilder -> uriBuilder.path(melissaUri).queryParams(req).build())
			.retrieve()
			.bodyToFlux(PersonatorResponse.class)
			.timeout(Duration.ofMillis(connectionTimeout))
			.retryWhen(Retry.fixedDelay(retries, Duration.ofMillis(retryBackoff)).filter(this::is5xxServerError))
			.blockFirst();
	}

}
