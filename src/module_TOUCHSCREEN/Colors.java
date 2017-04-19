package module_TOUCHSCREEN;

import java.awt.Color;

public class Colors {
	
	/**
	 * Parameters are set to static so that other classes can make changes easily without getters and setters.
	 */
	
	/**
	 * @param used to set all panel colors at once
	 * @param used to set all button colors at once
	 */
	private static Color panel_Color = new Color(58,86,127);
	private static Color button_Color = new Color(255,255,255);
	
	public Colors(){
		//empty constructor
	}
	/**
	 * Returns the panel_Color.
	 * @return
	 */
	public static Color getPanelColor(){
		return panel_Color;
	}
	/**
	 * Returns the button_Color.
	 * @return
	 */
	public static Color getButtonColor(){
		return button_Color;
	}
}
