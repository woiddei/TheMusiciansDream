package utils;
import java.util.Random;
public class Rand {
	public static float randomInRange(float max,Random rand) {
		return max*rand.nextFloat();
	}
	public static float randomInRange(float min,float max,Random rand) {
		return randomInRange(max-min,rand)+min;
	}
	public static float randomIntRounded(float middle,Random rand) {
		return (int)(middle)+
			   (rand.nextFloat()+(int)(middle)>middle?1:0);
	}
	public static float randomIntRoundedWithErrorCorrection(float middle,float effective,Random rand) {
		return (int)(middle)+
			   (rand.nextFloat()+(int)(middle)>effective?1:0);
	}
}