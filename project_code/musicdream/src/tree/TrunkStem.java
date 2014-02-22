package tree;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.ThreeD;
public class TrunkStem implements Stem {
	GeometryArray ga; //GeometryArray of this stem.
	/**
	 * Construct a new Stem object, with info about its shape and geometry,
	 * from given parameters.
	 * @param p0 first point of the shape.
	 * @param p1 second point of the shape.
	 * @param r0 radius of the base around p0.
	 * @param r1 radius of the base around p1.
	 */
	public TrunkStem(Point3f p0,Point3f p1,float r0,float r1,int res,float lobeDepth,int lobes) {
		float slice=(float)(2*Math.PI/res),thisSlice=0; //advancement for each res. line.
		Point3f p;
		ga=new TriangleStripArray(4*res+6,GeometryArray.COORDINATES,new int[] {2*res+3,2*res+3}); //Some more j3d dark magic.
		Transform3D t=utils.ThreeD.bringZ(new Vector3f(p1.x-p0.x,p1.y-p0.y,p1.z-p0.z)); //Transform to bring the x,y plane perpendicular to the line between p0 and p1.
		float rEffective0,rEffective1;
		for(int i=0;i<4*res+6;i+=2) {
			rEffective0=r0*(1+lobeDepth*(float)Math.sin(lobes*thisSlice));
			rEffective1=r1*(1+lobeDepth*(float)Math.sin(lobes*thisSlice));
			p=new Point3f(rEffective0*(float)Math.cos(thisSlice),rEffective0*(float)Math.sin(thisSlice),0);
			t.transform(p); //Relevant point on p0's base.
			p.add(p0);
			ga.setCoordinate(i,p);
			p=new Point3f(rEffective1*(float)Math.cos(thisSlice),rEffective1*(float)Math.sin(thisSlice),0);
			t.transform(p); //Relevant point on p1's base.
			p.add(p1);
			ga.setCoordinate(i+1,p);
			thisSlice+=slice; //Go for the next slice.
		}
		ga=ThreeD.optimize(ga); //Creates normals and so on.
	}
	/**
	 * @return this stem's geometry array object.
	 */
	public GeometryArray getGeometryArray() {
		return ga;
	}
}
