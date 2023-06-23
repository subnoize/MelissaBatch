package net.subnoize.melissa.dto;

/**
 * 
 * @author John Bryant
 *
 */
public enum MelissaAddressStatusCode {
	
	// // @formatter:off
 
	/**
	 * AddressRequest Status
	 */
	AS01("Valid AddressRequest","The address is valid and deliverable according to official postal agencies."),
	AS02("Street Only Match","The street address was verified but the suite/apartment number is missing or invalid."),
	AS03("Non USPS AddressRequest Match","US Only. This US address is not serviced by the USPS but does exist and may receive mail through third party carriers like UPS."),
	AS09("Foreign AddressRequest","The address is in a non-supported country."),
	AS10("CMRA AddressRequest","US Only. The address is a Commercial Mail Receiving Agency (CMRA) like a Mailboxes Etc. These addresses include a Private Mail Box (PMB or #) number."),
	AS11("PBSA AddressRequest","A Post Office Box address with a street style address instead of the normal PO Box # address format."),
	AS12("Moved to New AddressRequest","The record moved to a new address."),
	AS13("AddressRequest Updated By LACS","US Only. The address has been converted by LACSLink® from a rural-style address to a city-style address."),
	AS14("Suite Appended","US Only. A suite was appended by SuiteLink™ using the address and company name."),
	AS15("Apartment Appended","An apartment number was appended by AddressPlus using the address and last name."),
	AS16("Vacant AddressRequest","US Only. The address has been unoccupied for more than 90 days."),
	AS17("No USPS Mail Delivery","US Only. The address is classified as not receiving mail by the USPS. This may be deliverable by third party delivery companies."),
	AS20("Deliverable only by USPS","US Only. This address can only receive mail delivered through the USPS (i.e. PO Box or a military address)."),
	AS23("Extraneous Information","Extraneous information not used in verifying the address was found. This includes unnecessary sub premises and other unrecognized data. Additional information will either be parsed into the Suite or Parsed Garbage/AddressExtras output."),
	AS24("USPS Door Not Accessible","AddressRequest identified by the USPS where carriers cannot physically access a building or door for mail delivery."),
	
	/**
	 * AddressRequest Change
	 */
	AC01("Postal Code Change","The postal code was changed or added."),
	AC02("Administrative Area Change","The administrative area (state, province) was added or changed."),
	AC03("Locality Change","The locality (city, municipality) name was added or changed."),
	AC04("Alternate to Base Change","US Only. The address was found to be an alternate record and changed to the base (preferred) version."),
	AC05("Alias Name Change","US Only. An alias is a common abbreviation for a long street name, such as “MLK Blvd” for “Martin Luther King Blvd.” This change code indicates that the full street name (preferred) has been substituted for the alias."),
	AC06("Address1/Address2 Swap","Address1 was swapped with Address2 because Address1 could not be verified and Address2 could be verified."),
	AC07("Address1 & Company Swapped","Address1 was swapped with Company because only Company had a valid address."),
	AC08("Plus4 Change","US Only. A non-empty plus4 was changed."),
	AC09("Dependent Locality Change","The dependent locality (urbanization) was changed."),
	AC10("Thoroughfare Name Change","The thoroughfare (street) name was added or changed due to a spelling correction."),
	AC11("Thoroughfare Type Change","The thoroughfare (street) leading or trailing type was added or changed, such as from \"St\" to \"Rd.\""),
	AC12("Thoroughfare Directional Change","The thoroughfare (street) pre-directional or post-directional was added or changed, such as from \"N\" to \"NW.\""),
	AC13("Sub Premise Type Change","The sub premise (suite) type was added or changed, such as from \"STE\" to \"APT.\""),
	AC14("Sub Premise Number Change","The sub premise (suite) unit number was added or changed."),
	AC20("House Number Change","The house number was changed."),

