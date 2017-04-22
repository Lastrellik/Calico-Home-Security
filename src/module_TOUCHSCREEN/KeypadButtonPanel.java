package module_TOUCHSCREEN;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KeypadButtonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int NUM_OF_BUTTONS_ON_PANEL = 12;
	private final int NUM_OF_ROWS = 4;
	private final int NUM_OF_COLUMNS = 3;
	private JPasswordField outputTextArea;
	private int buttonIterator = 0;
	private JButton[] keypadButtons;
	private boolean isPasswordMode = false;

	public KeypadButtonPanel(JPasswordField keypadTextField){
		this.outputTextArea = keypadTextField;
		keypadButtons = new KeypadButton[NUM_OF_BUTTONS_ON_PANEL];
		buildPanelMetadata();
		buildNumberPad();
	}
	
	private void buildPanelMetadata(){
		setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS));
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
				{' '},
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
		clearButton.setBackground(Colors.getButtonColor());
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
		enterButton.setBackground(Colors.getButtonColor());
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
				appendTextField(String.valueOf(((KeypadButton) e.getSource()).getButtonNumber()));
			}
		});
	}
	
	private void appendTextField(String stringToAppend){
		this.outputTextArea.setText(this.outputTextArea.getText() + stringToAppend);
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
