package module_TOUCHSCREEN;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KeypadButtonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int NUM_OF_BUTTONS_ON_PANEL = 12;
	private final int NUM_OF_ROWS = 4;
	private final int NUM_OF_COLUMNS = 3;
	private final int MILLIS_BEFORE_BUTTON_CHAR_CHANGE = 500;
	private JPasswordField outputTextArea;
	private int buttonIterator = 0;
	private JButton[] keypadButtons;
	private boolean isPasswordMode = false;
	private boolean isTextMode = true;
	private long millisAtButtonPress;
	private KeypadButton previousPressedButton;

	public KeypadButtonPanel(JPasswordField keypadTextField){
		this.outputTextArea = keypadTextField;
		keypadButtons = new KeypadButton[NUM_OF_BUTTONS_ON_PANEL];
		buildMetadata();
		buildNumberPad();
	}
	
	private void buildMetadata(){
		setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS));
		setBackground(CalicoColors.PANELBACKGROUND.getColor());
	}
	
	private void buildNumberPad() {
		buildAndAddButtonsOneThroughNine();
		buildAndAddClearButton();
		buildAndAddZeroButton();
		buildAndAddEnterButton();
	}
	
	private void buildAndAddButtonsOneThroughNine(){
		int numOfButtons = 9;
		char[][] numberButtonCharacters = {
				{'_'},
				{'A', 'B', 'C'},
				{'D', 'E', 'F'},
				{'G', 'H', 'I'}, 
				{'J', 'K', 'L'},
				{'M', 'N', 'O'},
				{'P', 'Q', 'R', 'S'},
				{'T', 'U', 'V'},
				{'W', 'X', 'Y', 'Z'},
		};
		while(this.buttonIterator < numOfButtons){
			keypadButtons[this.buttonIterator] = 
					new KeypadButton(this.buttonIterator + 1, numberButtonCharacters[buttonIterator]);
			add(keypadButtons[this.buttonIterator]);
			addKeypadButtonActionListener(keypadButtons[this.buttonIterator++]);
		}
	}
	
	private void buildAndAddClearButton(){
		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(CalicoColors.BUTTON.getColor());
		clearButton.setForeground(CalicoColors.FONT.getColor());
		addClearButtonActionListener(clearButton);
		add(clearButton);
	}
	
	private void addClearButtonActionListener(JButton clearButton){
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTextField();
			}
		});	
	}
	
	private void clearTextField(){
		this.outputTextArea.setText("");
	}
	
	private void buildAndAddZeroButton(){
		KeypadButton zeroButton = new KeypadButton(0, new char[]{' '});
		add(zeroButton);
		addKeypadButtonActionListener(zeroButton);
	}
	
	private void buildAndAddEnterButton(){
		JButton enterButton = new JButton("Enter");
		enterButton.setBackground(CalicoColors.BUTTON.getColor());
		enterButton.setForeground(CalicoColors.FONT.getColor());
		enterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				togglePasswordMode();
			}
		});
		add(enterButton);
	}
	
	private void togglePasswordMode(){
		this.isPasswordMode = !this.isPasswordMode;
		enablePasswordMode(this.isPasswordMode);
	}
	
	private void addKeypadButtonActionListener(JButton keypadButtons2){
		keypadButtons2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KeypadButton buttonThatWasPressed = (KeypadButton) e.getSource();
				processKeypadButtonPress(buttonThatWasPressed);
				setSystemTimeThatButtonWasPressed();
			}
		});
	}
	
	private void setSystemTimeThatButtonWasPressed(){
		this.millisAtButtonPress = System.currentTimeMillis();
	}

	private void processKeypadButtonPress(KeypadButton buttonThatWasPressed) {
		if(!this.isTextMode){
			appendTextField(String.valueOf(buttonThatWasPressed.getButtonNumber()));
		} else {
			appendAppropriateCharacter(buttonThatWasPressed);
		}
		this.previousPressedButton = buttonThatWasPressed;
	}
	
	private void appendAppropriateCharacter(KeypadButton buttonThatWasPressed){
		char nextCharacter = buttonThatWasPressed.getCurrentCharacter();
		if(shouldUseNextButtonChar(buttonThatWasPressed)){
			buttonThatWasPressed.incrementCharacterSelection();
			nextCharacter = buttonThatWasPressed.getCurrentCharacter();
			deleteLastCharacterInTextField();
			appendTextField(Character.toString(nextCharacter));
		} else {
			buttonThatWasPressed.resetCharacterSelection();
			nextCharacter = buttonThatWasPressed.getCurrentCharacter();
			appendTextField(Character.toString(nextCharacter));
		}
	}
	
	
	private boolean shouldUseNextButtonChar(KeypadButton keypadButton){
		boolean sufficientTime = (System.currentTimeMillis() - this.millisAtButtonPress < MILLIS_BEFORE_BUTTON_CHAR_CHANGE);
		boolean sameKeyWasPressed = (keypadButton == this.previousPressedButton);
		return (sufficientTime && sameKeyWasPressed);
	}
	
	private void deleteLastCharacterInTextField(){
		int numOfCharsInTextField = outputTextArea.getPassword().length;
		String textFieldString = new String(outputTextArea.getPassword());
		outputTextArea.setText(textFieldString.substring(0, numOfCharsInTextField - 1));
	}
	
	private void appendTextField(String stringToAppend){
		this.outputTextArea.setText(new String(outputTextArea.getPassword()) + stringToAppend);
	}
	
	public void enablePasswordMode(boolean passwordMode){
		isPasswordMode = passwordMode;
		if(passwordMode){
			outputTextArea.setEchoChar('*');
		} else {	
			outputTextArea.setEchoChar((char)0);
		}
	}
	
	public boolean isPasswordMode(){
		return isPasswordMode;
	}
	
}
