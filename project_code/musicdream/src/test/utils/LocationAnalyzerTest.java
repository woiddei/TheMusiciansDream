package test.utils;
import java.util.Arrays;
import utils.LocationAnalyzer;
/**
 * Testing class for util.LocationAnalyzer.
 * @author Amir
 */
public class LocationAnalyzerTest {
	public static void main(String[] args) { //Print the coordinates of the user's country.
		System.out.println(Arrays.toString(LocationAnalyzer.getCoordinates()));
	}
}
