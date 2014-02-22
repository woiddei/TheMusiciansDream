package utils;
import java.util.Random;
/**
 * Some random lottery utilities.
 * @author Amir
 */
public class Rand {
	/**
	 * Returns a random floating point number between 0 and max.
	 * @param max maximal range of the float this method returns.
	 * @param rand a Random object for this lottery.
	 * @return a random floating point number between 0 and max.
	 */
	public static float randomInRange(float max,Random rand) {
		return max*rand.nextFloat();
	}
	/**
	 * Returns a random floating point number between min and max.
	 * @param min minimal range of the float this method returns.
	 * @param max maximal range of the float this method returns.
	 * @param rand a Random object for this lottery.
	 * @return a random floating point number between min and max.
	 */
	public static float randomInRange(float min,float max,Random rand) {
		return randomInRange(max-min,rand)+min;
	}
	/**
	 * Returns a probabilistic integer based on a floating point number.
	 * If the number is an integer it will always be returned.
	 * If, for example, it is 1.2, 2 will be drawn 20% of the time and 1 80% of it.
	 * @param middle the float to draw the value from.
	 * @param rand a Random object.
	 * @return a random integer between floor(middle) and ceil(middle) with appropriate probabilities.
	 */
	public static int randomIntRounded(float middle,Random rand) {
		if((int)middle==middle) return (int)middle;
		return (int)(middle)+
			   (rand.nextFloat()+(int)(middle)>middle?1:0);
	}
	/**
	 * Returns a probabilistic integer like the last method, but accounting for errors to that
	 * after a high value is rolled there will be a greater chance for a lower number in the
	 * next roll, and vice versa.
	 * The way to do this is to add an affective middle value and to substract from it the error
	 * (returned_value-middle) of every roll.
	 * @param middle he float to draw the value from.
	 * @param effective a value to balance the probabilities with. When it's smaller, there's a larger
	 * change for the higher integer. For the first call make it NaN.
	 * @param rand a Random object.
	 * @return a random integer between floor(middle) and ceil(middle) with appropriate probabilities.
	 */
	public static int randomIntRoundedWithErrorCorrection(float middle,float effective,Random rand) {
		if(Float.isNaN(effective)) return randomIntRounded(middle,rand);
		if((int)middle==middle) return (int)middle;
		return (int)(middle)+
			   (rand.nextFloat()+(int)(middle)>effective?1:0);
	}
	public static int getProbArray(float[] probs,Random rand) {
		float f=rand.nextFloat();
		int i=0;
		while(i<probs.length&&f-probs[i]>0) {
			f-=probs[i];
			i++;
		}
		return i;
	}
	public static float angleAroundZero(float attraction,Random rand) {
		return (float)(rand.nextGaussian()/Math.sqrt(attraction));
	}
}