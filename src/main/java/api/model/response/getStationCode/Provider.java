package api.model.response.getStationCode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Provider {
	
	private String code;
    private String provider;
    private String[] suppliers;
    private Boolean metaStation;
	
	public Provider() {}
	
	@JsonCreator
	public Provider(@JsonProperty(value="code", required=true) String code,
			@JsonProperty(value="provider", required=true) String provider,
			@JsonProperty(value="suppliers", required=true) String[] suppliers,
			@JsonProperty(value="metaStation", required=true) Boolean metaStation) {
		this.code = code;
		this.provider = provider;
		this.suppliers = suppliers;
		this.metaStation = metaStation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String[] suppliers) {
		this.suppliers = suppliers;
	}

	public Boolean getMetaStation() {
		return metaStation;
	}

	public void setMetaStation(Boolean metaStation) {
		this.metaStation = metaStation;
	}
	
}