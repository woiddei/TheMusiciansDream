package tree;
import javax.media.j3d.GeometryArray;
/**
 * An interface to represent both stems for branched and of trunks similarly.
 * Note that a Stem object should only contain geometrical information - none of the actual 3d shape rendering should happen here.
 */
public interface Stem {
	public GeometryArray getGeometryArray();
}