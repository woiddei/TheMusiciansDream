package app;
import java.util.ArrayList;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Sphere;
import tree.BranchSkeleton;
import tree.Stem;
public class BranchShape extends Group {
	private BranchSkeleton bs;
	private int level;
	private Appearance[] apps;
	private float res;
	private List<BranchShape> sons=new ArrayList<>();
	public BranchShape(BranchSkeleton bs,Appearance[] apps,int level,float res) {
		super();
		this.bs=bs;
		this.res=res;
		Shape3D shape;
		Sphere s;
		this.apps=apps;
		this.level=level;
		for(int i=0;i<bs.getStemsLength();i++) {
			shape=new StemShape(new Stem(bs.getPoint(i),bs.getPoint(i+1),bs.getRadius(i),bs.getRadius(i+1),Math.max((int)(bs.getRadius(i)*Math.PI/res),6)),apps[level]);
			this.addChild(shape);
			s=new Sphere(bs.getRadius(i));
			s.setAppearance(apps[level]);
			TransformGroup tg=new TransformGroup();
			tg.addChild(s);
			Transform3D t=new Transform3D();
			t.setTranslation(new Vector3f(bs.getPoint(i).x,bs.getPoint(i).y,bs.getPoint(i).z));
			float[] f=new float[16];t.get(f);
			tg.setTransform(t);
			this.addChild(tg);
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
	public BranchSkeleton getBs() {
		return bs;
	}
	public void setBs(BranchSkeleton bs) {
		this.bs=bs;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level=level;
	}
	public Appearance[] getApps() {
		return apps;
	}
	public void setApps(Appearance[] apps) {
		this.apps=apps;
	}
	public Appearance getApp(int i) {
		return apps[i];
	}
	public void setApp(int i,Appearance app) {
		this.apps[i]=app;
	}
	private void addBranchChild(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow) {
		BranchShape son=new BranchShape(bs.child(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow),apps,level+1,res);
		addChild(son);
		sons.add(son);
	}
	public void addChildren(int maxStems,float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow) {
		int stems=(int)Math.round(maxStems*(1-0.5*(bs.getOffset())));
		for(int i=0;i<stems;i++)
			addBranchChild(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow);
	}
	public List<BranchShape> getSons() {
		return sons;
	}
}