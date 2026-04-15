package requestClasses.searchPassJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place {
	
	public String code;
	
	public Place(String code) {
		this.code = code;
	}	
	
}
