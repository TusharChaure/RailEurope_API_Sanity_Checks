package api.model.response.getStationCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	private Double lat;
    private Double lon;
	
	public Location() {}
	
	@JsonCreator
	public Location(@JsonProperty(value="lat", required=true) Double lat,
			@JsonProperty(value="lon", required=true) Double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}