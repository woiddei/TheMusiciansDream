package utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public enum TreePoints {
	A(44.964798f,115.988159f),
	B(35.317366f,24.581909f),
	C(-26.273714f,124.074097f),
	D(55.776573f,-30.640869f),
	E(-3.513421f,32.991943f),
	F(56.36525f,-112.554932f),
	G(5.615986f,-75.289307f),
	H(-26.431228f,143.382568f),
	I(23.885838f,6.624756f),
	J(24.527135f,76.937256f),
	K(-1.054628f,109.984131f),
	L(30.448674f,66.741943f),
	M(55.776573f,54.437256f),
	N(64.472794f,-130.836182f),
	O(30.751278f,-97.086182f),
	P(46.073231f,-122.750244f),
	Q(-8.754795f,-47.867432f),
	R(-33.137551f,-70.367432f),
	S(62.431074f,125.804443f),
	T(43.068888f,18.956909f),
	U(67.542167f,98.234253f),
	V(63.937372f,17.72644f),
	W(53.330873f,20.363159f),
	X(49.037868f,7.179565f),
	Y(28.149503f,43.03894f),
	Z(41.046217f,41.910095f);
	private float lat,lng;
	public static final float EARTH_RADIUS=6371.009f;
	private TreePoints(float lat,float lng) {
		this.lat=lat;
		this.lng=lng;
	}
	public float getLat() {
		return lat;
	}
	public float getLng() {
		return lng;
	}
	public float getDistance(float lat,float lng) {
		return ThreeD.sphericanDistance(this.lat,this.lng,lat,lng,EARTH_RADIUS);
	}
	public static TreePoints getClosest(float lat,float lng) {
		final float LAT=lat,LNG=lng;
		List<TreePoints> vals=Arrays.asList(TreePoints.values());
		return Collections.min(vals,new Comparator<TreePoints>() {
			@Override
			public int compare(TreePoints arg0,TreePoints arg1) {
				return Float.compare(arg0.getDistance(LAT,LNG),arg1.getDistance(LAT,LNG));
			}});
	}
	public static TreePoints getClosest() {
		float[] location=LocationAnalyzer.getCoordinates();
		return getClosest(location[0],location[1]);
	}
}