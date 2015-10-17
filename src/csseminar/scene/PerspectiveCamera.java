package csseminar.scene;

import csseminar.geometry.Ray;
import csseminar.geometry.Vector;

public class PerspectiveCamera implements Camera {

	int resX;  // Breite
	int resY;  // Höhe
	
	double fov;
	double focal_length;
	
	double W;
	double H;
	
	double WP;
	double HP;
	
	PerspectiveCamera(int resX1, int resY1, double fov1, double focal_length1){
		resX = resX1;
		resY = resY1;
		
		fov = fov1;
		focal_length= focal_length1;
		
		
		
		W = 2* focal_length * Math.tan(fov/2);
		double resY2 = resY;
		H = (resY2)/resX * W; 
		
		WP = W /resX;
		HP = H /resY;
		
		
	}
	@Override
	public Ray getRayForPixel(double px, double py){
		//Vector  Obli = (new Vector (W/(-2),(H/2),0.0));
		Vector  punkt = (new Vector (W/(-2)+px*WP,(H/2)-py*HP,0.0));
		Vector Aug  = new Vector(0.0,0.0, -focal_length);
		Vector richt = (punkt.sub(Aug)).einheitsvektor();
		Ray Strahl = new Ray (punkt, richt);
		return Strahl;
		
	}
	
	
}
