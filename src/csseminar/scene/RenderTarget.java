package csseminar.scene;

import csseminar.appearance.Color;

// something that can be rendered onto.
public interface RenderTarget {
	void setPixel(int x, int y, Color c);
	int getWidth();
	int getHeight();
}
