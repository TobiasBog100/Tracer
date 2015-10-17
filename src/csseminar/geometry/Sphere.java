package csseminar.geometry;


public class Sphere implements Shape{
	
	
	private Vector Mittelortsvektor;
	private double ra;
	
	public Sphere(Vector Ortsvektor, double ra){
		Mittelortsvektor = Ortsvektor;
		this.ra =ra;
	}
	
	@Override	
	
	public IntersectionDesc intersects(Ray r){
		
		Vector o = r.ortsvektor.sub(Mittelortsvektor);
		double sk = o.skmultivektor(r.richtung);
		

		double D = sk*sk - o.pot() + ra*ra;
		
		if (D < 0.0){
			return null;
		}
		
		
		IntersectionDesc id = new IntersectionDesc();
		
		id.t = (- sk )- (Math.sqrt(D));
		
		if (id.t < 0.0){
			return null;
		}
		
		
		
		id.originalRay = r;
		id.theHitShape = this; // Shape !!!!!!!!!
		id.position = r.vonPunktStrahl(id.t);
		id.normal = id.position.sub(Mittelortsvektor).einheitsvektor();
		
		
		return id;
	}

}
