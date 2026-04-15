package requestClasses.login;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
	
	public String AuthFlow;
	public AuthParameters AuthParameters;
	public String ClientId;
		
	public Login(String AuthFlow, AuthParameters AuthParameters, String ClientId) {
		this.AuthFlow = AuthFlow;
		this.AuthParameters = AuthParameters;
		this.ClientId = ClientId;
	}
	
}
