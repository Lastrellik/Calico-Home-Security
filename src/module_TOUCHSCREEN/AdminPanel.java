package module_TOUCHSCREEN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommandButton[] commandButtons;
	private JButton homeButton;
	private MenuPanel cardParent;
	private CardLayout cardLayout;
	
	public AdminPanel(MenuPanel cardParent){
		this.cardParent = cardParent;
		this.cardLayout = (CardLayout) cardParent.getLayout();
		buildMetadata();
		buildCommandButtons();
		buildHomeButton();
		addButtonsToPanel();
	}
	
	private void buildMetadata(){
		setLayout(new GridLayout(5, 2));
	}
	
	private void buildCommandButtons(){
		commandButtons = new CommandButton[CommandType.values().length];
		for(int i = 0; i < CommandType.values().length; i++){
			commandButtons[i] = new CommandButton(CommandType.values()[i].toString(), CommandType.values()[i]);
		}
	}
	
	private void buildHomeButton(){
		homeButton = new JButton("Home");
		homeButton.setBackground(CalicoColors.BUTTON.getColor());
		homeButton.setForeground(CalicoColors.FONT.getColor());
		homeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardParent, "Home Panel");
			}
			
		});
	}
	
	private void addButtonsToPanel(){
		for(CommandButton c : commandButtons) add(c);
		add(homeButton);
	}
}
