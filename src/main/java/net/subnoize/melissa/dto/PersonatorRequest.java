package net.subnoize.melissa.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author jbryant2
 *
 */
public class PersonatorRequest {

	@JsonProperty("TransmissionReference")
	private String transmissionReference;
	
	@JsonProperty("Actions")
	private String actions;
	
	@JsonProperty("Columns")
	private String columns;
	
	@JsonProperty("CustomerID")
	private String customerID;
	
	@JsonProperty("Options")
	private String options;

	@JsonProperty("Records")
	private List<AddressRequest> records = new ArrayList<>();

	public String getTransmissionReference() {
		return transmissionReference;
	}

	public void setTransmissionReference(String transmissionReference) {
		this.transmissionReference = transmissionReference;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public List<AddressRequest> getRecords() {
		return records;
	}

	public void setRecords(List<AddressRequest> records) {
		this.records = records;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
