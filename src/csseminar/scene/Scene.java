package csseminar.scene;



import csseminar.appearance.Color;
import csseminar.appearance.Material;
import csseminar.appearance.MaterialType;
import csseminar.appearance.UnicoloredMaterial;
import csseminar.geometry.*;
import csseminar.objects.PointLight;
import csseminar.objects.Primitive;

public class Scene {
	
	// The contents of the scene:
	Primitive[] sceneObjects;
	PointLight[] sceneLights;
	
	public static final int max_reflektion = 5;	
	
	// a target to render to:
	protected RenderTarget target;
	
	// the camera to use:
	protected Camera cam;
	
	// a progress observer
	protected ProgressObserver obs;
	
	// Setters
	
	
	public void setRenderTarget(RenderTarget rt) {
		target = rt;
	}
	
	public void setProgressObserver(ProgressObserver o) {
		obs = o;
	}
	
	// Intersections
	
	public void setup(int w, int h) {
		

		cam = new PerspectiveCamera(w, h, Math.PI * 0.5, 10.0); 
		
		sceneObjects = new Primitive[9];
		sceneLights = new PointLight[4];
		
		
		Material mDiffuse1 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.RED);
		Primitive wall1 = new Primitive (new Plane(new Vector(0,0,-1), -10.0), mDiffuse1);
		sceneObjects[0] = wall1;
		
