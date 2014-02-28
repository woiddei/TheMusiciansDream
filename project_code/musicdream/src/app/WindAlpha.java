package app;
import java.util.Random;
import javax.media.j3d.Alpha;
import utils.Rand;
public class WindAlpha extends Alpha {
	public WindAlpha(float pow,Random rand) {
		super(-1,Alpha.INCREASING_ENABLE|Alpha.DECREASING_ENABLE,(long)Rand.randomInRange((2000-1000*pow),rand),0,(long)(4000-2000*pow)/2,(long)(4000-2000*pow)/10,0,(long)(4000-2000*pow)/2,(long)(4000-2000*pow)/10,0);
	}
}