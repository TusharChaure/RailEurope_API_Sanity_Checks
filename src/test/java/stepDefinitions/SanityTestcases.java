package stepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import api.constants.Constants;
import api.constants.Endpoints;
import api.model.response.getStationCode.GetPlacesSuccessResponse;
import api.model.response.login.LoginSuccessResponse;
import api.utils.readConfigFile;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import requestClasses.createBooking.Items;
import requestClasses.createBooking.OfferLocations;
import requestClasses.login.AuthParameters;
import requestClasses.login.Login;
import requestClasses.searchJourney.Destination;
import requestClasses.searchJourney.Legs;
import requestClasses.searchJourney.Origin;
import requestClasses.searchJourney.SearchJourney;
import requestClasses.searchJourney.Travelers;
import requestClasses.searchPassJourney.Place;
import requestClasses.searchPassJourney.SearchPass;
import requestClasses.searchPassJourney.Traveler;
import requestClasses.updateTravelerDetails.TravelerDocument;
import requestClasses.updateTravelerDetails.UpdateTravelerDetails;

public class SanityTestcases extends BaseClass implements readConfigFile {
	
	@Before
	public void beforeStepImpl(Scenario scenario) {
		BaseClass.scenario = scenario;
	}

	@Given("User should have valid login credentials {int}")
	public void userShouldHaveValidLoginCredentials(Integer test) {
		try {
			switch(test) {
			case 1: 
				username = rootAdminUserName;
				password = rootAdminPassword;
				break;		}
			AuthParameters authParameters = new AuthParameters(username, password);
			object = new Login("USER_PASSWORD_AUTH", authParameters, ClientId);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Login with valid username and password")
	public void loginWithValidUsernameAndPassword() {
		try {
			res = commonMethods.executeWithPOSTMethod("login", object, Endpoints.loginPath, null);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Collect data from login response body {int}")
	public void collectDataFromLoginResponseBody(Integer test) {
		switch(test) {
		case 1: 
			adminToken = res.getBody().as(LoginSuccessResponse.class).getAuthenticationResult().getIdToken();
			break;
		}
	}
	
	@Given("Get origin station code {string}")
	public void getOriginStationCode(String origin) {
		try {
			res = commonMethods.executeWithGETMethod(adminToken, Endpoints.getStationCode, "boost=city&query=" + origin);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Collect origin station code from response")
	public void collectOriginStationCodeFromResponse() {
		try {
			originCityCode = res.getBody().as(GetPlacesSuccessResponse[].class)[0].getCode();
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Get departure station code {string}")
	public void getDepartureStationCode(String origin) {
		try {
			res = commonMethods.executeWithGETMethod(adminToken, Endpoints.getStationCode, "boost=city&query=" + origin);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Collect departure station code from response")
	public void collectDepartureStationCodeFromResponse() {
		try {
			destinationCityCode = res.getBody().as(GetPlacesSuccessResponse[].class)[0].getCode();
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Search PTP {string} journey")
	public void searchPTPCarrierJourney(String carrier) {
		try {
			Origin origin = new Origin("station", originCityCode);
			Destination destination = new Destination("station", destinationCityCode);
			Legs legs = new Legs(origin, destination, Constants.travelDate);
			Travelers travelers = new Travelers(Constants.travelerId, Constants.travelerAge);
			object = new SearchJourney(new Travelers[] { travelers }, new Legs[] { legs} , true);
			res = commonMethods.executeWithPOSTMethod(adminToken, object, Endpoints.searchPTPOffers, null);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Select {string} offer from returned journeys")
	public void selectCarrierOfferFromReturnedJourneys(String carrier) {
		try {
			ArrayList<Object> solutions = res.getBody().path("legs[0].solutions");
			ArrayList<Object> offers = res.getBody().path("offers");
			for (int i = 0; i < solutions.size(); i++) {
				System.out.println(res.getBody().path("legs[0].solutions["+i+"].segments[0].marketingCarrier").toString());
		        System.out.println(carrier);
				if( res.getBody().path("legs[0].solutions["+i+"].segments[0].marketingCarrier").toString().matches(carrier)) {
		            for(int j = 0; j < offers.size(); j++){
		                if(res.getBody().path("offers["+j+"].legSolution").toString().matches(res.getBody().path("legs[0].solutions["+i+"].id"))){
		                	selectedCarrierOffers.add(res.getBody().path("offers["+j+"].location").toString());
		                	break;
		                }
		            }
		            break;
		        }
		    }
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Prepare request body for {string} search pass operation")
	public void prepareRequestBodyForSearchPassOperation(String pass) {
		try {
			Place place = new Place(pass);
			Traveler traveler = new Traveler(Constants.travelerAge);
			object = new SearchPass(place, Constants.travelDate, new Traveler[] {traveler});
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Get results for requested pass")
	public void getResultsForRequestedPass() {
		try {
			res = commonMethods.executeWithPOSTMethod(adminToken, object, Endpoints.searchPasses, null);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Select {string} pass from search response")
	public void selectPassFromSearchResponse(String passCarrier) {
		try {
			ArrayList<Object> passOffers = res.getBody().path("offers");
			for(int i=0; i<passOffers.size(); i++) {
				if(res.getBody().path("offers[" + i + "].marketingCarrier").toString().matches(passCarrier)) {
					selectedCarrierOffers.add(res.getBody().path("offers[" + i + "].location").toString());
					break;
				}
			}
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Prepare request body selected journeys")
	public void prepareRequestBodySelectedJourneys() {
		try {
			OfferLocations offerLocations;
			List<OfferLocations> item = new ArrayList<>();
			for(int i=0; i<selectedCarrierOffers.size(); i++) {				
				offerLocations = new OfferLocations(Arrays.asList(selectedCarrierOffers.get(i)));
				item.add(offerLocations);
			}
			object = new Items(item);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Create booking for all carriers")
	public void createBookingAndCollectResponseBody() {
		try {
			res = commonMethods.executeWithPOSTMethod(adminToken, object, Endpoints.createBooking, null);
			testLogs = "\n" + "Booking reference: " + res.getBody().path("bookingReference");
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Collect require fields from response body")
	public void collectRequireFieldsFromResponseBody() {
		try {
			bookingId = res.getBody().path("id");
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Get created bookings details")
	public void getCreatedBookingsDetails() {
		try {
			res = commonMethods.executeWithGETMethod(adminToken, Endpoints.getBooking, bookingId);
			testLogs = "\n" + "Booking reference: " + res.getBody().path("bookingReference");
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@And("Verify all booking count 13 PTP and 3 Passes")
	public void verifyAllBookingCountPTPAndPasses() {
		try {
			bookingItemIdList = new HashMap<String, String>();
			createBookingItems = res.getBody().path("bookingItems");
			for(int i=0;i<createBookingItems.size();i++) {
				if(res.getBody().path("bookingItems["+i+"].type").toString().matches("point-to-point"))
					bookingItemIdList.put(res.getBody().path("bookingItems["+i+"].legs[0].segments[0].marketingCarrier").toString(), res.getBody().path("bookingItems["+i+"].id").toString());
				else if(res.getBody().path("bookingItems["+i+"].type").toString().matches("pass"))
					bookingItemIdList.put(res.getBody().path("bookingItems["+i+"].products[0].places[0]").toString(), res.getBody().path("bookingItems["+i+"].id").toString());
			}
			testLogs = "\n" + "Booking items and offerLocation ID's: " + bookingItemIdList.toString();
			verifyAssertEquality("All journey's are not booked. Total booking item count should be 16", 16, createBookingItems.size());
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Create update traveler request body with parameters {string}, {string}, {string}, {int}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	public void createRequestBodyWithMandatoryParameters(String travelerId, String travelerType, String leadTravelerType, Integer travelerAge, String travelerEmail, String travelerPhone, String countryCode, String travelerTitle, String travelerLastName, String travelerFirstName, String travelerDOB, String travelerCountry, String travelerDocumentNumber, String travelerDocumentExpirationDate, String travelerDocumentType) {
		try {
			TravelerDocument travelerDocuments = new TravelerDocument(travelerCountry, travelerDocumentNumber, travelerDocumentExpirationDate, travelerDocumentType);
			object = new UpdateTravelerDetails(travelerId, travelerType, leadTravelerType, travelerAge, travelerEmail, travelerPhone, countryCode, travelerTitle, travelerLastName, travelerFirstName, travelerDOB, travelerCountry, travelerDocuments);
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Update traveler details for each {string} journey")
	public void updateTravelerDetailsForEachCarrierJourney(String carrier) {
		try {
			res = commonMethods.executeWithPUTMethod(adminToken, Arrays.asList(object), Endpoints.updateTravelerInfo, bookingId, bookingItemIdList.getOrDefault(carrier, "Not-Found"));
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@Given("Prepare request body to perform prebook operation")
	public void prepareRequestBodyToPerformPrebookOperation() {
		try {
			object = bookingItemIdList.values().toArray();
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
	@When("Prebook created bookings")
	public void prebookCreatedBookings() {
		try {
			res = commonMethods.executeWithPOSTMethod(adminToken, object, Endpoints.prebook, bookingId);
			testLogs = "\n" + "Booking reference: " + res.getBody().path("bookingReference");
		} catch (Exception E) {
			errorLogs(E);
		}
	}
	
}
