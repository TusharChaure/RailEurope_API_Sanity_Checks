package requestClasses.login;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthParameters {
	
	public String USERNAME;
	public String PASSWORD;
		
	public AuthParameters(String USERNAME, String PASSWORD) {
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}
	
}
