package net.subnoize.melissa.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.subnoize.melissa.MelissaBatching;
import net.subnoize.melissa.dto.AddressRequest;
import net.subnoize.melissa.dto.MelissaAddress;
import net.subnoize.melissa.dto.MelissaAddressStatusCode;
import net.subnoize.melissa.dto.MelissaDataBatchFuture;

/**
 * @author youca
 * 
 *         In order to run this test you MUST have a real Melissa account and
 *         populate that into the configs for this test.
 */
@SpringBootTest
@TestPropertySource("/test.properties")
class MelissaBatchingIntegrationTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MelissaBatching addr;

	@Test
	@Ignore
	void testVerifyAddress() throws InterruptedException, ExecutionException, JsonProcessingException {

		AddressRequest a1 = new AddressRequest();
		a1.setAddressLine1("3172 Peachtree Rd NE");
		a1.setCity("Atlanta");
		a1.setState("GA");
		a1.setPostalCode("30305");

		MelissaDataBatchFuture addressFut = addr.verifyAddress(a1);

		MelissaAddress address = addressFut.get();

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
