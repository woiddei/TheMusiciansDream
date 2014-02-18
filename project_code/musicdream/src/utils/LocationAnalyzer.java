package utils;
import java.util.Arrays;
public class LocationAnalyzer {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getCoordinates()));
	}
	public static float[] getCoordinates() {
		try {
			return getCoordinatesOfLocation(getCountryName());
		}
		catch(Exception e) {
			return new float[] {35.86166f,104.195397f};
		}
	}
	public static String getCountryName() throws Exception {
			String source=WebUtils.getSourceCode("http://freegeoip.net/json/"),
				   fromCountryName=source.split("country_name\":\"")[1],
				   countryName=fromCountryName.split("\"")[0];
			return countryName;
	}
	public static float[] getCoordinatesOfLocation(String locationName) throws Exception {
		String source=WebUtils.getSourceCode("http://maps.googleapis.com/maps/api/geocode/json?address="+locationName+"&sensor=true"),
			   fromCoordsString=source.split("\"location\"")[1],
			   latString=fromCoordsString.split("\"lat\" : |,")[1],
			   lngString=fromCoordsString.split("\"lng\" : |}")[1].trim();
		return (new float[] {Float.parseFloat(latString),
							 Float.parseFloat(lngString)});
	}
}