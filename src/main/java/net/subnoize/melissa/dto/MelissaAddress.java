package net.subnoize.melissa.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class MelissaAddress {

	@JsonProperty("MelissaAddressKey")
	private String melissaAddressKey;

	@JsonProperty("MelissaAddressKeyBase")
	private String melissaAddressKeyBase;

	@JsonProperty("AddressLine1")
	private String addressLine1;

	@JsonProperty("Suite")
	private String suite;

	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("PostalCode")
	private String postalCode;

	@JsonProperty("CountryCode")
	private String countryCode;

	@JsonProperty("Results")
	private String results;

	@JsonProperty("DeliveryPointCode")
	private String deliveryPointCode;

	@JsonProperty("AddressTypeCode")
	private String addressTypeCode;

	@JsonProperty("DeliveryIndicator")
	private String deliveryIndicator;

	@JsonProperty("Latitude")
	private String latitude;

	@JsonProperty("Longitude")
	private String longitude;

	private Map<String, String> details;

	@JsonProperty("RecordID")
	private String recordId;

	public MelissaAddress() {
		super();
	}

	public MelissaAddress(String melissaAddressKey, String melissaAddressKeyBase) {
		this();
		this.melissaAddressKeyBase = melissaAddressKeyBase;
		this.melissaAddressKey = melissaAddressKey;
	}

	public String getMelissaAddressKeyBase() {
		return melissaAddressKeyBase;
	}

	public void setMelissaAddressKeyBase(String melissaAddressKeyBase) {
		this.melissaAddressKeyBase = melissaAddressKeyBase;
	}

	@JsonAnySetter
	public void addDetails(String propertyKey, String value) {
		if (this.details == null) {
			this.details = new HashMap<>();
		}
		this.details.put(propertyKey, value);
	}

	@JsonAnyGetter
	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMelissaAddressKey() {
		return melissaAddressKey;
	}

	public void setMelissaAddressKey(String melissaAddressKey) {
		this.melissaAddressKey = melissaAddressKey;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDeliveryPointCode() {
		return deliveryPointCode;
	}

	public void setDeliveryPointCode(String deliveryPointCode) {
		this.deliveryPointCode = deliveryPointCode;
	}

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getDeliveryIndicator() {
		return deliveryIndicator;
	}

	public void setDeliveryIndicator(String deliveryIndicator) {
		this.deliveryIndicator = deliveryIndicator;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
