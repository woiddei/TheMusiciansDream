package tree;
public enum TreeShape {
	ONICAL(0),
	SPHERICAL(1),
	HEMISPHERICAL(2),
	CYLINDRICAL(3),
	TAPERED_CYLINDRICAL(4),
	FLAME(5),
	INVERSE_CONICAL(6),
	TEND_FLAME(7);
	private int shapeIndex;
	private TreeShape(int shapeIndex) {
		this.shapeIndex=shapeIndex;
	}
	public double shapeRatio(float ratio) {
		switch(shapeIndex) {
		case 0: return 0.2+0.8*ratio;
		case 1: return 0.2+0.8*Math.sin(Math.PI*ratio);
		case 2: return 0.2+0.8*Math.sin(Math.PI*ratio/2);
		case 3: return 1;
		case 4: return 0.5+0.5*ratio;
		case 5: return (ratio<=0.7?ratio/0.7:(1.0-ratio)/0.3);
		case 6: return 1.0-0.8*ratio;
		case 7: return (ratio<=0.7?0.5+0.5*ratio/0.7:0.5+0.5*(1.0-ratio)/0.3);
		}
		return ratio;
	}
}