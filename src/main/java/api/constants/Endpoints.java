package api.constants;

public interface Endpoints {
	
	public static final String loginPath = "";
	public static final String getStationCode = "/places/autocomplete?{params}";
	public static final String searchPTPOffers = "/offers/point-to-point/searches";
	public static final String searchPasses = "/offers/passes/searches";
	public static final String createBooking = "/bookings";
	public static final String getBooking = "/bookings/{params}";
	public static final String updateTravelerInfo = "/bookings/{firstId}/items/{secondId}/travelers";
	public static final String prebook = "/bookings/{id}/checkout/prebook";
	
}