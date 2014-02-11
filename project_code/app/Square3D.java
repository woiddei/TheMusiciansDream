package app;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
public class Square3D extends Shape3D {
	private static Point3f A= new Point3f(-50f, 0.0f, 0.0f);
	private static Point3f B= new Point3f(-505f, 10.0f, 0.0f);
	private static Point3f C= new Point3f(50f, 10.0f, 0.0f);
	private static Point3f D= new Point3f(50f, 0.0f, 0.0f);
	public Square3D() {
		Point3f[] pts={C,D,A,B,C,B,A,D};
		int[] stripCounts={4,4};
		int[] contourCount={1,1};
		GeometryInfo gInf = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
		gInf.setCoordinates(pts);
		gInf.setStripCounts(stripCounts);
		gInf.setContourCounts(contourCount);
		NormalGenerator ng= new NormalGenerator();
		ng.setCreaseAngle ((float) Math.toRadians(30));
		ng.generateNormals(gInf);
	}
}