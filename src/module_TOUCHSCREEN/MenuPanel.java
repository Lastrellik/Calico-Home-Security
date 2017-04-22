package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HomePanel homePanel = new HomePanel();
	AdminPanel adminPanel = new AdminPanel();

	public MenuPanel(){
		buildMetadata();
		addComponents();
	}
	
	private void buildMetadata(){
		setLayout(new CardLayout());
	}
	
	private void addComponents(){
		add(homePanel, "Home Panel");
		add(adminPanel, "Admin Panel");
	}
}
