package csseminar.appearance;

public class Color {
	
	// color constants
	public static final Color BLACK = new Color(0,0,0);
	public static final Color RED = new Color(1,0,0);
	public static final Color GREEN = new Color(0,1,0);
	public static final Color BLUE = new Color(0,0,1);
	public static final Color YELLOW = new Color(1,1,0);
	public static final Color MAGENTA = new Color(1,0,1);
	public static final Color CYAN = new Color(0,1,1);
	public static final Color WHITE = new Color(1,1,1);
	
	public static final Color ORANGE = new Color(1,0.5,0);
	public static final Color LIGHTBLUE = new Color(0,0.5,1);
	public static final Color DARKGREEN = new Color(0,0.5,0);
	public static final Color GRAY = new Color(0.5,0.5,0.5);
	public static final Color LIGHTGRAY = new Color(0.8,0.8,0.8);
	public static final Color DARKGRAY = new Color(0.3,0.3,0.3);
	
	
	// components
	private double[] components = new double[3];
	
	public Color(double _r,double _g,double _b) {
		components[0] = _r;
		components[1] = _g;
		components[2] = _b;
	}
	
	public int getRGBInteger() {
		int rComp = (int)(components[0]*255.0) << 16;
		int gComp = (int)(components[1]*255.0) << 8;
		int bComp = (int)(components[2]*255.0);
		return (0xff000000 + rComp + gComp + bComp);
	}
	
	public static Color fromRGBInteger(int packed) {
		return new Color(
				(double)((packed >> 16) & 0xff) / 255.0,
				(double)((packed >> 8) & 0xff) / 255.0,
				(double)(packed & 0xff) / 255.0);
	}
	
	public Color add(Color c) {
		return new Color(
				components[0]+c.components[0],
				components[1]+c.components[1],
				components[2]+c.components[2]);
	}
	
	public Color mult(double l) {
		return new Color(components[0]*l,components[1]*l,components[2]*l);
	}
	
	public Color mult(Color c) {
		return new Color(components[0]*c.components[0],
				components[1]*c.components[1],
				components[2]*c.components[2]);
	}
	
	public double getR() { return components[0]; }
	public double getG() { return components[1]; }
	public double getB() { return components[2]; }
	
	public Color clamp() {
		double r = components[0];
		double g = components[1];
		double b = components[2];
		
		if(r < 0.0)			r = 0.0;
		else if(r > 1.0)	r = 1.0;
		if(g < 0.0)			g = 0.0;
		else if(g > 1.0)	g = 1.0;
		if(b < 0.0)			b = 0.0;
		else if(b > 1.0)	b = 1.0;
		
		return new Color(r,g,b);
	}
	
	public Color rescale() {
		double _max = components[0];
		double _min = components[0];
		if(components[1] > _max)
			_max = components[1];
		else if(components[1] < _min)
			_min = components[1];
		if(components[2] > _max)
			_max = components[2];
		else if(components[2] < _min)
			_min = components[2];
		
		double range = _max - _min;
		
		return new Color(
				(components[0] - _min) / range,
				(components[1] - _min) / range,
				(components[2] - _min) / range);
	}
	
}
