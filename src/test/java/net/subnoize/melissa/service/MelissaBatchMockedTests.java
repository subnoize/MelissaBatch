package net.subnoize.melissa.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import net.subnoize.melissa.MelissaBatching;
import net.subnoize.melissa.dto.AddressRequest;
import net.subnoize.melissa.dto.MelissaAddress;
import net.subnoize.melissa.dto.MelissaAddressStatusCode;
import net.subnoize.melissa.dto.MelissaDataBatchFuture;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * @author youca
 * 
 *         This is a mocked server style test using a canned response from
 *         MelissaData
 */
@SpringBootTest
@TestPropertySource("/test.properties")
public class MelissaBatchMockedTests {

	public static MockWebServer mockWebServer;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry r) throws IOException {
		r.add("melissabatch.service.base", () -> "http://localhost:" + mockWebServer.getPort());
	}

	@BeforeAll
	static void setUp() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockWebServer.shutdown();
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MelissaBatching addr;

	@Value(value = "classpath:/response_4442926559.json")
	Resource mockAddress4442926559;

	@Test
	void batchTestHappyPath() throws InterruptedException, ExecutionException, IOException {

		String jsonResponse = IOUtils.toString(mockAddress4442926559.getInputStream(), "UTF-8");

		mockWebServer.enqueue(new MockResponse().setBody(jsonResponse).addHeader("Content-Type", "application/json"));

		AddressRequest testAddress = new AddressRequest();
		testAddress.setAddressLine1("3172 Peachtree Rd NE");
		testAddress.setCity("Atlanta");
		testAddress.setState("GA");
		testAddress.setPostalCode("30305");
		/**
		 * This is so we can match the canned data to the new request. This feature is
		 * provided so that you can give your own RecordID to trace processing in
		 * Melissa Personator but we use it here to make the MockServer's canned data
		 * match up with our request.
		 */
		testAddress.setRecordID("14c908f7-0d5d-4106-9e08-8db42c937ef9");

		MelissaDataBatchFuture addressFut = addr.verifyAddress(testAddress);

		MelissaAddress address = addressFut.get();

		RecordedRequest recordedRequest = mockWebServer.takeRequest();

		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/v3/WEB/ContactVerify/doContactVerify", recordedRequest.getPath());
		assertEquals("3172 Peachtree Rd NE", address.getAddressLine1());
		assertEquals("Atlanta", address.getCity());
		assertEquals("GA", address.getState());
		assertEquals("30305-1853", address.getPostalCode());

		String[] results = address.getResults().split(",");

		assertArrayEquals(new String[] { "AS01", "GS05" }, results);

		for (String code : results) {
			logger.debug("Result Code: {} = {}", code, MelissaAddressStatusCode.valueOf(code).getDescription());
		}

	}
}
