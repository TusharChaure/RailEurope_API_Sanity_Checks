package stepDefinitions;

import static org.junit.Assert.assertTrue;
import org.apache.http.HttpStatus;
import io.cucumber.java.en.Then;

public class CommonStepDefinations extends BaseClass{

	@Then("Verify {int}, {string}, {string}, {string}, {int} and schema from response body")
	public void verifyResponseMessageErrorMessageStatusCodeAndSchemaFromResponseBody(Integer test, String testCaseName, String responseMessage, String errorMessage, Integer statusCode) {
		testName = " - " + test + ": " + testCaseName + "<br>";
		scenario.log(helper.writeLogtoReport());
		verifyAssertEquality("Wrong status code", statusCode, res.getStatusCode());
		if(!responseMessage.matches("NA")) {
			if (res.getStatusCode() == HttpStatus.SC_OK || res.getStatusCode() == HttpStatus.SC_CREATED
					|| res.getStatusCode() == HttpStatus.SC_ACCEPTED) {
				verifyAssertEquality("Failed Message", commonMessagesMap.get(responseMessage), actualResponseMsg);
			}
			else {
				verifyAssertEquality("Incorrect response Message", commonMessagesMap.get(responseMessage), actualResponseMsg);
				verifyAssertEquality("Incorrect Error message", commonMessagesMap.get(errorMessage), actualErrorMsg);

			}
		}
		assertTrue("Response time exceeded", res.getTime() < apiResponseTime);
	}

}