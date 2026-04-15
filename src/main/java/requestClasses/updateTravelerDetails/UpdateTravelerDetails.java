package requestClasses.updateTravelerDetails;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateTravelerDetails {
	
	public String id;
	public String type;
	public String leadTraveler;
	public Integer age;
	public String emailAddress;
	public String phoneNumber;
	public String phoneCountryCode;
	public String title;
	public String lastName;
	public String firstName;
	public String dateOfBirth;
	public String countryOfResidence;
	public TravelerDocument travelerDocument;
	
	public UpdateTravelerDetails(String id, String type, String leadTraveler, Integer age, String emailAddress,
			String phoneNumber, String phoneCountryCode, String title, String lastName, String firstName,
			String dateOfBirth, String countryOfResidence, TravelerDocument travelerDocument) {
		this.id = id;
		this.type = type;
		this.leadTraveler = leadTraveler;
		this.age = age;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.phoneCountryCode = phoneCountryCode;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.countryOfResidence = countryOfResidence;
		this.travelerDocument = travelerDocument;
	}
	
}
