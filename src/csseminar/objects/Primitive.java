package csseminar.objects;

import csseminar.appearance.Material;
import csseminar.geometry.IntersectionDesc;
import csseminar.geometry.Ray;
import csseminar.geometry.Shape;

public class Primitive implements Shape {

	Shape theShape;
	Material theMaterial;
	
	public Primitive(Shape s,Material m) {
		theShape = s;
		theMaterial = m;
	}
	
	@Override
	public IntersectionDesc intersects(Ray r) {
		return theShape.intersects(r);
	}

	public Material getMaterial() {
		return theMaterial;
	}
}
