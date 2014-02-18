package tree;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import utils.ThreeD;
public class Stem {
	GeometryArray ga;
	Shape3D stemShape;
	public Stem(Point3f p0,Point3f p1,float r0,float r1,int res) {
		float slice=(float)(2*Math.PI/res),thisSlice=0;
		Point3f p;
		ga=new TriangleStripArray(4*res+6,GeometryArray.COORDINATES,new int[] {2*res+3,2*res+3});
		Transform3D t=utils.ThreeD.bringZ(new Vector3f(p1.x-p0.x,p1.y-p0.y,p1.z-p0.z));
		for(int i=0;i<4*res+6;i+=2) {
			p=new Point3f(r0*(float)Math.cos(thisSlice),r0*(float)Math.sin(thisSlice),0);
			t.transform(p);
			p.add(p0);
			ga.setCoordinate(i,p);
			p=new Point3f(r1*(float)Math.cos(thisSlice),r1*(float)Math.sin(thisSlice),0);
			t.transform(p);
			p.add(p1);
			ga.setCoordinate(i+1,p);
			thisSlice+=slice;
		}
		ga=ThreeD.optimize(ga);
		stemShape=new Shape3D(ga);
	}
	public GeometryArray getGeometryArray() {
		return ga;
	}
	public Shape3D getShape() {
		return stemShape;
	}
}