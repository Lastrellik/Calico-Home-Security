package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.JButton;

public class KeypadButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private int buttonNumber;
	private char[] buttonCharacters;
	private Color buttonColor = Colors.getButtonColor();
	private int appropriateButtonCharacterIndex = 0;

	public KeypadButton(int buttonNumber, char[] buttonCharacters){
		this.buttonNumber = buttonNumber;
		this.buttonCharacters = buttonCharacters;
		buildButton();
		buildButtonMetadata();
	}

	private void buildButton(){
		setButtonText();
		setButtonColor();
	}
	
	private void buildButtonMetadata(){
		setFocusPainted(false);
	}
	
	private void setButtonText(){
		String htmlButtonText = "<html><center>" + buttonNumber + "<br>" + new String(buttonCharacters) + "</html>";
		this.setText(htmlButtonText);		
	}
	
	private void setButtonColor(){
		this.setBackground(this.buttonColor);
	}
	
	public char[] getButtonCharacters(){
		return buttonCharacters;
	}
	
	public int getButtonNumber(){
		return buttonNumber;
	}
	
	public void incrementCharacterSelection(){
		int maxIndex = buttonCharacters.length - 1;
		if(appropriateButtonCharacterIndex < maxIndex){
			appropriateButtonCharacterIndex++;
		} else {
			appropriateButtonCharacterIndex = 0;
		}
	}
	
	public void resetCharacterSelection() {
		appropriateButtonCharacterIndex = 0;
	}
	
	public char getCurrentCharacter(){
		return this.getButtonCharacters()[appropriateButtonCharacterIndex];
	}
}
