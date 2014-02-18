package test.tree;
import javax.media.j3d.GeometryArray;
import javax.vecmath.Point3f;
import tree.Stem;
/**
 * Testing class for tree.Stem.
 * Constructs a Stem object and checks its vertices and normals.
 * @author Amir
 */
public class TestStem {
	public static void main(String[] args) {
		GeometryArray ga=new Stem(new Point3f(0,0,0),new Point3f(1,0,0),1,2,10).getGeometryArray();
		for(int i=0;i<22;i++) {
			float[] d=new float[3];
			ga.getCoordinate(i,d);
			System.out.println(d[0]+" "+d[1]+" "+d[2]+" ");
			ga.getNormal(i,d);
			System.out.println(d[0]+" "+d[1]+" "+d[2]+" ");
		}
	}
}
