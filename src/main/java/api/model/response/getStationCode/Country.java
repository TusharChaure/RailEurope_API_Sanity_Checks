package api.model.response.getStationCode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
	
	private String code;
    private String label;
    private String localLabel;
	
	public Country() {}
	
	@JsonCreator
	public Country(@JsonProperty(value="code", required=true) String code,
			@JsonProperty(value="label", required=true) String label,
			@JsonProperty(value="localLabel", required=true) String localLabel) {
		this.code = code;
		this.label = label;
		this.localLabel = localLabel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLocalLabel() {
		return localLabel;
	}

	public void setLocalLabel(String localLabel) {
		this.localLabel = localLabel;
	}
	
}