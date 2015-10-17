package csseminar.scene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import csseminar.appearance.Color;

public class Image implements RenderTarget {

	protected BufferedImage data;
	
	public Image(String filename) {
		try {
			data = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// Es wurde wahrscheinlich ein Pfad übergeben der nicht existiert.
			throw new IllegalArgumentException(
					"Probably the given Path ("+filename+") does not exist.", e);
		}
	}
	
	public Image(int w,int h) {
		data = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		// set all pixels to completely transparent
		for(int y=0;y<h;y++)
			for(int x=0;x<w;x++)
				data.setRGB(x,y,0);
	}
	
	public void fill(Color c) {
		for(int y=0;y<data.getHeight();y++)
			for(int x=0;x<data.getWidth();x++)
				data.setRGB(x, y, c.getRGBInteger());
	}
	
	@Override
	public int getWidth() {
		return data.getWidth();
	}
	
	@Override
	public int getHeight() {
		return data.getHeight();
	}
	
	public void saveToPNG(String filename) {
		File f = new File(filename);
		try {
			ImageIO.write(data, "png", f);
		} catch(IOException e) {
			System.out.println("Caught an IOException trying to save the rendered image to "+filename);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void drawOnto(Graphics g) {
		g.drawImage(data, 0, 0, null);
	}
	
	public Color getPixel(int x,int y) {
		return Color.fromRGBInteger(data.getRGB(x, y));
	}
	
	@Override
	public void setPixel(int x, int y, Color c) {
		data.setRGB(x, y, c.getRGBInteger());
	}
	
	/*
	@Override
	public Color sample(double tu, double tv) {
		tu = tu % 1.0;
		tv = tv % 1.0;
		
		// bilinear filtering
		int x0 = (int)Math.floor((double)data.getWidth() * tu); 
		int y0 = (int)Math.floor((double)data.getHeight() * tv);
		int x1 = (x0 + 1) % data.getWidth();
		int y1 = (y0 + 1) % data.getHeight();
		
		Color c00 = Color.fromRGBInteger(data.getRGB(x0, y0));
		Color c10 = Color.fromRGBInteger(data.getRGB(x1, y0));
		Color c01 = Color.fromRGBInteger(data.getRGB(x0, y1));
		Color c11 = Color.fromRGBInteger(data.getRGB(x1, y1));
		
		double wu0 = ((double)data.getWidth() * tu) - (double)x0;
		double wv0 = ((double)data.getHeight() * tv) - (double)y0;
		
		Color sampled = c00.mult((1-wu0)*(1-wv0)).add(c10.mult(wu0*(1-wv0))).add(
				c01.mult((1-wu0)*wv0)).add(c11.mult(wu0*wv0));
		
		return sampled;
	}
	*/
}
