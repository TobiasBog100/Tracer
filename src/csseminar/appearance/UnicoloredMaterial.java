package csseminar.appearance;

import csseminar.geometry.IntersectionDesc;

public class UnicoloredMaterial extends Material {


	Color c;
	
	public UnicoloredMaterial(MaterialType Material, double brechen, Color fa){
		super(Material, brechen);
		c = fa;
	}
	
	@Override
	public Color getSurfaceColor(IntersectionDesc id){
		return c;
	}
	
	
}
