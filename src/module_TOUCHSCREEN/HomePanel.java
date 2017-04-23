package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class HomePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	LogoPanel logoPanel = new LogoPanel("src/module_TOUCHSCREEN/logo.png");
	HomeAlarmPanel homeAlarmPanel;
	private MenuPanel cardParent;

	public HomePanel(MenuPanel cardParent){
		this.cardParent = cardParent;
		buildHomePanel();
		addComponents();
	}
	
	private void buildHomePanel(){
		setLayout(new BorderLayout());
		homeAlarmPanel = new HomeAlarmPanel(cardParent);
	}
	
	private void addComponents(){
		add(logoPanel, BorderLayout.NORTH);
		add(homeAlarmPanel, BorderLayout.SOUTH);
	}
}
