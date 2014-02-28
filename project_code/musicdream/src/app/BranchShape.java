package app;
import java.util.List;
import java.util.Random;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Sphere;
import tree.Branch;
import tree.BranchStem;
import utils.Rand;
import utils.ThreeD;
public class BranchShape extends BoughShape {
	private Branch bs;
	private int level;
	public BranchShape(Branch bs,Appearance app,int level,float res) {
		this.bs=bs;
		this.res=res;
		Shape3D shape;
		Sphere s;
		this.app=app;
		this.level=level;
		for(int i=0;i<bs.getStemsLength();i++) {
			shape=new StemShape(new BranchStem(bs.getPoint(i),bs.getPoint(i+1),bs.getRadius(i),bs.getRadius(i+1),Math.max((int)(bs.getRadius(i)*Math.PI/res),6)),app);
			this.addChild(shape);
			s=new Sphere(bs.getRadius(i));
			s.setAppearance(app);
			TransformGroup tg=new TransformGroup();
			tg.addChild(s);
			Transform3D t=new Transform3D();
			t.setTranslation(new Vector3f(bs.getPoint(i).x,bs.getPoint(i).y,bs.getPoint(i).z));
			float[] f=new float[16];t.get(f);
			tg.setTransform(t);
			addChild(tg);
		}
	}
	/**
	 * Creates a group node from this BranchSkeleton object.
	 * @param app Apperance object for this branch.
	 * @param res - number of straight lines to approximate
	 * each stem of this branch with.
	 * @return a group node representing this BranchSkeleton's shape,
	 * using the given Appearance object.
	 */
	public Branch getBs() {
		return bs;
	}
	public void setBs(Branch bs) {
		this.bs=bs;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level=level;
	}
	private BranchShape addBranchChild(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow,Appearance app) {
		BranchShape son=new BranchShape(bs.child(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow),app,level+1,res);
		addChild(son);
		sons.add(son);
		return son;
	}
	public int getStemNum(int maxStems) {
		return (int)Math.round(maxStems*(1-0.5*(bs.getRelativeOffset())));
	}
	public List<BranchShape> addChildren(int maxStems,float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow,Appearance app) {
		int stems=getStemNum(maxStems);
		for(int i=0;i<stems;i++)
			addBranchChild(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow,app);
		return sons;
	}
	private void addLeaf(Leaf leaf,float upAttract,float rotate,Random rand) {
		float d=Rand.randomInRange(bs.getLength(),rand);
		Shape3D s=leaf.getShape(rand);
		Point3f[] location=bs.getPointAndDirOnBranch(d);
		Transform3D t0=new Transform3D(),t1=ThreeD.bringX(new Vector3f(location[1].x,location[1].y,location[1].z)),t2=new Transform3D();
		t0.setTranslation(new Vector3f(location[0].x,location[0].y,location[0].z));
		if(upAttract>0) t2.rotX(Rand.angleAroundZero(upAttract,rand));
		if(upAttract<0) t2.rotX(Math.PI+Rand.angleAroundZero(-upAttract,rand));
		if(upAttract==0) t2.rotX(bs.setRotate((bs.getRotate()+rotate)%(float)Math.PI));
		t1.mul(t2);
		t0.mul(t1);
		TransformGroup tg=new TransformGroup(t0);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(s);
		super.addChild(tg);
	}
	public void addLeaves(Leaf leaf,float leaves,float upAttract,float rotate,Random rand) {
		for(int i=0;i<leaves*bs.getStemsLength();i++)
			addLeaf(leaf,upAttract,rotate,rand);
	}
}