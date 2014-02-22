package utils;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
/**
 * Some basic 3d utilities, gathered to one library for easy usage.
 * @author Amir
 */
public class ThreeD {
	//x,y,z axes respectively.
	public static final Vector3f x=new Vector3f(1,0,0);
	public static final Vector3f y=new Vector3f(0,1,0);
	public static final Vector3f z=new Vector3f(0,0,1);
	/**
	 * returns a Transform3D rotation that brings v0 in the direction of v1.
	 * @param v0 the vector to move from.
	 * @param v1 the vector to move to.
	 * @return a Transform3D rotation that brings v0 in the direction of v1.
	 */
	public static Transform3D vectorRotation(Vector3f v0,Vector3f v1) {
		Transform3D t0=new Transform3D(),
				    t1=new Transform3D(),
					t2=new Transform3D();
		t0.rotZ(-Math.atan2(v0.y,v0.x));
		t1.rotY(Math.atan2(v0.z,Math.sqrt(v0.x*v0.x+v0.y*v0.y))-Math.atan2(v1.z,Math.sqrt(v1.x*v1.x+v1.y*v1.y)));
		t2.rotZ(Math.atan2(v1.y,v1.x));
		t1.mul(t0);
		t2.mul(t1);
		return t2;
	}
	/**
	 * @param v a vector to bring the x-axis to.
	 * @return a Transform3D rotation that brings the x-axis to v's direction.
	 */
	public static Transform3D bringX(Vector3f v) {
		return vectorRotation(x,v);
	}
	/**
	 * @param v a vector to bring the y-axis to.
	 * @return a Transform3D rotation that brings the y-axis to v's direction.
	 */
	public static Transform3D bringY(Vector3f v) {
		return vectorRotation(y,v);
	}
	/**
	 * @param v a vector to bring the z-axis to.
	 * @return a Transform3D rotation that brings the z-axis to v's direction.
	 */
	public static Transform3D bringZ(Vector3f v) {
		return vectorRotation(z,v);
	}
	/**
	 * @param v a vector to bring to the x-axis.
	 * @return a Transform3D rotation that brings v to the x-axis.
	 */
	public static Transform3D toX(Vector3f v) {
		return vectorRotation(v,x);
	}
	/**
	 * @param v a vector to bring to the y-axis.
	 * @return a Transform3D rotation that brings v to the y-axis.
	 */
	public static Transform3D toY(Vector3f v) {
		return vectorRotation(v,y);
	}
	/**
	 * @param v a vector to bring to the z-axis.
	 * @return a Transform3D rotation that brings v to the z-axis.
	 */
	public static Transform3D toZ(Vector3f v) {
		return vectorRotation(v,z);
	}
	/**
	 * Optimizes a GeometryArray object using a NormalGenerator and a Stripifier.
	 * @param ga GeometryArray object to optimize.
	 * @return a GeometryArray object representing the same Geometry, just optimized.
	 */
	public static GeometryArray optimize(GeometryArray ga) {
		GeometryInfo gi=new GeometryInfo(ga);
		NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        Stripifier sp=new Stripifier();
        sp.stripify(gi);
        return gi.getGeometryArray();
	}
	/**
	 * Returns an array of points from an array of vectors and a starting point,
	 * where each point is the some of all vectors up to this location and the starting point.
	 * @param directions an array of vectors with length n-1.
	 * @param start the starting point for this point array.
	 * @return a point array of length n.
	 */
	public static Point3f[] setPoints(Vector3f[] directions,Point3f start) {
		Point3f[] ans=new Point3f[directions.length+1];
		ans[0]=start;
		for(int i=1;i<=directions.length;i++)
			ans[i]=new Point3f(ans[i-1].x+directions[i-1].x,ans[i-1].y+directions[i-1].y,ans[i-1].z+directions[i-1].z);
		return ans;
	}
	public static float sphericanDistance(float lat0,float lng0,float lat1,float lng1,float rad) {
		lat0=radiansToDegrees(lat0);
		lat1=radiansToDegrees(lat1);
		lng0=radiansToDegrees(lng0);
		lng1=radiansToDegrees(lng1);
		return (float)(rad*Math.acos
						  (Math.sin(lat0)*Math.sin(lat1)
				          +Math.cos(lat0)*Math.cos(lat1)
				          *Math.cos(Math.abs(lng1-lng0))));
	}
	public static float radiansToDegrees(float degrees) {
		return degrees*(float)Math.PI/180;
	}
	public static float degreesToRadians(float radians) {
		return radians*180/(float)Math.PI;
	}
	public static Shape3D clone(Shape3D shape) {
		Shape3D copy=new Shape3D(shape.getGeometry());
		copy.setAppearance(shape.getAppearance());
		return copy;
	}
}