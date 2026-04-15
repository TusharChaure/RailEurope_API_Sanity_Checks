package requestClasses.searchJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchJourney {
	
	public Travelers[] travelers;
	public Legs[] legs;
	public Boolean multiProviderEnabled;
	
	public SearchJourney(Travelers[] travelers, Legs[] legs, Boolean multiProviderEnabled) {
		this.travelers = travelers;
		this.legs = legs;
		this.multiProviderEnabled = multiProviderEnabled;
	}
		
	
}
