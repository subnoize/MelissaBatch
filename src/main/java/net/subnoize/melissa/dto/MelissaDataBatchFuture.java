package net.subnoize.melissa.dto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * 
 * @author youca
 *
 */
public class MelissaDataBatchFuture extends CompletableFuture<MelissaAddress> {

	private AddressRequest request;

	public MelissaDataBatchFuture(AddressRequest request) {
		super();
		if(request.getRecordID() == null) {
			request.setRecordID(UUID.randomUUID().toString());
		}
		this.request = request;
	}

	public AddressRequest getRequest() {
		return request;
	}

	public void setRequest(AddressRequest request) {
		this.request = request;
	}

}
