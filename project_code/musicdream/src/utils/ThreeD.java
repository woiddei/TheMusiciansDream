package utils;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
public class ThreeD {
	public static final Vector3f x=new Vector3f(1,0,0);
	public static final Vector3f y=new Vector3f(0,1,0);
	public static final Vector3f z=new Vector3f(0,0,1);
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
	public static Transform3D bringX(Vector3f v) {
		return vectorRotation(new Vector3f(1,0,0),v);
	}
	public static Transform3D bringY(Vector3f v) {
		return vectorRotation(new Vector3f(0,1,0),v);
	}
	public static Transform3D bringZ(Vector3f v) {
		return vectorRotation(new Vector3f(0,0,1),v);
	}
	public static Transform3D toX(Vector3f v) {
		return vectorRotation(v,new Vector3f(1,0,0));
	}
	public static Transform3D toY(Vector3f v) {
		return vectorRotation(v,new Vector3f(0,1,0));
	}
	public static Transform3D toZ(Vector3f v) {
		return vectorRotation(v,new Vector3f(0,0,1));
	}
	public static GeometryArray optimize(GeometryArray ga) {
		GeometryInfo gi=new GeometryInfo(ga);
		NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        Stripifier sp=new Stripifier();
        sp.stripify(gi);
        return gi.getGeometryArray();
	}
	public static Point3f[] setPoints(Vector3f[] directions,Point3f start) {
		Point3f[] ans=new Point3f[directions.length+1];
		ans[0]=start;
		for(int i=1;i<=directions.length;i++)
			ans[i]=new Point3f(ans[i-1].x+directions[i-1].x,ans[i-1].y+directions[i-1].y,ans[i-1].z+directions[i-1].z);
		return ans;
	}
}