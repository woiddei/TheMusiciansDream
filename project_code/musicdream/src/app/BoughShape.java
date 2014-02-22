package app;
import java.util.ArrayList;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
public abstract class BoughShape extends Group {
	protected Appearance app;
	protected float res;
	protected List<BranchShape> sons=new ArrayList<>();
	public Appearance getApp() {
		return app;
	}
	public void setApp(Appearance app) {
		this.app=app;
	}
	public List<BranchShape> getSons() {
		return sons;
	}
	public abstract int getStemNum(int maxStems);
}
