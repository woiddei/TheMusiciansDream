package app;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.util.Arrays;
import java.util.Random;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import tree.TreeShape;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
/**
 * The main class of the applet - activates the 3d interface.
 * Contains ome usage of stem objects, for now.
 * @author Amir
 */
public class Main extends Applet {
	private static final long serialVersionUID = 2L;
	SimpleUniverse universe=null;
	BranchGroup group=null;
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
		group=new BranchGroup();
		setSize(XRESOLUTION,YRESOLUTION);
		GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas=new Canvas3D(gc);
		setLayout(new BorderLayout());
		add("Center",canvas); //Magical lines that make java 3d work properly. Don't ask me why.
		universe=new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(new Transform3D(new float[] {1,0,0,0,0,1,0,5,0,0,1,25,0,0,0,1}));
		showStatus("Loading Scene");
		addChildren();
	}
	/**
	 * Creates the objects on the scene, adds behavior effects and so on.
	 */
	public void addChildren() {
		try{
		BranchGroup group=new BranchGroup(); //Root group of the applet.
		Appearance ap=new Appearance(); //Configurate a material for the branch.
		Material mat=new Material();
		mat.setAmbientColor(new Color3f(43/255f,23/255f,5/255f)); //Brown.
		ap.setMaterial(mat);
		TransformGroup tg=new TransformGroup(); //This will actually store all leaf nodes because we want them to rotate with mouse movements.
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		GeometryArray geo=new TriangleArray(3,GeometryArray.COORDINATES|GeometryArray.ALLOW_COLOR_WRITE);
		geo.setCoordinates(0,new float[] {-0.05f,0,0,0.05f,0,0,0,0.2f,0});
		Shape3D leafShape=new Shape3D(geo);
		Appearance ap0=new Appearance();
		Material mat0=new Material();
		mat0.setAmbientColor(new Color3f(58/255f,95/255f,11/255f)); //Green.
		ap0.setMaterial(mat0);
		leafShape.setAppearance(ap0);
		Tree tree=new Tree(new Random(13214),TreeShape.CONICAL,0.3f,3,0.02f,1.2f,5,0.2f,0.5f,1.1f,1f,1f,0f,Arrays.asList(10,6,5),Arrays.asList(0.5f,0.3f,0.2f,0.4f),Arrays.asList(-0.3f,-0.5f,0f,0.1f),Arrays.asList(0.1f,0.05f,0.05f,0.05f),Arrays.asList(30f,0.5f,0.5f,0.5f),Arrays.asList(1f,0.5f,0.5f,0.5f),Arrays.asList(0.5f,0.3f,0.5f,0.5f),Arrays.asList(0.1f,0.1f,0.2f,0.3f),Arrays.asList(1f,1f,1f,1f),Arrays.asList(0.5f,0.5f,0.5f,1f),Arrays.asList(0,40,10,10),Arrays.asList(ap),0.1f,new Leaf(leafShape),20,0f);
		tg.addChild(tree);
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
		showStatus("Displaying Scene");
		universe.addBranchGraph(group);
		}
		catch(Error e) {
			System.out.println(e.getMessage());
		}
	}
}