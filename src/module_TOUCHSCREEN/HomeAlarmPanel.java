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
	private CardLayout cardLayout;
	private MenuPanel cardParent;
	
	public HomeAlarmPanel(MenuPanel cardParent){
		this.cardParent = cardParent;
		this.cardLayout = (CardLayout) cardParent.getLayout();
		buildMetadata();
		configureAdminPanelButton();
		addButtons();
	}
	
	private void buildMetadata(){
		setLayout(new GridLayout(2, 2, 3, 3));
		setBackground(CalicoColors.PANELBACKGROUND.getColor());
	}
	
	private void configureAdminPanelButton(){
		adminPanelButton.setBackground(CalicoColors.BUTTON.getColor());
		adminPanelButton.setForeground(CalicoColors.FONT.getColor());
		adminPanelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardParent, "Admin Panel");
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
