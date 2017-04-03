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

public class MainMenuPanel extends JPanel {
	
	private JTextField bottom_Display;
	private static JTextField top_Display;
	private static StringBuilder asterisk = new StringBuilder();

	private JTextField test_Text;
	/**
	 * @param timer used for creating a time event
	 */
	private Timer timer = new Timer();

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setBounds(0,0,388,480);
		setLayout(null);
		
		JPanel main_Menu_Inner = new JPanel();
		main_Menu_Inner.setBounds(12, 13, 364, 307);
		add(main_Menu_Inner);
		main_Menu_Inner.setLayout(null);
		
		bottom_Display = new JTextField();
		bottom_Display.setEditable(false);
		bottom_Display.setBounds(0, 267, 364, 40);
		main_Menu_Inner.add(bottom_Display);
		bottom_Display.setColumns(10);
		
		top_Display = new JTextField();
		top_Display.setBackground(SystemColor.control);
		top_Display.setEditable(false);
		top_Display.setBounds(0, 0, 364, 40);
		main_Menu_Inner.add(top_Display);
		top_Display.setColumns(10);
		
		JPanel main_Menu_Buttons = new JPanel();
		main_Menu_Buttons.setBounds(12, 318, 364, 149);
		add(main_Menu_Buttons);
		main_Menu_Buttons.setLayout(null);
		
		JButton button_Arm = new JButton("Arm");
		button_Arm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("The alarm has been armed");
			}
		});
		button_Arm.setBounds(0, 0, 187, 77);
		main_Menu_Buttons.add(button_Arm);
		
		JButton button_Test = new JButton("Test Screen");
		button_Test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Test_Menu_Visible();
			}
		});
		button_Test.setBounds(0, 76, 364, 71);
		main_Menu_Buttons.add(button_Test);
		
		JButton button_Disarm = new JButton("Disarm");
		button_Disarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("Enter employee access code");
			}
		});
		button_Disarm.setBounds(182, 0, 182, 77);
		main_Menu_Buttons.add(button_Disarm);
		

	}
	public static void append_Text_Field(){
		asterisk.append("*");
		top_Display.setText(String.valueOf(asterisk));
	}
		
}

