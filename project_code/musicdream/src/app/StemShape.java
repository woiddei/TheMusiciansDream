package app;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import tree.Stem;
public class StemShape extends Shape3D {
	private Stem stem;
	private Appearance app;
	public StemShape(Stem stem,Appearance app) {
		super(stem.getGeometryArray(),app);
		this.app=app;
	}
	public Stem getStem() {
		return stem;
	}
	public void setStem(Stem stem) {
		this.stem=stem;
	}
	public Appearance getApp() {
		return app;
	}
	public void setApp(Appearance app) {
		this.app=app;
	}
	
}