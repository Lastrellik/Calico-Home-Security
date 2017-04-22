package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class AdminPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommandButton[] commandButtons;
	
	public AdminPanel(){
		buildMetadata();
		buildCommandButtons();
		addButtonsToPanel();
	}
	
	private void buildMetadata(){
		setLayout(new FlowLayout());
	}
	
	private void buildCommandButtons(){
		commandButtons = new CommandButton[CommandType.values().length];
		for(int i = 0; i < CommandType.values().length; i++){
			commandButtons[i] = new CommandButton(CommandType.values()[i].toString(), CommandType.values()[i]);
		}
	}
	
	private void addButtonsToPanel(){
		for(CommandButton c : commandButtons) add(c);
	}
}
