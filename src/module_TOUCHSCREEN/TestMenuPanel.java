package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
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
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 366, 367);
		add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JButton button = new JButton("Arm");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been armed");
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("Disarm");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been disarmed");
			}
		});
		panel.add(button_1);
		
		JButton button_2 = new JButton("Trigger");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Triggering Alarm");
			}
		});
		panel.add(button_2);
		
		JButton button_3 = new JButton("Silence");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Silencing Arm");
			}
		});
		panel.add(button_3);
		
		JButton button_4 = new JButton("Calibrate");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been calibrated");
			}
		});
		panel.add(button_4);
		
		JButton button_5 = new JButton("Decalibrate");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been decalibrated");
			}
		});
		panel.add(button_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 13, 366, 90);
		add(panel_1);
		
		JButton main_Menu = new JButton("Main Menu");
		main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.set_Main_Menu_Visible();
			}
		});
		main_Menu.setBounds(0, 0, 366, 90);
		panel_1.add(main_Menu);
	}

}
