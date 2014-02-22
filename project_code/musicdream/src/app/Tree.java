package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import tree.TreeShape;
import tree.Trunk;
import utils.Rand;
public class Tree extends Group {
	private TrunkShape trunk;
	private Random rand;
	private int levels;
	private List<Integer> branches;
	private List<Float> maxLength;
	private List<Float> down;
	private List<Float> downV;
	private List<Float> rotate;
	private List<Float> rotateV;
	private List<Integer> curveRes;
	private List<Float> curve;
	private List<Float> curveBack;
	private List<Float> curveV;
	private float ratioPow;
	private float barePart;
	private TreeShape ts;
	private List<Appearance> apps;
	private Leaf leaf;
	private float leaves;
	private float upAttract;
	public Tree(Random rand,TreeShape ts,float barePart,int levels,float ratio,float ratioPower,int lobes,float lobeDepth,float flare,float scale,float scaleV,float radScale,float radScaleV,List<Integer> curveRes,List<Float> curve,List<Float> curveBack,List<Float> curveV,List<Float> length,List<Float> lengthV,List<Float> downAngle,List<Float> downAngleV,List<Float> rotate,List<Float> rotateV,List<Integer> branches,List<Appearance> apps,float res,Leaf leaf,float leaves,float upAttract) {
		super();
		this.rand=rand;
		this.branches=branches;
		this.levels=levels;
		this.maxLength=calcMaxLength(length,lengthV);
		this.down=downAngle;
		this.downV=downAngleV;
		this.rotate=rotate;
		this.rotateV=rotateV;
		this.curveRes=curveRes;
		this.curve=curve;
		this.curveBack=curveBack;
		this.curveV=curveV;
		this.ratioPow=ratioPower;
		this.barePart=barePart;
		this.ts=ts;
		this.apps=apps;
		this.leaf=leaf;
		this.leaves=leaves;
		this.upAttract=upAttract;
		float trunkLength=(scale+Rand.randomInRange(-scaleV,scaleV,rand))*(Rand.randomInRange(-getElement(lengthV,0),getElement(lengthV,0),rand)+getElement(length,0)),
			  trunkRadius=trunkLength*ratio*(radScale+Rand.randomInRange(-radScaleV,radScaleV,rand));
		trunk=new TrunkShape(new Trunk(getElement(curveRes,0),getElement(curve,0),getElement(curveBack,0),getElement(curveV,0),rand,trunkLength,trunkRadius,flare),getElement(apps,0),res,lobeDepth,lobes);
		addAllSons(trunk);
		addChild(trunk);
	}
	private List<Float> calcMaxLength(List<Float> length, List<Float> lengthV) {
		List<Float> ans=new ArrayList<>();
		for(int i=0;i<length.size();i++)
			ans.add(length.get(i)+Rand.randomInRange(-lengthV.get(i),lengthV.get(i),rand));
		return ans;
	}
	public void addAllSons(TrunkShape t) {
		List<BranchShape> sons=addSons(t);
		for(BranchShape bs:sons) addAllSons(bs,1);
	}
	public void addAllSons(BranchShape b,int level) {
		if(level<levels) {
			List<BranchShape> sons=addSons(b,level+1);
			for(BranchShape bs:sons) addAllSons(bs,level+1);
		}
		b.addLeaves(leaf,leaves,upAttract,getElement(rotate,level+1),rand);
	}
	public List<BranchShape> addSons(TrunkShape t) {
		return t.addChildren(getElement(branches,1),getElement(down,1),getElement(downV,1),getElement(rotate,1),getElement(rotateV,1),getElement(maxLength,1),getElement(curveRes,1),getElement(curve,1),getElement(curveBack,1),getElement(curveV,1),ratioPow,barePart,ts,getElement(apps,1));
	}
	public List<BranchShape> addSons(BranchShape t,int level) {
		return t.addChildren(getElement(branches,level),getElement(down,level),getElement(downV,level),getElement(rotate,level),getElement(rotateV,level),getElement(maxLength,level),getElement(curveRes,level),getElement(curve,level),getElement(curveBack,level),getElement(curveV,level),ratioPow,getElement(apps,level));
	}
	public static <T> T getElement(List<? extends T> list,int index) {
		if(list.size()>index) return list.get(index);
		return list.get(list.size()-1);
	}
}