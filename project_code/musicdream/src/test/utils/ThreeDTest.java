package test.utils;
import javax.vecmath.Vector3f;
/**
 * Testing class for utis.ThreeD.
 * @author Amir
 */
public class ThreeDTest {
	private static Vector3f x=utils.ThreeD.x,y=utils.ThreeD.y,z=utils.ThreeD.z;
	/**
	 * Calling for 7 differend test methods, each one tests one of the functions in ThreeD.
	 * @param args command line arguments for main class - ignored here.
	 */
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
	}
	private static void test1() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.vectorRotation(new Vector3f(1,2,3),new Vector3f(3,2,1)).transform(v);
		if(v.x==3&&v.y==2&&v.z==1) System.out.println("Success 1");
		else System.out.println("Fail 1 "+v.x+" "+v.y+" "+v.z);
	}
	private static void test2() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.toX(v).transform(v);
		if(v.x!=0&&Math.abs(v.y)<0.0000001f&&Math.abs(v.z)<0.0000001f) System.out.println("Success 2");
		else System.out.println("Fail 2 "+v.x+" "+v.y+" "+v.z);
	}
	private static void test3() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.toY(v).transform(v);
		if(v.y!=0&&Math.abs(v.x)<0.0000001f&&Math.abs(v.z)<0.0000001f) System.out.println("Success 3");
		else System.out.println("Fail 3 "+v.x+" "+v.y+" "+v.z);
	}
	private static void test4() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.toZ(v).transform(v);
		if(v.z!=0&&Math.abs(v.x)<0.0000001f&&Math.abs(v.y)<0.0000001f) System.out.println("Success 4");
		else System.out.println("Fail 4 "+v.x+" "+v.y+" "+v.z);
	}
	private static void test5() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.vectorRotation(x,v).transform(x);
		if(Math.abs(x.y-2*x.x)<0.0000001f&&Math.abs(x.z-3*x.x)<0.0000001f) System.out.println("Success 5");
		else System.out.println("Fail 5 "+x.x+" "+x.y+" "+x.z);
	}
	private static void test6() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.bringY(v).transform(y);
		if(Math.abs(y.y-2*y.x)<0.0000001f&&Math.abs(y.z-3*y.x)<0.0000001f) System.out.println("Success 6");
		else System.out.println("Fail 6 "+y.x+" "+y.y+" "+y.z);
	}
	private static void test7() {
		Vector3f v=new Vector3f(1,2,3);
		utils.ThreeD.bringZ(v).transform(z);
		if(Math.abs(z.y-2*z.x)<0.0000001f&&Math.abs(z.z-3*z.x)<0.0000001f) System.out.println("Success 7");
		else System.out.println("Fail 7 "+z.x+" "+z.y+" "+z.z);
	}
}