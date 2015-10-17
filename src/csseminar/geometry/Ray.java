package csseminar.geometry;

public class Ray {


	public Vector ortsvektor;
	public Vector richtung;
	

	
	
	
	
	public Ray(Vector ortsvektor,Vector richtung) {
		this.ortsvektor = ortsvektor;
		this.richtung = richtung;
	}
	
	public Ray() {
		ortsvektor = new Vector();
		richtung = new Vector();
	}
	
	public static Ray berechnung(Vector a, Vector b) {
		Vector c= (a.sub(b)).einheitsvektor();
		Ray S = new Ray(a,c);
		return S;
	}
	
	public Vector vonPunktStrahl(double ri) {
		Vector Sv = ortsvektor.add(richtung.verlaengerung(ri));
		return Sv;
		
	}
}