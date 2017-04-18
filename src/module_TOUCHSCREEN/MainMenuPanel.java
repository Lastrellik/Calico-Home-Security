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

import module_PI.Raspberry_PI.main.SerialOutput;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainMenuPanel extends JPanel {
	/**
	 * Parameters are set to static so that other classes can make changes easily without getters and setters.
	 */
	
	/**
	 * @param asterisk used to simulate numbers being entered in for disarming alam.
	 * @param start_Logo used to display the animation for the logo
	 * @param top_Display used to display the menu you're on
	 * @param bottom_Display used display messages of actions
	 */
	private static StringBuilder asterisk = new StringBuilder();
	private static StartupAnimation start_Logo = new StartupAnimation();
	private static JTextArea top_Display = new JTextArea();
	private JTextArea bottom_Display;
	private SerialOutput output = new SerialOutput();
	
	/**
	 * @param timer used for animation of the start_Logo
	 */
	private Timer timer = new Timer();


	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		setBounds(0,0,388,480);
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		/**
		 * @param main_Panel houses all other panels to keep them organized.
		 * 		Set to two rows to create even spacing
		 */
		JPanel main_Panel = new JPanel();
		main_Panel.setBounds(12, 13, 364, 454);
		main_Panel.setBackground(Colors.getPanelColor());
		add(main_Panel);
		main_Panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		/**
		 * @param display_Panel handles displaying text area and the menu.
		 * 		Uses absolute to make adjustable menu, to meet certain heights.
		 */
		JPanel display_Panel = new JPanel();
		main_Panel.add(display_Panel);
		display_Panel.setLayout(null);

		/** 
		 * Used to house the start_Logo animation panel
		 */
		JPanel logo_Panel = new JPanel();
		logo_Panel.setBounds(0, 40, 364, 187);
		logo_Panel.setLayout(null);
		start_Logo.setBounds(0, 0, 364, 187);
		//logo_Panel.add(start_Logo);
		display_Panel.add(logo_Panel);
		
	
		top_Display.setEditable(false);
		top_Display.setColumns(10);
		top_Display.setBounds(0, 0, 364, 40);
		top_Display.setBackground(Colors.getPanelColor());
		display_Panel.add(top_Display);
		
		
		/**
		 * @param add_User_Inner_Buttons houses all buttons in 3 rows in grid layout.
		 * @param action_Button_Panel house action buttons in two columns to keep organzing in the top row of add_User_Innner_Buttons panel
		 */
		JPanel buttons_Panel = new JPanel();
		main_Panel.add(buttons_Panel);
		buttons_Panel.setBackground(Colors.getPanelColor());
		buttons_Panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		bottom_Display = new JTextArea();
		bottom_Display.setEditable(false);
		bottom_Display.setBackground(Colors.getPanelColor());
		buttons_Panel.add(bottom_Display);
		bottom_Display.setColumns(10);
		
		JPanel action_Button_Panel = new JPanel();
		buttons_Panel.add(action_Button_Panel);
		action_Button_Panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		/**
		 * @param Disarm used to disarm the alarm.
		 * @param Arm used to arm the alarm.
		 * @param Display the test menu.
		 */
		
		JButton button_Arm = new JButton("Arm");
		button_Arm.setBackground(Colors.getButtonColor());
		action_Button_Panel.add(button_Arm);
		JButton button_Disarm = new JButton("Disarm");
		button_Disarm.setBackground(Colors.getButtonColor());
		action_Button_Panel.add(button_Disarm);
		button_Disarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("Enter employee access code");
				output.sendDisarmPacket();
			}
		});
		
		JButton button_Test = new JButton("Test Screen");
		button_Test.setBackground(Colors.getButtonColor());
		buttons_Panel.add(button_Test);
		button_Test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Test_Menu_Visible();
			}
		});
		/**
		 * Arms the alarm.
		 */
		button_Arm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom_Display.setText("The alarm has been armed");
				output.sendArmPacket();
			}
		});
		/**
		 * Disarms the alarm.
		 */
		

	}
	
	/** 
	 * Used to append an * to the display field.
	 */
	public static void append_Text_Field(){
		asterisk.append("*");
		top_Display.setText(String.valueOf(asterisk));
	}
	/**
	 * Clears out the display field.
	 */
	public static void clear_Text_Field(){
		asterisk.setLength(0);
		top_Display.setText("");
	}
}

