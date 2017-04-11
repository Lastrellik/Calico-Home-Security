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
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		fill_Current_User_List();
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 364, 454);
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		current_Users = new JList();
		current_Users.setBounds(0, 39, 364, 188);
		panel_2.add(current_Users);
		current_Users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		current_Users.setListData(store.toArray());
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(10);
		textArea.setBounds(0, 0, 364, 40);
		panel_2.add(textArea);
		
		JPanel remove_Users_Inner_Buttons = new JPanel();
		panel.add(remove_Users_Inner_Buttons);
		remove_Users_Inner_Buttons.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		remove_Users_Inner_Buttons.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_2 = new JButton("Delete User");
		button_2.setBackground(Colors.getButtonColor());
		panel_1.add(button_2);
		
		JButton button = new JButton("Create User");
		button.setBackground(Colors.getButtonColor());
		panel_1.add(button);
		
		JButton button_3 = new JButton("Delete User");
		button_3.setBackground(Colors.getButtonColor());
		remove_Users_Inner_Buttons.add(button_3);
		
		JButton button_1 = new JButton("Main Menu");
		button_1.setBackground(Colors.getButtonColor());
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
		remove_Users_Inner_Buttons.add(button_1);
	}
	private static void fill_Current_User_List(){

		for(String s: usersPass.keySet()){
			store.add(String.valueOf(usersPass.keySet()));
		}
		System.out.println();
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Remove User", 0, 0);   
    }
}
