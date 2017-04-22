package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class KeypadTextField extends JPasswordField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StringBuilder textAreaContents = new StringBuilder();
	
	public KeypadTextField(){
		buildTextAreaMetadata();
	}
	
	private void buildTextAreaMetadata(){
		setEditable(false);
		setFont(new Font("Courier", Font.BOLD, 55));
		setEchoChar((char)0);
		setHorizontalAlignment(CENTER);
		setBackground(CalicoColors.TEXTFIELDBACKGROUND.getColor());
		setForeground(CalicoColors.FONT.getColor());
	}

	public String getContents(){
		return textAreaContents.toString();
	}
}
