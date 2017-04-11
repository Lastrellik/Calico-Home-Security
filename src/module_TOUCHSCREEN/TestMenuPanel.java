package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JTextField;


import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestMenuPanel() {
		setBounds(0,0,388,480);
		setBackground(Colors.getPanelColor());
		setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 364, 454);
		add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 364, 380);
		panel_2.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JButton button = new JButton("Arm");
		button.setBackground(Colors.getButtonColor());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been armed");
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("Disarm");
		button_1.setBackground(Colors.getButtonColor());
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been disarmed");
			}
		});
		panel.add(button_1);
		
		JButton button_2 = new JButton("Trigger");
		button_2.setBackground(Colors.getButtonColor());
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Triggering Alarm");
			}
		});
		panel.add(button_2);
		
		JButton button_3 = new JButton("Silence");
		button_3.setBackground(Colors.getButtonColor());
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Silencing Arm");
			}
		});
		panel.add(button_3);
		
		JButton button_4 = new JButton("Calibrate");
		button_4.setBackground(Colors.getButtonColor());
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been calibrated");
			}
		});
		panel.add(button_4);
		
		JButton button_5 = new JButton("Decalibrate");
		button_5.setBackground(Colors.getButtonColor());
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been decalibrated");
			}
		});
		panel.add(button_5);
		
		JButton main_Menu = new JButton("Main Menu");
		main_Menu.setBackground(Colors.getButtonColor());
		main_Menu.setBounds(0, 378, 364, 76);
		panel_2.add(main_Menu);
		main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
	}

}
