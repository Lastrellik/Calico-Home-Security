package module_TOUCHSCREEN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeAlarmPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	CommandButton armButton = new CommandButton("Arm", CommandType.ARM);
	CommandButton disarmButton = new CommandButton("Disarm", CommandType.DISARM);
	CommandButton toggleLaserButton = new CommandButton("<html>Toggle<br>Laser</html>", CommandType.TOGGLELASER);
	JButton adminPanelButton = new JButton("<html>Admin<br>Panel</html>");
	
	public HomeAlarmPanel(){
		buildMetadata();
		configureAdminPanelButton();
		addButtons();
	}
	
	private void buildMetadata(){
		setLayout(new GridLayout(2, 2, 3, 3));
	}
	
	private void configureAdminPanelButton(){
		adminPanelButton.setBackground(Colors.getButtonColor());
		adminPanelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void addButtons(){
		add(armButton);
		add(disarmButton);
		add(toggleLaserButton);
		add(adminPanelButton);
	}

}