		Material mDiffuse2 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.GREEN);
		Primitive wall2 = new Primitive (new Plane(new Vector(0,-1,0), -8.0), mDiffuse2);
		sceneObjects[1] = wall2;
		
		Material mDiffuse3 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.BLUE);
		Primitive wall3 = new Primitive (new Plane(new Vector(0,1,0), -8.0), mDiffuse3);
		sceneObjects[2] = wall3;
		
		Material mDiffuse4 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.ORANGE);
		Primitive wall4 = new Primitive (new Plane(new Vector(-1,0,0), -10.0), mDiffuse4);
		sceneObjects[3] = wall4;
		
		Material mDiffuse5 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.WHITE);
		Primitive wall5 = new Primitive (new Plane(new Vector(1,0,0), -10.0), mDiffuse5);
		sceneObjects[4] = wall5;
		
		Material mDiffuse6 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE, 1.0, Color.ORANGE);
		Primitive ball1 = new Primitive (new Sphere(new Vector(4,-5,3),3), mDiffuse6);
		sceneObjects[5] = ball1;
		
		Material mDiffuse7 = new UnicoloredMaterial (MaterialType.MAT_DIFFUSE,1.0, Color.YELLOW);
		Primitive ball2 = new Primitive (new Sphere(new Vector(-3,-4,6),4), mDiffuse7);
		sceneObjects[6] = ball2;
		
		Material mDiffuse8 = new UnicoloredMaterial (MaterialType.MAT_MIRROR,1.0, Color.MAGENTA);
		Primitive ball3 = new Primitive (new Sphere(new Vector(3,4,5),3), mDiffuse8);
		sceneObjects[7] = ball3;		
		
		Material mDiffuse9 = new UnicoloredMaterial (MaterialType.MAT_TRANSMISSIVE,2.8, Color.WHITE);
		Primitive ball4 = new Primitive (new Sphere(new Vector(-2,3,3),2), mDiffuse9);
		sceneObjects[8] = ball4;
		
		
		
		PointLight light1 = new PointLight(new Vector(-2,-6,0),6);
		sceneLights[0] = light1;
		
		PointLight light2 = new PointLight(new Vector(7,6,7),8);
		sceneLights[1] = light2;
		
		
		PointLight light3 = new PointLight(new Vector(0,0,3),8);
		sceneLights[2] = light3;
		
		PointLight light4 = new PointLight(new Vector(-4,0,7),8);
		sceneLights[3] = light4;
		
		/*
		sceneObjects = new Primitive[9];
		sceneLights = new PointLight[3];
		
		// build a cornell box
		
		Material mMatteRed = new UnicoloredMaterial(MaterialType.MAT_DIFFUSE, 1.0, Color.RED);
		Material mMatteWhite = new UnicoloredMaterial(MaterialType.MAT_DIFFUSE, 1.0, Color.WHITE);
		Material mMatteGreen = new UnicoloredMaterial(MaterialType.MAT_DIFFUSE, 1.0, Color.DARKGREEN);
		Material mMirroringYellow = new UnicoloredMaterial(MaterialType.MAT_MIRROR, 1.0, Color.YELLOW);
		Material mMirroringCyan = new UnicoloredMaterial(MaterialType.MAT_MIRROR, 1.0, Color.CYAN);
		Material mMirroringMagenta = new UnicoloredMaterial(MaterialType.MAT_MIRROR, 1.0, Color.MAGENTA);
		
		Material mTransmissiveWhite = new UnicoloredMaterial(MaterialType.MAT_TRANSMISSIVE, 1.3, Color.WHITE);
		
		
		// left
		sceneObjects[0] = new Primitive(new Plane(new Vector(1,0,0), -10.0), mMatteRed);
		
		// right
		sceneObjects[1] = new Primitive(new Plane(new Vector(-1,0,0), -10.0), mMatteGreen);
		
		// back
		sceneObjects[2] = new Primitive(new Plane(new Vector(0,0,-1), -10.0), mMatteWhite);
		
		// top
		sceneObjects[3] = new Primitive(new Plane(new Vector(0,-1,0), -8.0), mMatteWhite);
		
		// bottom
		sceneObjects[4] = new Primitive(new Plane(new Vector(0,1,0), -8.0), mMatteWhite);

		// balls
		sceneObjects[5] = new Primitive(new Sphere(new Vector(-6.5,-4,6),3), mMirroringYellow);
		sceneObjects[6] = new Primitive(new Sphere(new Vector( 0,-4,6),3), mMirroringMagenta);
		sceneObjects[7] = new Primitive(new Sphere(new Vector( 6.5,-4,6),3), mMirroringCyan);
		
		sceneObjects[8] = new Primitive(new Sphere(new Vector( 2,-2,-3), 3.5), mTransmissiveWhite);
		
		/// now the LIGHTS:
		sceneLights[0] = new PointLight(new Vector(-5.0,2.0,0.0),20.0);
		sceneLights[1] = new PointLight(new Vector(9.0,-7.0,9.0),10.0);
		sceneLights[2] = new PointLight(new Vector(9.0,-7.0,0.0),10.0);
		*/
		
		
		
	}
	
	
	
	// Rendering
	
	public
	Color renderSample(double x, double y) {
		// get ray for this pixel
		Ray r = cam.getRayForPixel(x, y);

		return renderRay(r,1);
	}
	
	
	
	/*
	public Color renderPixel(double x,double y) {
	Color c = renderSample(x,y);
	return c;
	}
	*/
	
	public
	Color renderPixel(double x,double y) {
	Color cAbove = renderSample(x,y-0.2).mult(0.125);
	Color cBelow = renderSample(x,y+0.2).mult(0.125);
	Color cLeft = renderSample(x-0.2,y).mult(0.125);
	Color cRight = renderSample(x+0.2,y).mult(0.125);
	Color cCenter = renderSample(x,y).mult(0.5);
	return cAbove.add(cBelow).add(cLeft).add(cRight).add(cCenter);
	}

	
	
	
	
	
	
	static class PrimitiveIntersection {
		IntersectionDesc nearestIntersection;
		Primitive intersectedObject;
	}
	
	public PrimitiveIntersection castRay(Ray r) {
		
		PrimitiveIntersection pi = new PrimitiveIntersection();
		pi.nearestIntersection = null;
		pi.intersectedObject = null;
		
		for(int i=0;i<sceneObjects.length;i++) {
			// check object #i
			IntersectionDesc id = sceneObjects[i].intersects(r);
			
			// if there is no intersection, check next object
			if(id == null)
				continue;
			
			// is this intersection nearer than the previous one (is there a previous one?)
			if(pi.nearestIntersection != null) {
				if(id.t < pi.nearestIntersection.t) {
					pi.nearestIntersection = id;
					pi.intersectedObject = sceneObjects[i];
				}
			} else {
				pi.nearestIntersection = id;
				pi.intersectedObject = sceneObjects[i];
			}
		}
		
		if (pi.nearestIntersection == null)
			return null;
		
		return pi;
	}
	
