package csseminar;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import csseminar.scene.*;

public class SimpleRaytracer implements ProgressObserver {
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	private int previous_pc_complete;
	
	private JFrame imageFrame;
	private JPanel imagePanel; 
	
	private Image renderTarget;
	private Scene scene;
	
	int width;
	int height;

	public static void main(String[] args) {
		
		int w = DEFAULT_WIDTH, h = DEFAULT_HEIGHT;
		
		// take width and height cmdline arguments if given
		if (args.length >= 2) {
			try {
				w = Integer.parseInt(args[0]);
				h = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				System.err.println("The arguments given have to be numbers!");
				System.exit(-1);
			}
		}
		
		// create a raytracer and run it
		SimpleRaytracer rt = new SimpleRaytracer(w,h);
		rt.setup(w,h);
		rt.startRender();
	}
	
	public SimpleRaytracer(int w, int h) {
		width = w;
		height = h;
		
		// setup GUI
		imageFrame = new JFrame("SimpleRaytracer");
		imagePanel = new JPanel() {
			private static final long serialVersionUID = 3620228189861344073L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (renderTarget != null) {
					renderTarget.drawOnto(g);
				}
			}
		};
		
		imagePanel.setPreferredSize(new Dimension(width, height));
		
		imageFrame.add(imagePanel);
		imageFrame.pack();
		imageFrame.setLocationRelativeTo(null);
		imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageFrame.setVisible(true);
	}
	
	public void setup(int w, int h) {
		
		// setup rendertarget and scene
		renderTarget = new Image(width,height);
		
		scene = new Scene();
		scene.setup(w,h);
		scene.setRenderTarget(renderTarget);
	}
	
	public void startRender() {
		scene.setProgressObserver(this);
		scene.renderScene();
	}

	@Override
	public void start() {
		previous_pc_complete = 0;
	}

	@Override
	public void progress(double pccomplete) {
		
		// round to two digits
		final int pc = (int)(pccomplete * 100);
		
		// if there is a change:
		if(previous_pc_complete != pc) {
		
			// change window title to "Rendering: XX%"
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					imageFrame.setTitle("Rendering: " + pc + "%");
					imagePanel.repaint();
				}
			});
			
			previous_pc_complete = pc;
		}
	}

	@Override
	public void finish() {
		
		// repaint a last time and change title to "Saving Image"
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				imageFrame.setTitle("Saving image...");
				imagePanel.repaint();
			}
		});
		
		// save image
		renderTarget.saveToPNG("./render.png");
		
		// change title to "Finished."
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				imageFrame.setTitle("Finished.");
			}
		});
	}

}
