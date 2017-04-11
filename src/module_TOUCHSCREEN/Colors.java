package module_TOUCHSCREEN;

import java.awt.Color;

public class Colors {
	
	private static Color panel_Color = new Color(58,86,127);
	private static Color button_Color = new Color(255,255,255);
	
	public Colors(){
		//empty constructor
	}
	
	public static Color getPanelColor(){
		return panel_Color;
	}
	public static Color getButtonColor(){
		return button_Color;
	}
}
