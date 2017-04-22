package module_TOUCHSCREEN;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
//import java.awt.event.*;


public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final KeypadPanel keypadPanel = new KeypadPanel();
	/*
	private MainMenuPanel main_Menu = new MainMenuPanel();
	private TestMenuPanel test_Menu = new TestMenuPanel();
	private AddUserPanel add_User = new AddUserPanel();
	private RemoveUserPanel remove_User = new RemoveUserPanel();
	
	private final JPanel main_Area = new JPanel();
	private final JPanel num_Pad = new JPanel();
	private final JPanel mange_Users = new JPanel();
	private final JPanel numberPanel = new JPanel();
	private final static JPanel add_User_House = new JPanel();
	private final static JPanel remove_User_House = new JPanel();
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		buildMainPanelMetadata();
		addComponents();
		/*
		main_Area.setBounds(0, 0, 388, 480);
		add(main_Area);
		main_Area.setLayout(new CardLayout(0, 0));
		main_Area.add(test_Menu);
		main_Area.add(add_User);
		main_Area.add(main_Menu);
		main_Area.add(remove_User);
		main_Menu.setVisible(true);
		
		//Adds the numeric/alpha keys
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
		button_Add_User.setBackground(Colors.getButtonColor());
		button_Add_User.setBounds(0, 0, 388, 90);
		add_User_House.add(button_Add_User);
		

		mange_Users.add(remove_User_House, "name_69614333129613");
		remove_User_House.setLayout(null);
		button_Add_User.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_Add_User_Visible();
			}
		});
		numberPanel.setBounds(0, 90, 388, 364);
		
		num_Pad.add(numberPanel);
		numberPanel.setLayout(new GridLayout(0, 3, 0, 0));		*/
	}
	
	private void buildMainPanelMetadata(){
		setLayout(new GridLayout(0, 2));
		setBackground(Colors.getPanelColor());
		setBorder(new EmptyBorder(15, 15, 15, 15));
	}
	
	private void addComponents(){
		add(keypadPanel, BorderLayout.EAST);
	}
	/*
    public void set_Main_Menu_Visible(){
    	main_Menu.setVisible(true);
    	test_Menu.setVisible(false);
    	add_User.setVisible(false);
    	remove_User.setVisible(false);
    	add_User_House.setVisible(true);
    	remove_User_House.setVisible(false);
    	//MainMenuPanel.clear_Text_Field();
    }
    public void set_Test_Menu_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(true);
    	add_User.setVisible(false);
    	remove_User.setVisible(false);
    }
    public void set_Add_User_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(false);
    	add_User.setVisible(true);
    	remove_User.setVisible(false);
    	add_User_House.setVisible(false);
    	remove_User_House.setVisible(true);
    }
	public void set_Remove_User_Visible(){
    	main_Menu.setVisible(false);
    	test_Menu.setVisible(false);
    	add_User.setVisible(false);
    	remove_User.setVisible(true);
    	add_User_House.setVisible(false);
    	remove_User_House.setVisible(false);
    	RemoveUserPanel.set_Model();
	}*/
}
