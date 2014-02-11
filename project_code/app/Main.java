package app;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
public class Main extends Applet {
	/**
	 * First code draft. Just trying to figure out j3d.
	 */
	private static final long serialVersionUID = 1L;
	public Main() {
		GraphicsConfiguration GC_me = SimpleUniverse.getPreferredConfiguration();
		Canvas3D Canvas_me = new Canvas3D(GC_me);
		setLayout(new BorderLayout());
		add("Center", Canvas_me);
		SimpleUniverse universe = new SimpleUniverse(Canvas_me);
		universe.getViewingPlatform().setNominalViewingTransform();
		BranchGroup group = new BranchGroup();
		Sphere sphere = new Sphere(0.5f);
		group.addChild(sphere);
		Color3f light1Color = new Color3f(0.0f, 0.8f, 0.1f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);
		
	}
public void init() {
		System.out.println("started!");
		super.resize(1000,700);
		new Main();
	}
}