	/**
	 * AddressRequest Error
	 */
	AE01("Postal Code Error/General Error","The address could not be verified at least up to the postal code level."),
	AE02("Unknown Street","Could not match the input street to a unique street name. Either no matches or too many matches found."),
	AE03("Component Mismatch Error","The combination of directionals (N, E, SW, etc) and the suffix (AVE, ST, BLVD) is not correct and produced multiple possible matches."),
	AE04("Non-Deliverable AddressRequest","US Only. A physical plot exists but is not a deliverable addresses. One example might be a railroad track or river running alongside this street, as they would prevent construction of homes in that location."),
	AE05("Multiple Match","The address was matched to multiple records. There is not enough information available in the address to break the tie between multiple records."),
	AE06("Early Warning System","US Only. This address currently cannot be verified but was identified by the Early Warning System (EWS) as belonging to a upcoming area and will likely be included in a future update."),
	AE07("Missing Minimum AddressRequest","Minimum requirements for the address to be verified is not met. A country input is required for global products. For US/CA, a postal code or city/state are required. Requirements for other countries may be different."),
	AE08("Sub Premise Number Invalid","An address element after the house number, in most cases the sub-premise, was not valid."),
	AE09("Sub Premise Number Missing","An address element after the house number, in most cases the sub-premise, was missing."),
	AE10("Premise Number Invalid","The premise (house or building) number for the address is not valid."),
	AE11("Premise Number Missing","The premise (house or building) number for the address is missing."),
	AE12("Box Number Invalid","The PO (Post Office Box), RR (Rural Route), or HC (Highway Contract) Box number is invalid."),
	AE13("Box Number Missing","The PO (Post Office Box), RR (Rural Route), or HC (Highway Contract) Box number is missing."),
	AE14("PMB Number Missing","US Only. The address is a Commercial Mail Receiving Agency (CMRA) and the Private Mail Box (PMB or #) number is missing."),
	AE17("Sub Premise Not Required (Deprecated - See AS23)","A sub premise (suite) number was entered but the address does not have secondaries. (Deprecated - See AS23)"),
	AE21("MAK Not Found","The input MAK was not found. This can be caused by an improperly formatted MAK (a proper MAK is 10 numerical digits long) or by requesting a MAK number that has not yet been assigned to a location."),
	
	/**
	 * Geocode Status
	 */
	GS01("Geocoded to Street Level","The record was coded to the street level (Zip+4 for US, full postal code for CA)."),
	GS02("Geocoded to the Neighborhood Level","The record was geocoded down to neighborhood level (Zip+2 for US)."),
	GS03("Geocoded to Community Level","The record was coded to the community level (ZIP centroid for US, 3-digit postal code for CA)."),
	GS04("Geocoded to State Level","The record was geocoded to the state (administrative area) level."),
	GS05("Geocoded to Rooftop Level","The record was geocoded down to the rooftop level, meaning the point is within the property boundaries, usually the center."),
	GS06("Geocoded to Interpolated Rooftop Level","The record was geocoded down to the rooftop level using interpolation (educated estimations using street coordinates). The point may be in or close to the property boundaries."),
	GS10("Wire Center Lat/Long","The latitude and longitude are based off of the wire center of the phone number."),
	
	/**
	 * Geocode Error
	 */
	GE01("Invalid Postal Code","The submitted postal code is not in a valid format. Not to be confused with the GE01 Transmission result code."),
	GE02("Postal Code Coordinates Not Found in Database","The submitted postal code coordinates were not found in the Geocode database. Not to be confused with the GE02 Transmission result code."),
	
	
	/**
	 * Name Status
	 */
	NS01("Name Parsed","Name parsing was successful."),
	NS02("Error Parsing","An error was detected. Please check for a name error code."),
	NS03("First Name Spelling Corrected","The spelling in the first name field was corrected."),
	NS04("First Name 2 Spelling Corrected","The spelling in the second first name field was corrected."),
	NS05("First Name 1 Found","FirstName1 was found in our census table of names. Very likely to be a real first name."),
	NS06("Last Name 1 Found","LastName1 was found in our census table of names. Very likely to be a real last name."),
	NS07("First Name 2 Found","FirstName2 was found in our census table of names. Very likely to be a real first name."),
	NS08("Last Name 2 Found","LastName2 was found in our census table of names. Very likely to be a real last name."),
	
	/**
	 * Name Error
	 */
	NE01("Unrecognized Format","Two names were detected but the FullName string was not in a recognized format."),
	NE02("Multiple First Names Detected","Multiple first names were detected and could not be accurately genderized."),
	NE03("Vulgarity Detected","A vulgarity was detected in the name."),
	NE04("Suspicious Word Detected","The name contained words found on the list of nuisance names, such as \"Mickey Mouse.\""),
	NE05("Company Name Detected","The name contained words normally found in a company name."),
	NE06("Non-Alphabetic Character Detected","The name contained a non-alphabetic character."),
	
	/**
	 * Append Results
	 */
	DA00("AddressRequest Appended","An address was changed or appended."),
	DA01("City/State Append from Phone","A city or state was appended from a phone number wire center."),
	DA10("Name Appended","A full name was changed or appended."),
	DA20("Company Appended","A company name was changed or appended."),
	DA30("Phone Appended","A phone number was changed or appended."),
	DA40("Email Appended","An email address was changed or appended."),
	
