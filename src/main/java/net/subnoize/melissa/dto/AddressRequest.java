package net.subnoize.melissa.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author jbryant2
 *
 */
public class AddressRequest {

	@JsonProperty("AddressLine1")
	private String addressLine1;
	
	@JsonProperty("AddressLine2")
	private String addressLine2;

	@JsonProperty("BirthDay")
	private String birthDay;

	@JsonProperty("BirthMonth")
	private String birthMonth;

	@JsonProperty("BirthYear")
	private String birthYear;

	@JsonProperty("City")
	private String city;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("EmailAddress")
	private String emailAddress;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("FreeForm")
	private String freeForm;

	@JsonProperty("FullName")
	private String fullName;

	@JsonProperty("IPAddress")
	private String ipAddress;

	@JsonProperty("LastLine")
	private String lastLine;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("MelissaAddressKey")
	private String melissaAddressKey;

	@JsonProperty("MelissaAddressKeyBase")
	private String melissaAddressKeyBase;

	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@JsonProperty("PostalCode")
	private String postalCode;

	@JsonProperty("RecordID")
	private String recordID;

	@JsonProperty("SocialSecurity")
	private String socialSecurity;

	@JsonProperty("State")
	private String state;
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFreeForm() {
		return freeForm;
	}

	public void setFreeForm(String freeForm) {
		this.freeForm = freeForm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String iPAddress) {
		this.ipAddress = iPAddress;
	}

	public String getLastLine() {
		return lastLine;
	}

	public void setLastLine(String lastLine) {
		this.lastLine = lastLine;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMelissaAddressKey() {
		return melissaAddressKey;
	}

	public void setMelissaAddressKey(String melissaAddressKey) {
		this.melissaAddressKey = melissaAddressKey;
	}

	public String getMelissaAddressKeyBase() {
		return melissaAddressKeyBase;
	}

	public void setMelissaAddressKeyBase(String melissaAddressKeyBase) {
		this.melissaAddressKeyBase = melissaAddressKeyBase;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
