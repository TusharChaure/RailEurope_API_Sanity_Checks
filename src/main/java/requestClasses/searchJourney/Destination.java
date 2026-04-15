package requestClasses.searchJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Destination {
	
	public String type;
	public String code;
	
	public Destination(String type, String code) {
		this.type = type;
		this.code = code;
	}	
	
}
