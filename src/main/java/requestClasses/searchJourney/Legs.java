package requestClasses.searchJourney;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Legs {
	
	public Origin origin;
	public Destination destination;
	public String departure;
	
	public Legs(Origin origin, Destination destination, String departure) {
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
	}
	
}
