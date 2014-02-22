package tree;
/**
 * Some shape options for the tree's shape.
 * Each function is recognized by a shapeIndex.
 * Each shape is connected with its own shapeRatio function.
 * @author Amir
 */
public enum TreeShape {
	CONICAL,
	SPHERICAL,
	HEMISPHERICAL,
	CYLINDRICAL,
	TAPERED_CYLINDRICAL,
	FLAME,
	INVERSE_CONICAL,
	TEND_FLAME;
	/**
	 * Respective function for this tree's shape.
	 * @param ratio - the input ratio for this function.
	 * @return the shape function output.
	 */
	public float shapeRatio(float ratio) {
		switch(this) { //respective function for each shape.
		case CONICAL: return 0.2f+0.8f*ratio;
		case SPHERICAL: return 0.2f+0.8f*(float)Math.sin(Math.PI*ratio);
		case HEMISPHERICAL: return 0.2f+0.8f*(float)Math.sin(Math.PI*ratio/2);
		case CYLINDRICAL: return 1;
		case TAPERED_CYLINDRICAL: return 0.5f+0.5f*ratio;
		case FLAME: return (ratio<=0.7?ratio/0.7f:(1.0f-ratio)/0.3f);
		case INVERSE_CONICAL: return 1.0f-0.8f*ratio;
		case TEND_FLAME: return (ratio<=0.7?0.5f+0.5f*ratio/0.7f:0.5f+0.5f*(1.0f-ratio)/0.3f);
		}
		return ratio;
	}
}