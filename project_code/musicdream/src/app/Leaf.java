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
	public Leaf(Shape3D leafShape0,Shape3D leafShape1,float prob) {
		this.leafShape=new Shape3D[2];
		this.leafShape[0]=leafShape0;
		this.leafShape[1]=leafShape1;
		this.prob=new float[2];
		this.prob[0]=prob;
		this.prob[1]=1-prob;
	}
	public Leaf(Shape3D leafShape0,Shape3D leafShape1,Shape3D leafShape2,float prob0,float prob1) {
		this.leafShape=new Shape3D[3];
		this.leafShape[0]=leafShape0;
		this.leafShape[1]=leafShape1;
		this.leafShape[2]=leafShape2;
		this.prob=new float[3];
		this.prob[0]=prob0;
		this.prob[1]=prob1;
		this.prob[2]=1-prob0-prob1;
	}
	public Shape3D getShape(Random rand) {
		return ThreeD.clone(leafShape[Rand.getProbArray(prob,rand)]);
	}
}