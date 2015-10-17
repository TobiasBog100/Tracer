package csseminar.geometry;

public class Vector {

	public double x;
	public double y;
	public double z;
	
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector(double x,double y,double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	public Vector sub(Vector a) {
		Vector b= new Vector(x-a.x, y-a.y, z-a.z);
		return b;
	}	
	
	public Vector add(Vector a) {
		Vector b= new Vector(x+a.x, y+a.y, z+a.z);
		return b;
	}
	

	public double skmultivektor(Vector a) {
		double d= (x*a.x + y*a.y + z*a.z);
		return d;
	}

	public Vector verlaengerung(double la) {
		Vector b= new Vector(la*x, la*y, la*z);
		return b;
	}
	
	
	public double pot() {
		double d= x*x+y*y+z*z;
		return d;
	}

	
	public double laenge() {
		double d= Math.sqrt(this.pot());
		return d;
	}

	
	public Vector einheitsvektor() {
		double d1= this.laenge();
		double d= 1 / d1;
		Vector b= new Vector(x*d,y*d,z*d);
		return b;
	}
	
	public Vector reflect(Vector n) {
		return n.verlaengerung(2.0*this.skmultivektor(n)).sub(this);
	}
	
	public Vector refract(Vector n, double ind_from, double ind_to) {
		

		double cosTheta_o = -this.skmultivektor(n);
		double c = cosTheta_o;
		double r = ind_from / ind_to;
		
		double cosTheta_i = Math.sqrt(Math.max(0.0, 1.0 - r*r * (1.0 - c*c)));
		
		Vector wi = this.verlaengerung(-r).add(n.verlaengerung(cosTheta_i - r*c));
				
		return wi;
	}
	
	
}
