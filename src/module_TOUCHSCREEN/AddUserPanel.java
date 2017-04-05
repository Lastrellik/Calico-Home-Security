package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddUserPanel extends JPanel {
	private static StringBuilder create_User = new StringBuilder();
	private static int count = 0;
	private static JTextArea user_List = new JTextArea();

	private int min = 0;
	private int max = 9;
	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		setBounds(0,0,388,480);
		setLayout(null);
		
		JPanel add_User_Inner_Display = new JPanel();
		add_User_Inner_Display.setBounds(12, 35, 364, 210);
		add(add_User_Inner_Display);
		add_User_Inner_Display.setLayout(null);
		user_List.setEditable(false);
		
		
		user_List.setBounds(0, 0, 364, 210);
		add_User_Inner_Display.add(user_List);
		
		JPanel add_User_Inner_Buttons = new JPanel();
		add_User_Inner_Buttons.setLayout(null);
		add_User_Inner_Buttons.setBounds(12, 245, 364, 222);
		add(add_User_Inner_Buttons);
		
		JButton button_Create = new JButton("Create User");
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
			}
		});
		button_Create.setBounds(0, 72, 364, 78);
		add_User_Inner_Buttons.add(button_Create);
		
		JButton main_Screen = new JButton("Main Menu");
		main_Screen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
		main_Screen.setBounds(0, 148, 364, 74);
		add_User_Inner_Buttons.add(main_Screen);
		
		JButton button_Del = new JButton("Del");
		button_Del.setBounds(181, 0, 183, 74);
		add_User_Inner_Buttons.add(button_Del);
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove_Last_Create_User();
				user_List.setText(String.valueOf(create_User));
			}
		});
		
		JButton button_Select = new JButton("Select");
		button_Select.setBounds(0, 0, 183, 74);
		add_User_Inner_Buttons.add(button_Select);
		button_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				create_User.setLength(create_User.length()+1);
			}
		});
	}
	
	public static void append_Create_User(String letter){
		create_User.setLength(create_User.length()+1);
		System.out.println(create_User.length());
		create_User.insert(create_User.length(), letter);
		user_List.setText(String.valueOf(create_User));
	}
	public static void remove_Last_Create_User(){
		create_User.setLength(create_User.length()-1);
	}
	public static int get_Create_User_Length(){
		return create_User.length();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Add User", 19, 30);   
    }
}
