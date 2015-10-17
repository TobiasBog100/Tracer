package csseminar.geometry;

public class Plane implements Shape{
	
	private Vector n;
	private double b;
	
	public Plane(Vector n, double b) {
		this.n = n;
		this.b = b;
	}
	
	@Override
	
	public IntersectionDesc intersects(Ray r){
		
		double c = r.richtung.skmultivektor(n);
		
		if (c == 0.0){
			return null;
		}
		
		
		IntersectionDesc id = new IntersectionDesc();
		id.t = (b - r.ortsvektor.skmultivektor(n)) / c;
		
		if (id.t < 0.0){
			return null;
		}
		
		id.originalRay = r;
		id.theHitShape = this;
		id.position = r.vonPunktStrahl(id.t);
		id.normal = n.einheitsvektor();
		
		return id;	
	}

	
}
