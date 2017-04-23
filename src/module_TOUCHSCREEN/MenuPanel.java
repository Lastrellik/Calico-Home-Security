package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HomePanel homePanel;
	private AdminPanel adminPanel;

	public MenuPanel(){
		buildMetadata();
		buildCards();
		addComponents();
	}
	
	private void buildMetadata(){
		setLayout(new CardLayout());
		setBackground(CalicoColors.PANELBACKGROUND.getColor());
	}
	
	private void buildCards(){
		homePanel = new HomePanel(this);
		adminPanel = new AdminPanel(this);
	}
	
	private void addComponents(){
		add(homePanel, "Home Panel");
		add(adminPanel, "Admin Panel");
	}
}
