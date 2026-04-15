package requestClasses.updateTravelerDetails;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelerDocument {
	
	public String countryCode;
	public String documentNumber;
	public String expirationDate;
	public String type;
	
	public TravelerDocument(String countryCode, String documentNumber, String expirationDate, String type) {
		this.countryCode = countryCode;
		this.documentNumber = documentNumber;
		this.expirationDate = expirationDate;
		this.type = type;
	}
	
}
