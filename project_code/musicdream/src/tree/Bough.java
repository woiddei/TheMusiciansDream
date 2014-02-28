package tree;
import java.util.Random;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.Rand;
import utils.ThreeD;
public class Bough {
	protected Vector3f[] directions; //Vectors for the stems.
	protected Point3f[] points; //Coordinates of the stems' vertices.
	protected float[] radii;
	protected int length;
	protected Random rand;
	protected float lastRotate=0,totalLength;
	protected void  setStartPoints(int nCurveRes,Random rand,float totalLength) {
		length=nCurveRes;
		radii=new float[nCurveRes+1];
		directions=new Vector3f[nCurveRes];
		this.rand=rand;
		this.totalLength=totalLength;
	}
	protected void setVectors(int nCurveRes,float nCurve,float nCurveBack,float nCurveV) {
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
	}
	protected void setVector(int i,float deterAngle,int curves,float randAngle) {
		directions[i]=new Vector3f(0,1,0);
		Transform3D thisTransform=new Transform3D();
		ThreeD.bringY(directions[i-1]).transform(directions[i]);
		thisTransform.rotZ(-angleCalc(deterAngle,curves/2,randAngle));
		thisTransform.transform(directions[i]);
	}
	protected float angleCalc(float angle,int n,float magniture) {
		return angle/n+Rand.randomInRange(-magniture/n,magniture/n,rand);
	}
	protected void scaleDirections(float totalLength, int nCurveRes) {
		float distance=totalLength/nCurveRes;
		for(int i=0;i<nCurveRes;i++) {
			directions[i].scale(distance);
		}
	}
	public Point3f[] getPoints() {
		return this.points;
	}
	public float[] getRadii() {
		return this.radii;
	}
	public int getStemsLength() {
		return length;
	}
	public float getLength() {
		return totalLength;
	}
	public Point3f getPoint(int i) {
		return points[i];
	}
	public Vector3f getDirection(int i) {
		return directions[i];
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
	public Point3f[] getPointAndDirOnBranch(float d) {
		float sum=0;
		int i=0;
		while(i<length&&sum+directions[i].length()<d) {
			sum+=directions[i].length();
			i++;
		}
		float scaleLast=d-sum,scaleNext=sum+directions[i].length()-d,rad=(scaleLast*radii[i]+scaleNext*radii[i+1])/(scaleLast+scaleNext);
		return new Point3f[] {new Point3f((scaleLast*points[i].x+scaleNext*points[i+1].x)/(scaleLast+scaleNext),(scaleLast*points[i].y+scaleNext*points[i+1].y)/(scaleLast+scaleNext),(scaleLast*points[i].z+scaleNext*points[i+1].z)/(scaleLast+scaleNext)),new Point3f(directions[i]),new Point3f(rad,rad,rad)};
	}
	public Branch child(float childLength,float childRadius,float d,float down,float downV,float rotate,float rotateV,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV) {
		Point3f[] starts=getPointAndDirOnBranch(d);
		childRadius=Math.min(childRadius,starts[2].x);
		Transform3D t0=new Transform3D(),t1=new Transform3D();
		t0.rotZ(-Rand.randomInRange(down-downV,down+downV,rand));
		lastRotate+=Rand.randomInRange(rotate-rotateV,rotate+rotateV,rand)%(2*Math.PI);
		t1.rotY(lastRotate);
		t1.mul(t0);
		t1.transform(starts[1]);
		return new Branch(nextCurveRes,nextCurve,nextCurveBack,nextCurveV,rand,childLength,childRadius,starts[0],new Vector3f(starts[1]),d);
	}
	public float getRotate() {
		return this.lastRotate;
	}
	public float setRotate(float r) {
		return this.lastRotate=r;
	}
}