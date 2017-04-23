package module_TOUCHSCREEN;

import java.awt.Color;

public enum CalicoColors {
	BUTTON(Color.BLACK), MAINBACKGROUND(Color.BLACK), TEXTFIELDBACKGROUND(Color.BLACK), 
	PANELBACKGROUND(new Color(31, 134, 241)), FONT(new Color(224, 121, 14));
	
	private Color color;
	
	private CalicoColors(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
}
