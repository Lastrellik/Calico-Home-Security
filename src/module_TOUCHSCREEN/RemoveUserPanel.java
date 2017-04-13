package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;


import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveUserPanel extends JPanel {
	/**
	 * Parameters are set to static so that other classes can make changes easily without getters and setters.
	 */
	
	/**
	 * @param list is meant to be used with current_User to display an array that is selectable for deletion
	 * @param usersPass symbol table meant to store users, looking to remove and create simple array
	 * @param store array list meant to be used with list, looking to remove and create a simple array
	 * @param current_User displays a selectable list of users for deletion
	 */
	private static Users list = new Users();
	private static HashMap<String, String> usersPass = (HashMap<String, String>) list.get_Users_Pass();
	private static ArrayList<String> store = new ArrayList<>();
	private static JList current_Users = new JList();
	/**
	 * Create the panel.
	 */
	public RemoveUserPanel() {
		setBounds(0,0,388,480);
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		fill_Current_User_List();
		
		/**
		 * @param main_Panel houses all other panels to keep them organized.
		 * 		Set to two rows to create even spacing
		 */
		JPanel main_Panel = new JPanel();
		main_Panel.setBounds(12, 13, 364, 454);
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
		 * current_Users set to Single Selection to prevent multiple deletions.
		 */
		current_Users.setBounds(0, 39, 364, 188);
		display_Panel.add(current_Users);
		current_Users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		current_Users.setListData(store.toArray());
		current_Users.setBounds(0, 41, 364, 186);
		display_Panel.add(current_Users);
		
		/**
		 * Displays the current menu name.
		 */
		JTextArea menu_Name = new JTextArea();
		menu_Name.setEditable(false);
		menu_Name.setColumns(10);
		menu_Name.setBounds(0, 0, 364, 40);
		display_Panel.add(menu_Name);
		
		/**
		 * @param buttons_Panel houses all buttons in 3 rows in grid layout.
		 * @param action_Button_Panel house action buttons in two columns to keep organizing in the top row of add_User_Innner_Buttons panel
		 */
		JPanel buttons_Panel = new JPanel();
		main_Panel.add(buttons_Panel);
		buttons_Panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		/**
		 * @param button_Undo undoes the last deleted user.
		 * @param button_Delete_User deletes the currently selected user.
		 * @param button_Main_Menu makes the main menu visible.
		 */
		JButton button_Undo = new JButton("Undo");
		buttons_Panel.add(button_Undo);
		button_Undo.setBackground(Colors.getButtonColor());

		JButton button_Delete_User = new JButton("Delete User");
		button_Delete_User.setBackground(Colors.getButtonColor());
		buttons_Panel.add(button_Delete_User);
		
		JButton button_Main_Menu = new JButton("Main Menu");
		button_Main_Menu.setBackground(Colors.getButtonColor());
		button_Main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
		buttons_Panel.add(button_Main_Menu);
	}
	/**
	 * Supposed to fill the current user list to display current_Users
	 */
	private static void fill_Current_User_List(){

		for(String s: usersPass.keySet()){
			store.add(String.valueOf(usersPass.keySet()));
		}
		System.out.println();
	}
}
