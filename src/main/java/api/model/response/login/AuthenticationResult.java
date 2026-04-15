package api.model.response.login;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResult {

	private String AccessToken;
	private Integer ExpiresIn;
	private String IdToken;
	private String RefreshToken;
	private String TokenType;
	
	public AuthenticationResult() {}
	
	@JsonCreator
	public AuthenticationResult(@JsonProperty(value="AccessToken", required=true) String AccessToken, @JsonProperty(value="ExpiresIn", required=true) Integer ExpiresIn, @JsonProperty(value="IdToken", required=true) String IdToken, @JsonProperty(value="RefreshToken", required=true) String RefreshToken, @JsonProperty(value="TokenType", required=true) String TokenType) {
		this.AccessToken = AccessToken;
		this.ExpiresIn = ExpiresIn;
		this.IdToken = IdToken;
		this.RefreshToken = RefreshToken;
		this.TokenType = TokenType;
	}

	public String getAccessToken() {
		return AccessToken;
	}

	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return ExpiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		ExpiresIn = expiresIn;
	}

	public String getIdToken() {
		return IdToken;
	}

	public void setIdToken(String idToken) {
		IdToken = idToken;
	}

	public String getRefreshToken() {
		return RefreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}

	public String getTokenType() {
		return TokenType;
	}

	public void setTokenType(String tokenType) {
		TokenType = tokenType;
	}

}