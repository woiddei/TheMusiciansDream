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
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
/**
 * The main class of the applet - activates the 3d interface.
 * Contains ome usage of stem objects, for now.
 * @author Amir
 */
public class AppTest5 extends Applet {
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
		add("Center",canvas); //Magical lines that make java 3d work properly. Don't ask me why.
		universe=new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(new Transform3D(new float[] {1,0,0,0,0,1,0,5,0,0,1,25,0,0,0,1}));
		addChildren();
	}
	/**
	 * Creates the objects on the scene, adds behavior effects and so on.
	 */
	public void addChildren() {
		BranchGroup group=new BranchGroup(); //Root group of the applet.
		Appearance ap=new Appearance(); //Configurate a material for the branch.
		Material mat=new Material();
		mat.setAmbientColor(new Color3f(43/255f,23/255f,5/255f)); //Brown.
		ap.setMaterial(mat);
		TransformGroup tg=new TransformGroup(); //This will actually store all leaf nodes because we want them to rotate with mouse movements.
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//Tree tree=new Tree(new Random(13214),TreeShape.CONICAL,0.3f,2,0.02f,1.2f,5,0.2f,0.5f,1.1f,1f,1f,0f,Arrays.asList(10,6,4),Arrays.asList(0.5f,0.3f,0.2f,0.4f),Arrays.asList(-0.3f,-0.5f,0f,0.1f),Arrays.asList(0.1f,0.05f,0.05f,0.05f),Arrays.asList(30f,0.5f,0.5f,0.5f),Arrays.asList(1f,1f,0.5f,0.1f),Arrays.asList(0.5f,0.3f,0.5f,0.5f),Arrays.asList(0.1f,0.1f,0.2f,0.3f),Arrays.asList(1f,1f,1f,1f),Arrays.asList(0.5f,0.5f,0.5f,0.5f),Arrays.asList(1,10,10,10),Arrays.asList(ap),0.01f);
		//tg.addChild(tree);
		MouseRotate mr=new MouseRotate(tg);
		mr.setSchedulingBounds(new BoundingSphere(new Point3d(),100));
		mr.setFactor(0.005,0.001); //Slow rotation.
		tg.addChild(mr);
		group.addChild(tg);
		Color3f light1Color = new Color3f(1f,1f,1f); //Just to see the model, as a temporary solution.
		BoundingSphere bounds = new BoundingSphere(new Point3d(),100);
		AmbientLight light1 = new AmbientLight(light1Color);
		light1.setInfluencingBounds(bounds); //Some more j3d magic.
		group.addChild(light1);
		universe.addBranchGraph(group);
	}
}