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


public class MainPanel extends JPanel {
	
	private BufferedImage calDot = null;
	private static MainMenuPanel main_Menu = new MainMenuPanel();
	private static TestMenuPanel test_Menu = new TestMenuPanel();
	private static AddUserPanel add_User = new AddUserPanel();
	private static RemoveUserPanel remove_User = new RemoveUserPanel();
	
	private StringBuilder pass = new StringBuilder();
	/**
	 * @param timer used for creating a time event
	 */
	private Timer timer = new Timer();

	/**
	 * Drawing booleans for the paint component
	 * @param draw_Thinking_Dot
	 * @param draw_Logo
	 */
	private boolean draw_Thinking_Dot = false;
	private boolean draw_Logo = false;
	private final JPanel main_Area = new JPanel();
	private final JPanel num_Pad = new JPanel();
	private final JPanel mange_Users = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final static JPanel add_User_House = new JPanel();
	private final static JPanel remove_User_House = new JPanel();
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);
		main_Area.setBounds(0, 0, 388, 480);
		
		add(main_Area);
		main_Area.setLayout(new CardLayout(0, 0));
		main_Area.add(test_Menu, "name_29408201602606");
		main_Area.add(add_User, "name_29409953565271");
		main_Area.add(main_Menu, "name_30342747712594");
		main_Area.add(remove_User);
		main_Menu.setVisible(true);
		num_Pad.setBounds(400, 13, 388, 454);
		
		add(num_Pad);
		add_User.setVisible(false);
		test_Menu.setVisible(false);
		num_Pad.setLayout(null);
		mange_Users.setBounds(0, 0, 388, 90);
		num_Pad.add(mange_Users);
		mange_Users.setLayout(new CardLayout(0, 0));
		
		mange_Users.add(add_User_House, "name_69549789820215");
		add_User_House.setLayout(null);
		
		JButton button_Add_User = new JButton("Add User");
		button_Add_User.setBounds(0, 0, 388, 90);
		add_User_House.add(button_Add_User);
		

		mange_Users.add(remove_User_House, "name_69614333129613");
		remove_User_House.setLayout(null);
		
		JButton remove_User_Button = new JButton("Remove User");
		remove_User_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_Remove_User_Visible();
			}
		});
		remove_User_Button.setBounds(0, 0, 388, 90);
		remove_User_House.add(remove_User_Button);
		button_Add_User.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_Add_User_Visible();
			}
		});
		panel_1.setBounds(0, 90, 388, 364);
		
		num_Pad.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		number_Pad();
		
		
	}

	private void number_Pad() {
		JButton numPad_7 = new JButton("<html><center>7<br>ABC</center></html>");
		numPad_7.addActionListener(new ActionListener() {
			int count = 0;
			public void actionPerformed(ActionEvent e) {
				count++;
				if(count == 1){
					if(AddUserPanel.get_Create_User_Length() > 0)
						AddUserPanel.remove_Last_Create_User();
					AddUserPanel.append_Create_User("A");
				}
				if(count == 2){
					if(AddUserPanel.get_Create_User_Length() > 0)
						AddUserPanel.remove_Last_Create_User();
					AddUserPanel.append_Create_User("B");
				}
				if(count == 3){
					if(AddUserPanel.get_Create_User_Length() > 0)
						AddUserPanel.remove_Last_Create_User();
					AddUserPanel.append_Create_User("C");
				count = 0;
				}
			}
		});
		panel_1.add(numPad_7);
		
		JButton numPad_8 = new JButton("<html><center>8<br>DEF</center></html>");
		numPad_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("8");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_8.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_8);
		JButton numPad_9 = new JButton("<html><center>9<br>GHI</center></html>");
		numPad_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("9");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_9.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_9);
		JButton numPad_4 = new JButton("<html><center>4<br>JKL</center></html>");
		numPad_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("4");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_4.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_4);
		
		JButton numPad_5 = new JButton("<html><center>5<br>MNO</center></html>");
		numPad_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("5");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_5.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_5);
		
		JButton numPad_6 = new JButton("<html><center>6<br>PQR</center></html>");
		numPad_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("6");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_6.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_6);
		JButton numPad_1 = new JButton("<html><center>1<br>STU</center></html>");
		numPad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("1");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_1.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_1);
		
		JButton numPad_2 = new JButton("<html><center>2<br>VWX</center></html>");
		numPad_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("2");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_2.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_2);
		JButton numPad_3 = new JButton("<html><center>4<br>YZ</center></html>");
		numPad_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("3");
					MainMenuPanel.append_Text_Field();
					//t_Display.setText(String.valueOf(asterisk));
					System.out.println(numPad_3.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_3);
		
		JButton numPad_Clear = new JButton("Clr");
		numPad_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton num_Pad_Asterisk = new JButton("*");
		num_Pad_Asterisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//invalid * can not be used clearing out code
			}
		});

		panel_1.add(num_Pad_Asterisk);
		
		JButton numPad_0 = new JButton("0");
		numPad_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pass.length()<4){
					pass.append("0");
					MainMenuPanel.append_Text_Field();
					System.out.println(numPad_0.getText());
				}
				//for testing print out of pass when reaching 4 characters
				if(pass.length() == 4){
					System.out.println(pass);
				}
			}
		});
		panel_1.add(numPad_0);
		panel_1.add(numPad_Clear);
	}

	/**
	 * checks image files to make sure they're able to be read from the directory.
	 * 
	 */
	private void checkFilePathOfImages() {
		try {                
	          calDot = ImageIO.read(getClass().getResource("calDot.png"));
	       
	       } catch (IOException ex) {
	            System.out.println("Fail");
	       }
	}
	
	/**
		Class that is only used for scheduling the transitions of certain actions.
	*/
    class Task extends TimerTask {
    	/**
    	 * 
    	 */
        public void run() {
        	pass.setLength(0);
            timer.cancel(); //Terminate the timer thread
        }
    }
	/**
	 * Getter for Pair Key Value usersPass, mainly for testing.
	 * @param usersPass
	 */
    public static void set_Main_Menu_Visible(){
    	main_Menu.setVisible(true);
    	test_Menu.setVisible(false);
    	add_User.setVisible(false);
    	remove_User.setVisible(false);
    	add_User_House.setVisible(true);
    	remove_User_House.setVisible(false);
    }
    public static void set_Test_Menu_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(true);
    	add_User.setVisible(false);
    	remove_User.setVisible(false);
    }
    public static void set_Add_User_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(false);
    	add_User.setVisible(true);
    	remove_User.setVisible(false);
    	add_User_House.setVisible(false);
    	remove_User_House.setVisible(true);
    	
    }

	public static void set_Remove_User_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(false);
    	add_User.setVisible(false);
    	remove_User.setVisible(true);
    	add_User_House.setVisible(false);
    	remove_User_House.setVisible(false);
    	
	}
	/**
	 * Testing for reading in from file
	 */
	public static void main(String[] args){

	}
}
