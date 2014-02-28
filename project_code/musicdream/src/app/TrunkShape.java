package app;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Sphere;
import tree.TreeShape;
import tree.Trunk;
import tree.TrunkStem;
public class TrunkShape extends BoughShape {
	private Trunk trunk;
	public TrunkShape(Trunk trunk,Appearance app,float res,float lobeDepth,int lobes) {
		this.trunk=trunk;
		this.res=res;
		Shape3D shape;
		Sphere s;
		this.app=app;
		for(int i=0;i<trunk.getStemsLength();i++) {
			shape=new StemShape(new TrunkStem(trunk.getPoint(i),trunk.getPoint(i+1),trunk.getRadius(i),trunk.getRadius(i+1),Math.max((int)(trunk.getRadius(i)*Math.PI/res),6),lobeDepth,lobes),app);
			this.addChild(shape);
			s=new Sphere(trunk.getRadius(i));
			s.setAppearance(app);
			TransformGroup tg=new TransformGroup();
			if(i>0) tg.addChild(s);
			Transform3D t=new Transform3D();
			t.setTranslation(new Vector3f(trunk.getPoint(i).x,trunk.getPoint(i).y,trunk.getPoint(i).z));
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
	public Trunk getTrunk() {
		return trunk;
	}
	public void setTrunk(Trunk trunk) {
		this.trunk=trunk;
	}
	private BranchShape addBranchChild(float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow,float barePart,TreeShape ts,Appearance app) {
		BranchShape son=new BranchShape(trunk.child(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow,barePart,ts),app,1,res);
		addChild(son);
		sons.add(son);
		return son;
	}
	public int getStemNum(int maxStems) {
		return (int)Math.round(maxStems);
	}
	public List<BranchShape> addChildren(int maxStems,float down,float downV,float rotate,float rotateV,float maxLength,int nextCurveRes,float nextCurve,float nextCurveBack,float nextCurveV,float ratioPow,float barePart,TreeShape ts,Appearance app) {
		int stems=getStemNum(maxStems);
		for(int i=0;i<stems;i++)
			addBranchChild(down,downV,rotate,rotateV,maxLength,nextCurveRes,nextCurve,nextCurveBack,nextCurveV,ratioPow,barePart,ts,app);
		return sons;
	}
}