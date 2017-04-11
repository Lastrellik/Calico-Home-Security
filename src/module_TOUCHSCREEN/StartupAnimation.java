package module_TOUCHSCREEN;

import java.awt.Color;

import javax.swing.JPanel;

public class StartupAnimation extends JPanel{
	
	public StartupAnimation(){
		setBackground(Colors.getPanelColor());
		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(0, 39, 364, 188);

	}
}
