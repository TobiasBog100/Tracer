package csseminar.objects;

import csseminar.geometry.Vector;

public class PointLight {

	private Vector position;
	private double radius;
	
	public PointLight(Vector p,double r) {
		position = p;
		radius = r;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public double getRadius() {
		return radius;
	}
}
