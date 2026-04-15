package api.stepimplementation;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class CommonMethods{
	
	private static ResponseOptions<Response> response;
	public static RequestSpecification request;
	HelperClass helper = new HelperClass();

	public ResponseOptions<Response> executeWithPOSTMethod(String token, Object requestBody, String URL, String id)
	{
		request = helper.createReq(token);
		if(id != null)
			response = request.body(requestBody).post(URL, id);			
		else
			response = request.body(requestBody).post(URL);			
		return response;
	}

	public ResponseOptions<Response> executeWithPUTMethod(String token, Object requestBody, String URL, String firstId, String secondId)
	{
		request = helper.createReq(token);
		response = request.body(requestBody).put(URL, firstId, secondId);				
		return response;
	}

	public ResponseOptions<Response> executeWithGETMethod(String token, String URL, String params)
	{
		request = helper.createReq(token);
		response = request.get(URL, params);				
		return response;
	}
	
	public ResponseOptions<Response> executeWithDELETEMethod(String token, String URL, String id)
	{
		request = helper.createReq(token);
		response = request.delete(URL, id);				
		return response;
	}
		
}
