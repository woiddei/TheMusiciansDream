package tree;
import java.util.Random;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import utils.Rand;
public class Trunk extends Bough {
	private float radius;
	public Trunk(int nCurveRes,float nCurve,float nCurveBack,float nCurveV,Random rand,float totalLength,float radius,float flare) {
		setStartPoints(nCurveRes,radius,rand,totalLength,flare); //Some initialization.
		setVectors(nCurveRes,nCurve,nCurveBack,nCurveV);
		points=utils.ThreeD.setPoints(directions,new Point3f(0,0,0)); //Derives points from vectors and starting point.
	}
	private void setStartPoints(int nCurveRes,float radius,Random rand,float totalLength,float flare) {
		super.setStartPoints(nCurveRes,rand,totalLength);
		this.radius=radius;
		for(int i=0;i<=nCurveRes;i++)
			radii[i]=(radius*(nCurveRes-i)/nCurveRes)*(flare*((float)Math.pow(100,Math.max(1-8*i/nCurveRes,0))-1)/100+1);
		directions[0]=new Vector3f(0,1,0);
	}
	public Branch child(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow,float barePart,TreeShape ts) {
		float bare=barePart*totalLength,d=Rand.randomInRange(bare,totalLength,rand),childLength=maxLength*totalLength*ts.shapeRatio((totalLength-d)/(length-bare)),childRadius=radius*(float)Math.pow(childLength/totalLength,ratioPow);
		return super.child(childLength,childRadius,d,down,downV,rotate,rotateV,nextCurveRes,nextCurve,nextCurveBack,nextCurveV);
	}
}