/*	
	public Color renderRay(Ray r) {
		

		IntersectionDesc nearest = null; //// Vorsicht naehe und ortsbestimmung jetzt nearest usw. Ä: 10.01
		int nearest_i = -1;
		
		for(int i=0;i<sceneObjects.length ;i++) {

			IntersectionDesc id = sceneObjects[i].intersects(r);
			

			if(id == null)
				continue;

			if(nearest != null) {
				if(id.t < nearest.t) {
					nearest = id;
					nearest_i = i;
				}
			} else {
				nearest = id;
				nearest_i = i;
			}
		}
		
		
		if (nearest == null){
			return Color.BLACK;
		}
			
			Color cAccum = new Color(0,0,0);
			Material matObject = sceneObjects[nearest_i].getMaterial();
			for (int j=0;j<sceneLights.length;j++){
				PointLight currentLight = sceneLights[j];
			
			Color col = matObject.shadelight(nearest,currentLight);
			cAccum=cAccum.add(col);

			}
			return cAccum;
	}
	
*/
	
	public double testShadowing(IntersectionDesc id, PointLight pl) {
		
		// build shadow ray from intersection point to light source
		Vector vIP = id.position.add(id.normal.verlaengerung(1e-5));
		Vector vToLight = pl.getPosition().sub(vIP);
		double distToLight = vToLight.laenge();
		Ray shadowRay = new Ray(vIP, vToLight.verlaengerung(1.0/distToLight));
		
		// test for intersections
		PrimitiveIntersection pi = castRay(shadowRay);
		
		// no intersection?
		if(pi == null)
			return 1.0;
		
		// or no intersection between object and light source?
		if(pi.nearestIntersection.t >  distToLight)
			return 1.0;
		
		// else: shadowed by another object
		return 0.0;
	}
	
	public Color renderRay(Ray r, int rayDepth) {
		
		// check if ray depth is too deep
		if (rayDepth > max_reflektion)
			return Color.BLACK;
		
		//-------------------------------------
		// RAYCASTING
		
		// shoot ray into scene, find closest t
		PrimitiveIntersection pi = castRay(r);
		
		// now nearest contains the nearest intersection or is null if there is none
		if (pi == null)
			return Color.BLACK;
		
		//---------------------------------
		// SHADING
		
		// the material of the hit object
		Material matHitObject = pi.intersectedObject.getMaterial();
		
		// if this is a diffuse material, shade every light
		Color colHitPoint = Color.BLACK;
		
		if (matHitObject.getMaterialKind() == MaterialType.MAT_DIFFUSE) {
			for(int j=0;j<sceneLights.length;j++) {
				
				// test shadowing
				double shadowFactor = testShadowing(pi.nearestIntersection, sceneLights[j]);
				
				// compute shading
				Color colCurrentLight = matHitObject.shadeDiffuse(pi.nearestIntersection,sceneLights[j]);
				
				// combine
				colHitPoint = colHitPoint.add(colCurrentLight.mult(shadowFactor));
			}
		}
		//--------------------------------
		// RECURSION
		
		else if (matHitObject.getMaterialKind() == MaterialType.MAT_MIRROR) {
			colHitPoint = matHitObject.shadeMirror(pi.nearestIntersection, this, rayDepth);
		}
		
		else /* if matHitObject.getMaterialKind() == MaterialType.MAT_TRANSMISSIVE */ {
			colHitPoint = matHitObject.shadeTransmissive(pi.nearestIntersection, this, rayDepth);
		}
		
		return colHitPoint.clamp();
	}


		
	
	
	public void renderScene() {
		
		assert cam != null;
		assert target != null;
		
		if(obs != null) {
			obs.start();
			for (int y=0;y<target.getHeight();y++) {
				for (int x=0;x<target.getWidth();x++) {						
					Color c = renderPixel((double)x,(double)y);
					target.setPixel(x, y, c);
					obs.progress((double)y / (double)target.getHeight());
				}
			}			
			obs.finish();
		} else {
			for (int y=0;y<target.getHeight();y++) {
				for (int x=0;x<target.getWidth();x++) {
					Color c = renderPixel((double)x,(double)y);
					target.setPixel(x, y, c);
				}
			}
		}
	}
}