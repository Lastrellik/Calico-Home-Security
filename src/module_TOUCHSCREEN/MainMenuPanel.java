package module_TOUCHSCREEN;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


import java.util.Timer;
import java.util.TimerTask;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainMenuPanel extends JPanel {
	private static StringBuilder asterisk = new StringBuilder();
	private static StartupAnimation start_Logo = new StartupAnimation();
	private static JTextArea top_Display = new JTextArea();
	
	private JTextField test_Text;
	/**
	 * @param timer used for creating a time event
	 */
	private Timer timer = new Timer();
	private JTextArea bottom_Display;

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setBounds(0,0,388,480);
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		JPanel main_Menu_Inner = new JPanel();
		main_Menu_Inner.setBounds(12, 13, 364, 454);
		main_Menu_Inner.setBackground(Colors.getPanelColor());
		add(main_Menu_Inner);
		main_Menu_Inner.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel main_Menu_Display = new JPanel();
		main_Menu_Inner.add(main_Menu_Display);
		main_Menu_Display.setLayout(null);

		
		JPanel logo_Panel = new JPanel();
		logo_Panel.setBounds(0, 40, 364, 187);
		logo_Panel.setLayout(null);
		start_Logo.setBounds(0, 0, 364, 187);
		logo_Panel.add(start_Logo);
		main_Menu_Display.add(logo_Panel);
		
	
		top_Display.setEditable(false);
		top_Display.setColumns(10);
		top_Display.setBounds(0, 0, 364, 40);
		top_Display.setBackground(Colors.getPanelColor());
		main_Menu_Display.add(top_Display);
		
		
		
		JPanel main_Menu_Buttons = new JPanel();
		main_Menu_Inner.add(main_Menu_Buttons);
		main_Menu_Buttons.setBackground(Colors.getPanelColor());
		main_Menu_Buttons.setLayout(new GridLayout(3, 1, 0, 0));
		
		bottom_Display = new JTextArea();
		bottom_Display.setEditable(false);
		bottom_Display.setBackground(Colors.getPanelColor());
		main_Menu_Buttons.add(bottom_Display);
		bottom_Display.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		main_Menu_Buttons.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_Disarm = new JButton("Disarm");
		button_Disarm.setBackground(Colors.getButtonColor());
		panel_2.add(button_Disarm);
		
		JButton button_Arm = new JButton("Arm");
		button_Arm.setBackground(Colors.getButtonColor());
		panel_2.add(button_Arm);
		
		JButton button_Test = new JButton("Test Screen");
		button_Test.setBackground(Colors.getButtonColor());
		main_Menu_Buttons.add(button_Test);
		button_Test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Test_Menu_Visible();
			}
		});
		button_Arm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("The alarm has been armed");
			}
		});
		button_Disarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("Enter employee access code");
			}
		});
		

	}
	public static void append_Text_Field(){
		asterisk.append("*");
		top_Display.setText(String.valueOf(asterisk));
	}
	public static void clear_Text_Field(){
		asterisk.setLength(0);
		top_Display.setText("");
	}
}

