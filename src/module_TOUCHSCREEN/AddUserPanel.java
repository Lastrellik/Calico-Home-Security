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
import javax.swing.JLabel;

public class AddUserPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * @param create_User used to manage the string of the user currently being written
	 */
	private static StringBuilder create_User = new StringBuilder();
	
	/**
	 * @param user_List text area that is used to display the create_User StringBuilder
	 */
	private static JTextArea user_List = new JTextArea();
	
	/**
	 * @ param min used in random number generated for lowest value
	 * @ param max used in random number generated for highest value
	 */
	private int min = 0;
	private int max = 9;
	
	/**
	 * @ param button_Pressed used to increment if the select button has been pressed, or reset to not being pressed 
	 * @ param create_True used to increment if the create button has been pressed, or reset to not being pressed
	 */
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
		
		/**
		 * @param main_Panel houses all other panels to keep them organized.
		 * 		Set to two rows to create even spacing
		 */
		JPanel main_Panel = new JPanel();
		main_Panel.setBounds(12, 13, 364, 454);
		add(main_Panel);
		main_Panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		/**
		 * @param display_Panel handles displaying text area and the menu.
		 * 		Uses absolute to make adjustable menu, to meet certain heights.
		 */
		JPanel display_Panel = new JPanel();
		main_Panel.add(display_Panel);
		display_Panel.setLayout(null);
		user_List.setBounds(0, 39, 364, 188);
		display_Panel.add(user_List);
		user_List.setEditable(false);
		
		/**
		 * @param displays names of the menu you're on
		 */
		menu_Name = new JTextArea();
		menu_Name.setEditable(false);
		menu_Name.setBounds(0, 0, 364, 40);
		display_Panel.add(menu_Name);
		menu_Name.setColumns(10);
		
		/**
		 * @param buttons_Panel houses all buttons in 3 rows in grid layout.
		 * @param action_Button_Panel house action buttons in two columns to keep organzing in the top row of add_User_Innner_Buttons panel
		 */
		JPanel buttons_Panel = new JPanel();
		main_Panel.add(buttons_Panel);
		buttons_Panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel action_Button_Panel = new JPanel();
		buttons_Panel.add(action_Button_Panel);
		action_Button_Panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		/**
		 * @param button_Del deletes last character in user_List
		 * @param button_Selec selects the current character being displayed and increments the Stringbuilder create_User up by one
		 * @param button_Create creates a user and displays in user_List
		 * @param button_Main_Menu takes you back to the Main Menu
		 */
		JButton button_Del = new JButton("Del");
		button_Del.setBackground(Colors.getButtonColor());
		action_Button_Panel.add(button_Del);
		
		JButton button_Select = new JButton("Select");
		button_Select.setBackground(Colors.getButtonColor());
		action_Button_Panel.add(button_Select);
		
		JButton button_Create = new JButton("Create User");
		buttons_Panel.add(button_Create);
		button_Create.setBackground(Colors.getButtonColor());
		
		JButton button_Main_Menu = new JButton("Main Menu");
		buttons_Panel.add(button_Main_Menu);
		button_Main_Menu.setBackground(Colors.getButtonColor());
		button_Main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MainPanel.set_Main_Menu_Visible();
			}
		});
		
		/**
		 * Generates a random number 0 through 9 for a four digit pin, for for different spots and strings it together in one string.
		 * Supposed to check to see if the pin is already set in a list of user and if so generate another one.
		 */
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
				Users.addUser(create_User, passcode);
			}
		});
		/**
		 * Determines if the StringBuilder create_User is not set to 0.
		 * If not set to 0 increments it plus one to append it manually and add the next letter chosen.
		 * Sets button_Pressed to false to let the system know not increment the StringBuilder again unless a letter is entered in.
		 */
		button_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//For testing
				//System.out.println("Current Status Pressed: " + button_Pressed);
				if(create_User.length() > 0 && button_Pressed == true){ 
					create_User.setLength(create_User.length()+1);
					pressedFalse();
				}
				//For testing
				//System.out.println(create_User.length());
			}
		});
		/**
		 * Checks first to see if create_User is not set to 0.
		 * Then it increments the StringBuilder down one to erase the last character.
		 * Sets button_Pressed to True so people can use the select again.
		 */
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(create_User.length() > 0){
					//For testing
					//System.out.println("Current Status Pressed in Del: "+button_Pressed);
					if(button_Pressed == false){
						create_User.setLength(create_User.length()-1);
						pressedTrue();
					}
					create_User.setLength(create_User.length()-1);
					//For testing
					//System.out.println(create_User.length());
					user_List.setText(String.valueOf(create_User));
				}
			}
		});
	}
	/**
	 * Takes in a char from the MainPanel number/alpha keys to append to create_User String Builder
	 * @param letter
	 */
	public static void append_Create_User(char letter){
		if(create_True == true){
			clear_Create_User();
			createFalse();
		}
		create_User.append(letter);
		user_List.setText(String.valueOf(create_User));
		//For testing
		//System.out.println(create_User.length());
	}
	/**
	 * Increment create_User minus one
	 */
	public static void remove_Last_Create_User(){
		create_User.setLength(create_User.length()-1);
	}
	/**
	 * Clears out the create_User StringBuilder by setting it to 0 and it clears out the user_List
	 */
	public static void clear_Create_User(){
		create_User.setLength(0);
		user_List.setText(String.valueOf(create_User));
	}
	/**
	 * Returns the length of the create_User.
	 * @return
	 */
	public static int get_Create_User_Length(){
		return create_User.length();
	}
	/**
	 * Sets button_Pressed to true.
	 */
	public static void pressedTrue(){
		if(button_Pressed == false) button_Pressed = true;
	}
	/**
	 * Sets button_Pressed to false.
	 */
	public static void pressedFalse(){
		if(button_Pressed == true) button_Pressed = false;
	}
	/**
	 * Sets create_True to true.
	 */
	public static void createTrue(){
		if(create_True == false) create_True = true;
	}
	/**
	 * Sets create_True to false.
	 */
	public static void createFalse(){
		if(create_True == true) create_True = false;
	}
	/**
	 * Returns to the status of button_Pressed.
	 * @return
	 */
	public static boolean getButtonPressed(){
		return button_Pressed;
	}
	/**
	 * Returns to the status of create_True.
	 * @return
	 */
	public static boolean getCreateTrue(){
		return create_True;
	}
}
