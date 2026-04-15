package requestClasses.searchPassJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Traveler {
	
	public Integer age;
	
	public Traveler(Integer age) {
		this.age = age;
	}	
	
}
