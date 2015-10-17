package csseminar.geometry;

public class IntersectionDesc {
	// information about the ray that hit the object
	public Ray originalRay;
	public double t;
	
	// geometric information about the hitpoint: 
	//   position and normal vector
	public Vector position;
	public Vector normal;
	
	// the shape that was hit by the ray
	public Shape theHitShape;
	
	// benötigt für Dreiecke/Vierecke und Texturierung:
	//   barycentric coordinates
	//public double tu;
	//public double tv;	
}
