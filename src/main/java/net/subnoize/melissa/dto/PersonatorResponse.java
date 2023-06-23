package net.subnoize.melissa.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author jbryant2
 *
 */
public class PersonatorResponse {

	@JsonProperty("TotalRecords")
	private String totalRecords;

	@JsonProperty("TransmissionReference")
	private String transmissionReference;

	@JsonProperty("TransmissionResults")
	private String transmissionResults;

	@JsonProperty("Version")
	private String version;

	@JsonProperty("Records")
	private List<MelissaAddress> records;

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getTransmissionReference() {
		return transmissionReference;
	}

	public void setTransmissionReference(String transmissionReference) {
		this.transmissionReference = transmissionReference;
	}

	public String getTransmissionResults() {
		return transmissionResults;
	}

	public void setTransmissionResults(String transmissionResults) {
		this.transmissionResults = transmissionResults;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<MelissaAddress> getRecords() {
		return records;
	}

	public void setRecords(List<MelissaAddress> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
