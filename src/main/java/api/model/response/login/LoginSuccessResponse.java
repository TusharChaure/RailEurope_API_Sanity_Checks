package api.model.response.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginSuccessResponse{
	
	private AuthenticationResult AuthenticationResult;	
	private ChallengeParameters ChallengeParameters;
	
	public LoginSuccessResponse() {}
	
	@JsonCreator
	public LoginSuccessResponse(@JsonProperty(value="AuthenticationResult", required=true) AuthenticationResult AuthenticationResult, @JsonProperty(value="ChallengeParameters", required=true) ChallengeParameters ChallengeParameters) {
		this.AuthenticationResult = AuthenticationResult;
		this.ChallengeParameters = ChallengeParameters;
	}

	public AuthenticationResult getAuthenticationResult() {
		return AuthenticationResult;
	}

	public void setAuthenticationResult(AuthenticationResult authenticationResult) {
		this.AuthenticationResult = authenticationResult;
	}

	public ChallengeParameters getChallengeParameters() {
		return ChallengeParameters;
	}

	public void setChallengeParameters(ChallengeParameters challengeParameters) {
		this.ChallengeParameters = challengeParameters;
	}
	
}