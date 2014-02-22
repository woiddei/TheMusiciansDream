package test.app;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import tree.BranchStem;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
/**
 * The main class of the applet - activates the 3d interface.
 * Contains ome usage of stem objects, for now.
 * @author Amir
 */
public class AppTest1 extends Applet {
	private static final long serialVersionUID = 2L;
	SimpleUniverse universe=null;
	BranchGroup group=new BranchGroup();
	final static int XRESOLUTION=1000,YRESOLUTION=700;
	//final static int XRESOLUTION=1920,YRESOLUTION=1080;
	//for exporting high-quality movie.
	@Override
	/**
	 * Overrides the native initialising method of the applet.
	 * Resizes the applet according to the needed resolution,
	 * and creates the basic 3d universe and canvas.
	 */
	public void init() {
		setSize(XRESOLUTION,YRESOLUTION);
		GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas=new Canvas3D(gc);
		setLayout(new BorderLayout());
		add("Center",canvas);
		universe=new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		addChildren();
	}
	/**
	 * Creates the objects on the scene, adds behavior effects and so on.
	 */
	public void addChildren() {
		BranchGroup group=new BranchGroup();
		Shape3D stem=new Shape3D(new BranchStem(new Point3f(0,0,-1.5f),new Point3f(0,0,-0.5f),0.1f,0.0f,10).getGeometryArray());
		Appearance ap=new Appearance();
		Material mat=new Material();
		mat.setAmbientColor(new Color3f(43/255f,23/255f,5/255f));
		ap.setMaterial(mat);
		stem.setAppearance(ap);
		TransformGroup tg=new TransformGroup();
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//These lines used to work before BranchShape was refactored out of BranchSkeletom.
		//tg.addChild(new BranchSkeleton(6,0.5f,-0.5f,0.3f,new Random(1253312432),6f,0.1f,new Point3f(0,0,-10),new Vector3f(0,1,0),0.1f).getBranchShape(ap,10));
		//tg.addChild(new BranchSkeleton(6,0.5f,-0.5f,0.3f,new Random(13125524),6f,0.1f,new Point3f(0,0,-10),new Vector3f(0,1,0),0.1f).getBranchShape(ap,10));
		//tg.addChild(new BranchSkeleton(6,0.5f,-0.5f,0.3f,new Random(1345512324),6f,0.1f,new Point3f(0,0,-10),new Vector3f(0,1,0),0.1f).getBranchShape(ap,10));
		MouseRotate mr=new MouseRotate(tg);
		mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100));
		mr.setFactor(0.001,0.001);
		tg.addChild(mr);
		group.addChild(tg);
		Color3f light1Color = new Color3f(1f,1f,1f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(), 100);
		AmbientLight light1 = new AmbientLight(light1Color);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		universe.addBranchGraph(group);
	}
}