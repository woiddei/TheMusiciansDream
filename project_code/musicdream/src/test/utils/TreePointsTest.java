package test.utils;
import utils.LocationAnalyzer;
import utils.TreePoints;
public class TreePointsTest {
	public static void main(String[] args) {
		for(TreePoints tp:TreePoints.values())
			System.out.println(tp.getDistance(LocationAnalyzer.getCoordinates()[0],LocationAnalyzer.getCoordinates()[1]));
		System.out.println(TreePoints.getClosest().getLat()+" "+TreePoints.getClosest().getLng());
	}
}
