package utils;
/**
 * Analyzing a user's location using JS API from google and freegeoip.
 * @author Amir
 */
public class LocationAnalyzer {
	public static float[] getCoordinates() {
		try { //Actual coordinates check.
			return getCoordinatesOfLocation(getCountryName());
		}
		catch(Exception e) { //Return the most likely guess, aka most populous country, China.
			return new float[] {35.86166f,104.195397f};
		}
	}
	/*
	 * Returns the user's country name usin freegeoip's API.
	 */
	private static String getCountryName() throws Exception {
			String source=WebUtils.getSourceCode("http://freegeoip.net/json/"),
				   fromCountryName=source.split("country_name\":\"")[1],
				   countryName=fromCountryName.split("\"")[0];
			return countryName;
	}
	/*
	 * Returns the coordinates of the location of a country using google's API.
	 */
	private static float[] getCoordinatesOfLocation(String locationName) throws Exception {
		String source=WebUtils.getSourceCode("http://maps.googleapis.com/maps/api/geocode/json?address="+locationName+"&sensor=true"),
			   fromCoordsString=source.split("\"location\"")[1],
			   latString=fromCoordsString.split("\"lat\" : |,")[1],
			   lngString=fromCoordsString.split("\"lng\" : |}")[1].trim();
		return (new float[] {Float.parseFloat(latString),
							 Float.parseFloat(lngString)});
	}
}