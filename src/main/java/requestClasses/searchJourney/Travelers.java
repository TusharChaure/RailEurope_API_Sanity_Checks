package requestClasses.searchJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Travelers {
	
	public String id;
	public Integer age;
	
	public Travelers(String id, Integer age) {
		this.id = id;
		this.age = age;
	}	
	
}
