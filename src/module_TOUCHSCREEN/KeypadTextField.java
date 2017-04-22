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
		setEchoChar('*');
		setHorizontalAlignment(CENTER);
	}

	public String getContents(){
		return textAreaContents.toString();
	}
}
