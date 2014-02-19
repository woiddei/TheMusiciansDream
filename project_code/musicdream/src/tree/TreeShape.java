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
	public double shapeRatio(float ratio) {
		switch(this) { //respective function for each shape.
		case CONICAL: return 0.2+0.8*ratio;
		case SPHERICAL: return 0.2+0.8*Math.sin(Math.PI*ratio);
		case HEMISPHERICAL: return 0.2+0.8*Math.sin(Math.PI*ratio/2);
		case CYLINDRICAL: return 1;
		case TAPERED_CYLINDRICAL: return 0.5+0.5*ratio;
		case FLAME: return (ratio<=0.7?ratio/0.7:(1.0-ratio)/0.3);
		case INVERSE_CONICAL: return 1.0-0.8*ratio;
		case TEND_FLAME: return (ratio<=0.7?0.5+0.5*ratio/0.7:0.5+0.5*(1.0-ratio)/0.3);
		}
		return ratio;
	}
}