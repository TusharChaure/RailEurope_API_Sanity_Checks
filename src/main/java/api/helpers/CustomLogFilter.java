package api.helpers;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLogFilter implements Filter {

	private StringBuilder requestBuilderLogs;
	private StringBuilder responseBuilderLogs;
	String logs = "";

	@Override
	public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
		Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
		requestBuilderLogs = new StringBuilder();
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Request method: " + objectValidation(filterableRequestSpecification.getMethod()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Request URI: " + objectValidation(filterableRequestSpecification.getURI()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Form Params: " + objectValidation(filterableRequestSpecification.getFormParams()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Request Param: " + objectValidation(filterableRequestSpecification.getRequestParams()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Headers: " + objectValidation(filterableRequestSpecification.getHeaders()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Cookies: " + objectValidation(filterableRequestSpecification.getCookies()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Proxy: " + objectValidation(filterableRequestSpecification.getProxySpecification()));
		requestBuilderLogs.append("<br>");
		requestBuilderLogs.append("Body: " + objectValidation(filterableRequestSpecification.getBody()));
		requestBuilderLogs.append("<br>"+"<br>");
		requestBuilderLogs.append("************************************************************");
		requestBuilderLogs.append("<br>"+"<br>");
		responseBuilderLogs = new StringBuilder();
		responseBuilderLogs.append("<br>"+"<br>");
		responseBuilderLogs.append("Status Code: "+response.getStatusCode());
		responseBuilderLogs.append("<br>");
		responseBuilderLogs.append("Status Line: "+response.getStatusLine());
		responseBuilderLogs.append("<br>");
		responseBuilderLogs.append("Response Cookies: "+response.getDetailedCookies());
		responseBuilderLogs.append("<br>");
		responseBuilderLogs.append("Response Content Type: "+response.getContentType());
		responseBuilderLogs.append("<br>");
		responseBuilderLogs.append("Response Headers: "+response.getHeaders());
		responseBuilderLogs.append("<br>");
		responseBuilderLogs.append("Response Body: "+"<br>"+"<br>"+response.getBody().prettyPrint()+"<br>");
		return response;
	}

	public String getRequestBuilder() {
		try {
			logs =  requestBuilderLogs.toString();
		}
		catch (Exception E) {
			System.out.println(E.toString());
		}
		return logs;
	}
 
	public String getResponseBuilder() {
		try {
			logs = responseBuilderLogs.toString();
		}
		catch (Exception E) {
			System.out.println(E.toString());
		}
		return logs;
	}

	public String objectValidation(Object o) {
		if (o == null)
			return null;
		else
			return o.toString();
	}

}