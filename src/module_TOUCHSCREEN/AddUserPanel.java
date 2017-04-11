package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class AddUserPanel extends JPanel {
	private static StringBuilder create_User = new StringBuilder();
	private static int count = 0;
	private static JTextArea user_List = new JTextArea();
	

	private int min = 0;
	private int max = 9;
	private static boolean button_Pressed = false;
	private static boolean create_True = false;
	private JTextArea menu_Name;
	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		setBounds(0,0,388,480);
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 364, 454);
		add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);
		user_List.setBounds(0, 39, 364, 188);
		panel_3.add(user_List);
		user_List.setEditable(false);
		
		menu_Name = new JTextArea();
		menu_Name.setEditable(false);
		menu_Name.setBounds(0, 0, 364, 40);
		panel_3.add(menu_Name);
		menu_Name.setColumns(10);
		
		JPanel add_User_Inner_Buttons = new JPanel();
		panel.add(add_User_Inner_Buttons);
		add_User_Inner_Buttons.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 364, 227);
		add_User_Inner_Buttons.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_Del = new JButton("Del");
		button_Del.setBackground(Colors.getButtonColor());
		panel_2.add(button_Del);
		
		JButton button_Select = new JButton("Select");
		button_Select.setBackground(Colors.getButtonColor());
		panel_2.add(button_Select);
		
		JButton button_Create = new JButton("Create User");
		button_Create.setBackground(Colors.getButtonColor());
		panel_1.add(button_Create);
		button_Create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Need while loop here that continues to create a new
				//code as long as it doesn't exist already
				Random random = new Random();
				int p1 = random.nextInt(max - min + 1)+0;
				int p2 = random.nextInt(max - min + 1)+0;
				int p3 = random.nextInt(max - min + 1)+0;
				int p4 = random.nextInt(max - min + 1)+0;
				String passcode = String.valueOf(p1) + String.valueOf(p2)
				+String.valueOf(p3)+String.valueOf(p4);
				
				user_List.setText("The following user has now been created\n"+
				"User Name: "+create_User+"\nUser Pass Code: "+passcode);
				createTrue();
				
			}
		});
		
		JButton main_Screen = new JButton("Main Menu");
		main_Screen.setBackground(Colors.getButtonColor());
		panel_1.add(main_Screen);
		main_Screen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
		button_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Current Status Pressed: " + button_Pressed);
				if(create_User.length() > 0 && button_Pressed == true){ 
					create_User.setLength(create_User.length()+1);
					pressedFalse();
				}
				System.out.println(create_User.length());
			}
		});
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(create_User.length() > 0){
					System.out.println("Current Status Pressed in Del: "+button_Pressed);
					if(button_Pressed == false){
						create_User.setLength(create_User.length()-1);
						pressedTrue();
					}
					create_User.setLength(create_User.length()-1);
					System.out.println(create_User.length());
					user_List.setText(String.valueOf(create_User));
				}
			}
		});
	}
	
	public static void append_Create_User(char letter){
		if(create_True == true){
			clear_Create_User();
			createFalse();
		}
		create_User.append(letter);
		user_List.setText(String.valueOf(create_User));
		System.out.println(create_User.length());
	}
	public static void remove_Last_Create_User(){
		create_User.setLength(create_User.length()-1);
	}
	public static void clear_Create_User(){
		create_User.setLength(0);
		user_List.setText(String.valueOf(create_User));
	}
	public static int get_Create_User_Length(){
		return create_User.length();
	}
	public static void pressedTrue(){
		if(button_Pressed == false) button_Pressed = true;
	}
	public static void pressedFalse(){
		if(button_Pressed == true) button_Pressed = false;
	}
	public static void createTrue(){
		if(create_True == false) create_True = true;
	}
	public static void createFalse(){
		if(create_True == true) create_True = false;
	}
	public static boolean getButtonPressed(){
		return button_Pressed;
	}
	public static boolean getCreateTrue(){
		return create_True;
	}
}
