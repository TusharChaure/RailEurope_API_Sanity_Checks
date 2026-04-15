package requestClasses.searchPassJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchPass {
	
	public Place place;
	public String validityStartDate;
	public Traveler[] travelers;
	
	public SearchPass(Place place, String validityStartDate, Traveler[] travelers) {
		this.place = place;
		this.validityStartDate = validityStartDate;
		this.travelers = travelers;
	}
	
	
		
	
}