	/**
	 * Verify Results
	 */
	VR01("Name and AddressRequest Match","The name and address match."),
	VR02("Name and Phone Match","The name and phone match."),
	VR03("Name and Email Match","The name and email match."),
	VR04("AddressRequest and Phone Match","The address and phone match."),
	VR05("AddressRequest and Email Match","The address and email match."),
	VR06("Phone and Email Match","The phone and email match."),
	VR07("Company and AddressRequest Match","The company and address match."),
	VR08("Company and Phone Match","The company and phone match."),
	VR09("Company and Email Match","The company and email match."),
	VR10("Company and Name Match","The company and name match."),
	VR11("Name and SSN Match","Matched the name field to the Social Security Number."),
	
	/**
	 * Verify Status
	 */
	VS00("AddressRequest Not Found","An address was not found in the reference data."),
	VS01("Historical AddressRequest Match","The current address is outdated and a newer address match was found. Use the \"Move\" action to get the latest address."),
	VS02("Partial AddressRequest Match","A match was made to a partial address. This could be due to matching the street address but not to the suite."),
	VS12("Last Name Match","A match was made to the last name only."),
	VS13("First Name Match","A match was made to the first name only."),
	VS22("Partial Company Name Match","A match was made to a partial company name."),
	VS30("Phone Not Found","A phone number was not found in the reference data."),
	VS31("Historical Phone Match","The current phone number is outdated and a newer phone number match was found. Use the \"Append\" action to get the latest phone number."),
	VS40("Email Not Found","An email address was not found in the reference data."),
	VS41("Historical Email AddressRequest","The current email address is outdated and a newer email address match was found. Use the \"Append\" action to get the latest email address."),
	VS50("Last4 Only Match","The name only matched to the last 4 digits of the Social Security Number."),
	
	/**
	 * Personator Status
	 */
	SP01("No Action","A record was sent in, but the service did not have enough information to modify the record."),
	
	/**
	 * GE - General Transmission Error
	 * 
	 * First wto are dups :p
	 */
	xGE01("Empty Request Structure","The SOAP, JSON, or XML request structure is empty. Not to be confused with the GE01 GeoCode result code."),
	xGE02("Empty Request Record Structure","The SOAP, JSON, or XML request record structure is empty. Not to be confused with the GE02 GeoCode result code."),
	GE03("Records Per Request Exceeded","The counted records sent more than the number of records allowed per request."),
	GE04("Empty License Key","The License Key is empty."),
	GE05("Invalid License Key","The required license string is missing or invalid. Use the Credits License on the My Account page or call Tech Support 1-800-Melissa."),
	GE06("Disabled License Key","The License Key is disabled."),
	GE07("Invalid Request","The SOAP, JSON, or XML request is invalid."),
	GE08("Product/Level Not Enabled","The License Key is invalid for this product or level."),
	GE09("Customer Does Not Exist","The Customer ID is not in our system."),
	GE10("Customer License Disabled","The encrypted license is on the ban list."),
	GE11("Customer Disabled","The Customer ID is disabled."),
	GE12("IP Blacklisted","The IP AddressRequest is on the global ban list."),
	GE13("IP Not Whitelisted","The IP AddressRequest is not on the customer's whitelist."),
	GE14("Out of Credits","The account has ran out of credits. Add more credits to continue using the service. https://www.melissa.com/pricing/purchase"),
	GE20("Verify Not Activated","The Verify package was requested but is not active for the License Key."),
	GE21("Append Not Activated","The Append package was requested but is not active for the License Key."),
	GE22("Move Not Activated","The Move package was requested but is not active for the License Key."),
	GE23("No Valid Action Requested","No valid action was requested by the service. The request must include at least one of the following actions: Check, Verify, Append, or Move."),
	GE24("Demographics Not Activated","The Demographics package was requested but is not active for the License Key."),
	GE27("IP Columns Not Activated","IP Columns requested but not active for the customer ID."),
	GE28("SSN Verification Not Activated","SSN Verification requested but not active for the customer ID."),
	GE29("Not Available for Credit License","The requested fields were not available for a credit license. To have access to the demographics fields, please upgrade your license to a subscription.");
	
	// @formatter:on
	
	
	private String description;
	private String explaination;

	MelissaAddressStatusCode(String description, String explaination) {
		this.description = description;
		this.explaination = explaination;

	}

	public String getDescription() {
		return description;
	}

	public String getExplaination() {
		return explaination;
	}

}
