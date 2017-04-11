package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import javafx.scene.text.Font;

import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import java.awt.Panel;

public class RemoveUserPanel extends JPanel {
	private static Users list = new Users();
	private static HashMap<String, String> usersPass = (HashMap<String, String>) list.get_Users_Pass();
	private static ArrayList<String> store = new ArrayList<>();
	private static JList current_Users; 
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public RemoveUserPanel() {
		setBounds(0,0,388,480);
		setLayout(null);
		
		JPanel remove_Users_Inner_Display = new JPanel();
		remove_Users_Inner_Display.setLayout(null);
		remove_Users_Inner_Display.setBounds(12, 32, 364, 210);
		add(remove_Users_Inner_Display);
		
		fill_Current_User_List();
		current_Users = new JList(store.toArray());
		current_Users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        current_Users.setListData(store.toArray());
		
		current_Users.setBounds(0, 0, 364, 210);
		remove_Users_Inner_Display.add(current_Users);
		
		JPanel remove_Users_Inner_Buttons = new JPanel();
		remove_Users_Inner_Buttons.setLayout(null);
		remove_Users_Inner_Buttons.setBounds(12, 245, 364, 222);
		add(remove_Users_Inner_Buttons);
		
		JButton button = new JButton("Create User");
		button.setBounds(0, 0, 183, 77);
		remove_Users_Inner_Buttons.add(button);
		
		JButton button_1 = new JButton("Main Menu");
		button_1.setBounds(0, 149, 364, 74);
		remove_Users_Inner_Buttons.add(button_1);
		
		JButton button_2 = new JButton("Delete User");
		button_2.setBounds(181, 0, 183, 77);
		remove_Users_Inner_Buttons.add(button_2);
		
		JButton button_3 = new JButton("Delete User");
		button_3.setBounds(0, 73, 364, 78);
		remove_Users_Inner_Buttons.add(button_3);
		
		//testing for determining where paint is
		//remove_Users_Inner_Display.setVisible(false);
		//remove_Users_Inner_Buttons.setVisible(false);
	}
	private static void fill_Current_User_List(){

		for(String s: usersPass.keySet()){
			store.add(String.valueOf(usersPass.keySet()));
		}
		
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Remove User", 19, 30);   
    }
    
    //for testing
    public static void main(String[] args){
    	Users.readFromTextFile();
    	fill_Current_User_List();
    	String[] bar =  new String[store.size()];
    	bar = store.toArray(bar);
    	for(String s: bar){
    		System.out.println(s);
		}
		//System.out.println(bar);
    }
}
