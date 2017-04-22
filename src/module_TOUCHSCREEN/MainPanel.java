package module_TOUCHSCREEN;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
//import java.awt.event.*;


public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final MenuPanel menuPanel = new MenuPanel();
	private final KeypadPanel keypadPanel = new KeypadPanel();
	
	public MainPanel() {
		buildMainPanelMetadata();
		addComponents();
	}
	
	private void buildMainPanelMetadata(){
		setLayout(new GridLayout(0, 2, 10, 10));
		setBackground(CalicoColors.MAINBACKGROUND.getColor());
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	private void addComponents(){
		add(menuPanel);
		add(keypadPanel);
	}
}
