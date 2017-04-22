package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;

public class HomePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	LogoPanel logoPanel = new LogoPanel("src/module_TOUCHSCREEN/logo.png");
	HomeAlarmPanel homeAlarmPanel = new HomeAlarmPanel();

	public HomePanel(){
		buildMetadata();
		addComponents();
	}
	
	private void buildMetadata(){
		setLayout(new BorderLayout());
	}
	
	private void addComponents(){
		add(logoPanel, BorderLayout.NORTH);
		add(homeAlarmPanel, BorderLayout.SOUTH);
	}
}
