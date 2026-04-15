package api.stepimplementation;

import api.helpers.CustomLogFilter;
import api.utils.readConfigFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class HelperClass implements readConfigFile{

	protected static RequestSpecification request;
	protected static RequestSpecification get_request;
	protected static RequestSpecBuilder builder;
	private static Filter logFilter;

	public static RequestSpecification setRequestSpec(String token)
	{	
		builder=new RequestSpecBuilder();
		if(token.contentEquals("login")) {
			builder.setBaseUri(loginURL);
			builder.setConfig(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", ContentType.JSON)));
			builder.setContentType("application/x-amz-json-1.1");
			builder.addHeader("X-Amz-Target", xAmzTarget);
			token = null;
		}
		else {
			builder.setBaseUri(baseURL);
			builder.setContentType(ContentType.JSON);
		}
		builder.setAccept(ContentType.ANY);
		if(token!=null) {
			builder.addHeader("Authorization", "Bearer " + token);
			builder.addHeader("x-point-of-sale", pos);
		}
		logFilter=new CustomLogFilter();
		return builder.build();
	}

	public  RequestSpecification createReq(String token)
	{		
		return RestAssured.given(setRequestSpec(token)).filter(logFilter).relaxedHTTPSValidation().log().all();
	}

	
	public String writeLogtoReport()
	{
		CustomLogFilter customLogFilter = (CustomLogFilter)logFilter;	       
		return  "\n" + "API Request: " + customLogFilter.getRequestBuilder()
		+ "\n" + "API Response: " + customLogFilter.getResponseBuilder();
	}

}
