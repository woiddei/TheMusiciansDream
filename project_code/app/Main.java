package app;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.universe.SimpleUniverse;
public class Main extends Applet {
	/**
	 * First code draft. Just trying to figure out j3d.
	 */
	private static final long serialVersionUID = 1L;
	SimpleUniverse universe=null;
	BranchGroup group=new BranchGroup();
	final static int XRESOLUTION=1000,YRESOLUTION=700;
	//final static int XRESOLUTION=1920,YRESOLUTION=1080;
	//for exporting high-quality movie.
	@Override
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
	public void addChildren() {
		BranchGroup group=new BranchGroup();
		Cone cone = new Cone(0.4f,0.7f);
		Appearance ap=new Appearance();
		Material mat=new Material();
		mat.setAmbientColor(new Color3f(0.0f,0.0f,1.0f));
		ap.setMaterial(mat);
		cone.setAppearance(ap);
		TransformGroup tg=new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(cone);
		MouseRotate mr=new MouseRotate(tg);
		mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100));
		mr.setFactor(0,0.001);
		tg.addChild(mr);
		group.addChild(tg);
		Color3f light1Color = new Color3f(0.1f,0.2f,0.2f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(), 100);
		AmbientLight light1 = new AmbientLight(light1Color);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		universe.addBranchGraph(group);
	}
}