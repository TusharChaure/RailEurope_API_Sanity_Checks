package requestClasses.createBooking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferLocations {
	
	public List<String> offerLocations;

	public OfferLocations(List<String> offerLocations) {
		this.offerLocations = offerLocations;
	}	
	
}
