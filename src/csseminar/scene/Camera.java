package csseminar.scene;

import csseminar.geometry.Ray;

public interface Camera {
	
	public Ray getRayForPixel(double px, double py);
}
