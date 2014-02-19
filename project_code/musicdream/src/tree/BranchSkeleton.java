package tree;
import java.util.Random;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Sphere;

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
	public BranchSkeleton(int nCurveRes,float nCurve,float nCurveBack,float nCurveV,Random rand,float totalLength,float radius,Point3f startPoint,Vector3f startVector) {
		setStartPoints(nCurveRes,radius,startVector); //Some initialization.
		if(nCurveBack==0) {
			for(int i=1;i<nCurveRes;i++)
				setVector(i,nCurve,nCurveRes,nCurveV,rand);
		}
		else {
			for(int i=1;i<=nCurveRes/2;i++)
				setVector(i,nCurve,nCurveRes/2,nCurveV,rand);
			for(int i=nCurveRes/2+1;i<nCurveRes;i++)
				setVector(i,nCurveBack,nCurveRes/2,nCurveV,rand);
		} //All vector values currently point in the right direction but may have incorrect length.
		scaleDirections(totalLength,nCurveRes); //Scaling the vectors correctly.
		points=utils.ThreeD.setPoints(directions,startPoint); //Derives points from vectors and starting point.
	}
	/*
	 * Setting some initialization values:
	 * Number of stems in this branch, length of vector and radii array.
	 * Setting the first vector as the first vector given to us, normalized.
	 */
	private void setStartPoints(int nCurveRes,float radius,Vector3f startVector) {
		length=nCurveRes;
		radii=new float[nCurveRes+1];
		for(int i=0;i<=nCurveRes;i++) radii[i]=radius*(nCurveRes-i)/nCurveRes;
		directions=new Vector3f[nCurveRes];
		startVector.normalize();
		directions[0]=new Vector3f(startVector);
	}
	/*
	 * Sets a vector given the last one and all angle parameters.
	 */
	private void setVector(int i,float deterAngle,int curves,float randAngle,Random random) {
		directions[i]=new Vector3f(0,1,0);
		Transform3D thisTransform=new Transform3D();
		thisTransform.rotZ(-angleCalc(deterAngle,curves/2,randAngle,random));
		thisTransform.transform(directions[i]);
		ThreeD.bringY(directions[i-1]).transform(directions[i]);
	}
	/*
	 * Randomly assign value for an angle, given the total curvature angle,
	 * the number of stems to form this curvature, and the magnitude of the
	 * random addition to the curvature. Also, a Random regenrator object
	 * should be passed for creation of the random values.
	 */
	private double angleCalc(float angle,int n,float magniture,Random rand) {
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
	/**
	 * Creates a group node from this BranchSkeleton object.
	 * @param app Apperance object for this branch.
	 * @param res - number of straight lines to approximate
	 * each stem of this branch with.
	 * @return a group node representing this BranchSkeleton's shape,
	 * using the given Appearance object.
	 */
	public Node getBranchShape(Appearance app,int res) {
		Group ans=new Group();
		Shape3D shape;
		Sphere s;
		for(int i=0;i<length;i++) {
			shape=new Stem(points[i],points[i+1],radii[i],radii[i+1],res).getShape();
			shape.setAppearance(app);
			ans.addChild(shape);
			s=new Sphere(radii[i]);
			s.setAppearance(app);
			TransformGroup tg=new TransformGroup();
			tg.addChild(s);
			Transform3D t=new Transform3D();
			t.setTranslation(new Vector3f(points[i].x,points[i].y,points[i].z));
			float[] f=new float[16];t.get(f);
			tg.setTransform(t);
			ans.addChild(tg);
		}
		return ans;
	}
}