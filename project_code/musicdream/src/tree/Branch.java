package tree;
import java.util.Random;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import utils.Rand;
/**
 * A class for representation of the skeleton of a single branch:
 * locations and radii for each vertex.
 * @author Amir
 */
public class Branch extends Bough {
	private float offset;
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
	public Branch(int nCurveRes,float nCurve,float nCurveBack,float nCurveV,Random rand,float totalLength,float radius,Point3f startPoint,Vector3f startVector,float offset) {
		setStartPoints(nCurveRes,radius,startVector,rand,offset,totalLength); //Some initialization.
		setVectors(nCurveRes,nCurve,nCurveBack,nCurveV);
		points=utils.ThreeD.setPoints(directions,startPoint); //Derives points from vectors and starting point.
	}
	/*
	 * Setting some initialization values:
	 * Number of stems in this branch, length of vector and radii array.
	 * Setting the first vector as the first vector given to us, normalized.
	 */
	private void setStartPoints(int nCurveRes,float radius,Vector3f startVector,Random rand,float offset,float totalLength) {
		super.setStartPoints(nCurveRes,rand,totalLength);
		for(int i=0;i<=nCurveRes;i++) radii[i]=radius*(nCurveRes-i)/nCurveRes;
		startVector.normalize();
		directions[0]=new Vector3f(startVector);
		this.offset=offset;
	}
	public Branch child(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow) {
		float d=Rand.randomInRange(totalLength,rand),childLength=maxLength*(totalLength-0.6f*d),childRadius=radii[0]*(float)Math.pow(childLength/totalLength,ratioPow);
		return super.child(childLength,childRadius,d,down,downV,rotate,rotateV,nextCurveRes,nextCurve,nextCurveBack,nextCurveV);
	}
	public float getOffset() {
		return offset;
	}
	public void setOffset(float offset) {
		this.offset = offset;
	}
	public float getRelativeOffset() {
		return offset/totalLength;
	}
}