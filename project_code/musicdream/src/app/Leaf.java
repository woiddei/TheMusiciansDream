package app;
import java.util.Random;
import javax.media.j3d.Shape3D;
import utils.Rand;
import utils.ThreeD;
public class Leaf {
	private Shape3D[] leafShape;
	private float[] prob;
	public Leaf(Shape3D leafShape) {
		this.leafShape=new Shape3D[1];
		this.leafShape[0]=leafShape;
		this.prob=new float[1];
		this.prob[0]=1;
	}
	public Shape3D getShape(Random rand) {
		return ThreeD.clone(leafShape[Rand.getProbArray(prob,rand)]);
	}
}