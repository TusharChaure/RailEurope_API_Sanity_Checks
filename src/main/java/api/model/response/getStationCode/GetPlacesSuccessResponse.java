package api.model.response.getStationCode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPlacesSuccessResponse{
	
	private String id;
    private String type;
    private String code;
    private String uicCode;
    private String label;
    private String localLabel;
    private Country country;
    private Location location;
    private Provider[] providers;
    private String timezone;
    private Boolean ticketOffice;	
    private Boolean ticketMachine;	
	
	public GetPlacesSuccessResponse() {}
	
	@JsonCreator
	public GetPlacesSuccessResponse(@JsonProperty(value="id", required=true) String id, 
			@JsonProperty(value="type", required=true) String type,
			@JsonProperty(value="code", required=true) String code,
			@JsonProperty(value="uicCode", required=false) String uicCode,
			@JsonProperty(value="label", required=true) String label,
			@JsonProperty(value="localLabel", required=true) String localLabel,
			@JsonProperty(value="country", required=true) Country country,
			@JsonProperty(value="location", required=true) Location location,
			@JsonProperty(value="providers", required=true) Provider[] providers,
			@JsonProperty(value="timezone", required=true) String timezone,
			@JsonProperty(value="ticketOffice", required=false) Boolean ticketOffice,
			@JsonProperty(value="ticketMachine", required=false) Boolean ticketMachine) {
		this.id = id;
		this.type = type;
		this.code = code;
		this.uicCode = uicCode;
		this.label = label;
		this.localLabel = localLabel;
		this.country = country;
		this.location = location;
		this.providers = providers;
		this.timezone = timezone;
		this.ticketOffice = ticketOffice;
		this.ticketMachine = ticketMachine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUicCode() {
		return uicCode;
	}

	public void setUicCode(String uicCode) {
		this.uicCode = uicCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLocalLabel() {
		return localLabel;
	}

	public void setLocalLabel(String localLabel) {
		this.localLabel = localLabel;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Provider[] getProviders() {
		return providers;
	}

	public void setProviders(Provider[] providers) {
		this.providers = providers;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Boolean getTicketMachine() {
		return ticketMachine;
	}

	public void setTicketMachine(Boolean ticketMachine) {
		this.ticketMachine = ticketMachine;
	}

	public Boolean getTicketOffice() {
		return ticketOffice;
	}

	public void setTicketOffice(Boolean ticketOffice) {
		this.ticketOffice = ticketOffice;
	}
	
}