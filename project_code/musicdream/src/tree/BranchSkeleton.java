package tree;
import java.util.Random;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import utils.Rand;
import utils.ThreeD;
/**
 * A class for representation of the skeleton of a single branch:
 * locations and radii for each vertex.
 * @author Amir
 */
public class BranchSkeleton {
	private Vector3f[] directions; //Vectors for the stems.
	private Point3f[] points; //Coordinates of the stems' vertices.
	private float[] radii;
	private int length;
	private Random rand;
	private float lastRotate=0,offset=0,totalLength;
	/**
	 * Constructs a BranchSkeleton object by given parameters, as detailed here.
	 * @param nCurveRes number of curves in this branch.
	 * @param nCurve total angle of the curves (only in the first half if nCurveBack!=0), in radians.
	 * @param nCurveBack total angle of the curves in the second half, if it isn't 0, in radians.
	 * @param nCurveV order of magnitude for the randomized factor of each angle, in radians.
	 * @param rand a Random object for the random output.
	 * @param totalLength the total length of the branch.
	 * @param radius the radius on the beginning of the branch.
	 * @param startPoint the point where the first stem starts.
	 * @param startVector the direction of the first stem.
	 */
	public BranchSkeleton(int nCurveRes,float nCurve,float nCurveBack,float nCurveV,Random rand,float totalLength,float radius,Point3f startPoint,Vector3f startVector,float offset) {
		setStartPoints(nCurveRes,radius,startVector,rand,offset,totalLength); //Some initialization.
		if(nCurveBack==0) {
			for(int i=1;i<nCurveRes;i++)
				setVector(i,nCurve,nCurveRes,nCurveV);
		}
		else {
			for(int i=1;i<=nCurveRes/2;i++)
				setVector(i,nCurve,nCurveRes/2,nCurveV);
			for(int i=nCurveRes/2+1;i<nCurveRes;i++)
				setVector(i,nCurveBack,nCurveRes/2,nCurveV);
		} //All vector values currently point in the right direction but may have incorrect length.
		scaleDirections(totalLength,nCurveRes); //Scaling the vectors correctly.
		points=utils.ThreeD.setPoints(directions,startPoint); //Derives points from vectors and starting point.
	}
	/*
	 * Setting some initialization values:
	 * Number of stems in this branch, length of vector and radii array.
	 * Setting the first vector as the first vector given to us, normalized.
	 */
	private void setStartPoints(int nCurveRes,float radius,Vector3f startVector,Random rand,float offset,float totalLength) {
		length=nCurveRes;
		radii=new float[nCurveRes+1];
		for(int i=0;i<=nCurveRes;i++) radii[i]=radius*(nCurveRes-i)/nCurveRes;
		directions=new Vector3f[nCurveRes];
		startVector.normalize();
		directions[0]=new Vector3f(startVector);
		this.rand=rand;
		this.offset=offset;
		this.totalLength=totalLength;
	}
	/*
	 * Sets a vector given the last one and all angle parameters.
	 */
	private void setVector(int i,float deterAngle,int curves,float randAngle) {
		directions[i]=new Vector3f(0,1,0);
		Transform3D thisTransform=new Transform3D();
		ThreeD.bringY(directions[i-1]).transform(directions[i]);
		thisTransform.rotZ(-angleCalc(deterAngle,curves/2,randAngle));
		thisTransform.transform(directions[i]);
	}
	/*
	 * Randomly assign value for an angle, given the total curvature angle,
	 * the number of stems to form this curvature, and the magnitude of the
	 * random addition to the curvature.
	 */
	private double angleCalc(float angle,int n,float magniture) {
		return angle/n+Rand.randomInRange(-magniture/n,magniture/n,rand);
	}
	/*
	 * Scaling the vectoric directions equally according to the given total length.
	 */
	private void scaleDirections(float totalLength, int nCurveRes) {
		float distance=totalLength/nCurveRes;
		for(int i=0;i<nCurveRes;i++)
			directions[i].scale(distance);
	}
	/**
	 * @return an array of locations for vertices of the branch.
	 */
	public Point3f[] getPoints() {
		return this.points;
	}
	/**
	 * @return an array of radii for vertices of the branch.
	 */
	public float[] getRadii() {
		return this.radii;
	}
	/**
	 * @return the number of stems on this branch.
	 */
	public int getStemsLength() {
		return length;
	}
	public Point3f getPoint(int i) {
		return points[i];
	}
	private Point3f[] getPointAndDirOnBranch(float d) {
		float sum=0;
		int i=0;
		while(i<length&&sum+directions[i].length()<d) {
			sum+=directions[i].length();
			i++;
		}
		float scaleLast=d-sum;
		return new Point3f[] {new Point3f(points[i].x+scaleLast*directions[i].x,points[i].y+scaleLast*directions[i].y,points[i].z+scaleLast*directions[i].z),new Point3f(directions[i])};
	}
	public void setPoint(int i,Point3f p) {
		points[i]=p;
	}
	public float getRadius(int i) {
		return radii[i];
	}
	public void setRadius(int i,float r) {
		radii[i]=r;
	}
	public BranchSkeleton child(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow) {
		float d=Rand.randomInRange(totalLength,rand),childLength=maxLength*(totalLength-0.6f*d),childRadius=radii[0]*(float)Math.pow(childLength/totalLength,ratioPow);
		Point3f[] starts=getPointAndDirOnBranch(d);
		Transform3D t0=new Transform3D(),t1=new Transform3D();
		t0.rotZ(-Rand.randomInRange(down-downV,down+downV,rand));
		lastRotate+=Rand.randomInRange(rotate-rotateV,rotate+rotateV,rand)%(2*Math.PI);
		t1.rotY(lastRotate);
		t1.mul(t0);
		t1.transform(starts[1]);
		return new BranchSkeleton(nextCurveRes,nextCurve,nextCurveBack,nextCurveV,rand,childLength,childRadius,starts[0],new Vector3f(starts[1]),d);
	}
	public float getOffset() {
		return offset;
	}
	public void setOffset(float offset) {
		this.offset = offset;
	}
}