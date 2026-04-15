package requestClasses.searchJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Origin {
	
	public String type;
	public String code;
	
	public Origin(String type, String code) {
		this.type = type;
		this.code = code;
	}	
	
}
