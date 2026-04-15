package requestClasses.createBooking;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items {
	
	public List<OfferLocations> items;

	public Items(List<OfferLocations> items) {
		this.items = items;
	}
	
}
