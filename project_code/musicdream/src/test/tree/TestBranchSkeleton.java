package test.tree;
import java.util.Random;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import tree.BranchSkeleton;
/**
 * Testing class for tree.BranchSkeleton.
 * Constructs a BranchSkeleton object according to some random data,
 * and then prints vertices.
 * @author Amir
 */
public class TestBranchSkeleton {
	public static void main(String[] args) {
		BranchSkeleton bs=new BranchSkeleton(6,1,-1,0.1f,new Random(13124324),6,1,new Point3f(),new Vector3f());
		for(int i=0;i<6;i++) //print all skeleton vertices' coordinates.
			System.out.println(bs.getPoints()[i].x+" "+bs.getPoints()[i].y+" "+bs.getPoints()[i].z);
	}
}