package csseminar.appearance;


import csseminar.geometry.IntersectionDesc;
import csseminar.geometry.Ray;
import csseminar.geometry.Vector;
import csseminar.objects.PointLight;
import csseminar.scene.Scene;

public abstract class Material {


	public static final double REFRACTION_INDEX_AIR = 1.00029;

	private MaterialType type;
	private double refraction_index;
	
	public Material(MaterialType Material, double brechen) {
		type = Material;
		refraction_index = brechen;
	}
	
	public Color shadeDiffuse(IntersectionDesc id, PointLight light) {
	
		Color C = getSurfaceColor(id);

		Vector l = light.getPosition().sub(id.position);
		double d = l.laenge();
		l = l.verlaengerung(1.0 / d);
		Color I_D = C.mult(clamp(l.skmultivektor(id.normal)));
				
		double denom = d / light.getRadius() + 1.0;
		double f_atten = 1.0 / (denom * denom);
		
		Color col = I_D.mult(f_atten);

		return col;
	}

	public Color shadeMirror(IntersectionDesc id, Scene s, int rayDepth) {
		// compute reflected ray
		Vector wo = id.originalRay.richtung.verlaengerung(-1.0);
		Vector oriented_n = (wo.skmultivektor(id.normal) < 0.0) ?	id.normal.verlaengerung(-1.0) : id.normal;
		Vector wi = wo.reflect(oriented_n);
		Ray rReflect = new Ray(id.position.add(wi.verlaengerung(1e-5)), wi);
		
		// render that ray
		Color reflectedRayColor = s.renderRay(rReflect, rayDepth+1);  
		
		return getSurfaceColor(id).mult(reflectedRayColor);
	}
	
	public Color shadeTransmissive(IntersectionDesc id, Scene s, int rayDepth) {
		
		Color cResult;
		Color cSurface = getSurfaceColor(id);
				
		// compute reflected ray
		Vector wo = id.originalRay.richtung.verlaengerung(-1.0);
		boolean entering_object = wo.skmultivektor(id.normal) >= 0.0;  
		Vector oriented_n = (entering_object) ? id.normal : id.normal.verlaengerung(-1.0); // normal that points into the hemisphere that contains wo
		Vector wi_reflected = wo.reflect(oriented_n);
		
		Ray rReflect = new Ray(id.position.add(wi_reflected.verlaengerung(1e-5)), wi_reflected);
		
		Color reflectedRayColor = s.renderRay(rReflect, rayDepth+1);
		
		// compute refracted ray
		double cosTheta_o = wo.skmultivektor(oriented_n);
		double eta_i, eta_o;
		if (entering_object) {
			eta_o = Material.REFRACTION_INDEX_AIR;
			eta_i = refraction_index;
		} else {
			eta_o = refraction_index;
			eta_i = Material.REFRACTION_INDEX_AIR;
		}
		double ratio = eta_o / eta_i;
		double cos2Theta_i = 1.0 - ratio * ratio * (1.0 - cosTheta_o * cosTheta_o);
		
		// handle internal reflection
		if (cos2Theta_i < 0.0) {
			// we have total internal reflection
			cResult = reflectedRayColor;
		} else {
			// we have reflection and refraction
			double cosTheta_i = Math.sqrt(cos2Theta_i);
			Vector wi_refracted = wo.verlaengerung(-ratio).add(oriented_n.verlaengerung(ratio * cosTheta_o - cosTheta_i));
			
			Ray rRefract = new Ray(id.position.add(wi_refracted.verlaengerung(1e-5)), wi_refracted);
			
			Color refractedRayColor = s.renderRay(rRefract, rayDepth+1);
			
			// compute fresnel coefficient
			double fresnel = computeFresnelReflectance(eta_i, eta_o, cosTheta_i, cosTheta_o);
			
			cResult = reflectedRayColor.mult(fresnel).add(refractedRayColor.mult(1.0 - fresnel));
		}
		
		return cResult.mult(cSurface);
	}
	
	static private double computeFresnelReflectance(double eta_i, double eta_o, double cosTheta_i, double cosTheta_o) {

		double r_parl = (eta_o * cosTheta_i - eta_i * cosTheta_o) / (eta_o * cosTheta_i + eta_i * cosTheta_o);
		double r_perp = (eta_i * cosTheta_i - eta_o * cosTheta_o) / (eta_i * cosTheta_i + eta_o * cosTheta_o);
		
		return 0.5*(r_parl*r_parl + r_perp*r_perp);
	}
	

	
	
	
	
	public MaterialType getMaterialKind() {
		return type;
	}
	
	public double getRefractionIndex() {
		return refraction_index;
	}
	
	
	public abstract Color getSurfaceColor(IntersectionDesc id);

	static private double clamp(double t) {
		if (t < 0){
			return 0;
		}
		else{
		return t;
	}	
	}
		

}
