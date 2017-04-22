package module_TOUCHSCREEN;

import javax.swing.JPanel;
import javax.swing.JTextField;

import module_PI.Raspberry_PI.main.SerialOutput;

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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SerialOutput output = new SerialOutput();
	/**
	 * Create the panel.
	 */
	public TestMenuPanel() {
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
		main_Panel.setLayout(null);
		
		/**
		 * @param houses the test buttons for the system.
		 */
		JPanel test_Button_Panel = new JPanel();
		test_Button_Panel.setBounds(0, 0, 364, 380);
		main_Panel.add(test_Button_Panel);
		test_Button_Panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		/**
		 * @param button_Arm sends out a test arm and message
		 * @param button_Disarm sends out a test disarm and message
		 * @param button_Trigger sends out a test trigger and message
		 * @param button_Silence sends out a test silence and message
		 * @param button_Calibrate sends out a test calibration and message
		 * @param button_Decalibration sends out a test decalibration and message
		 * @param button_Main_Menu makes the main menu visible
		 */
		JButton button_Arm = new JButton("Arm");
		button_Arm.setBackground(Colors.getButtonColor());
		button_Arm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendArmPacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been armed");
			}
		});
		test_Button_Panel.add(button_Arm);
		
		JButton button_Disarm = new JButton("Disarm");
		button_Disarm.setBackground(Colors.getButtonColor());
		button_Disarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendDisarmPacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been disarmed");
			}
		});
		test_Button_Panel.add(button_Disarm);
		
		JButton button_Trip = new JButton("Trip");
		button_Trip.setBackground(Colors.getButtonColor());
		button_Trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendTripPacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Triggering Alarm");
			}
		});
		test_Button_Panel.add(button_Trip);
		
		JButton button_Silence = new JButton("Silence");
		button_Silence.setBackground(Colors.getButtonColor());
		button_Silence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendSilencePacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Silencing Arm");
			}
		});
		test_Button_Panel.add(button_Silence);
		
		JButton button_Calibrate = new JButton("Calibrate");
		button_Calibrate.setBackground(Colors.getButtonColor());
		button_Calibrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendCalibratePacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been calibrated");
			}
		});
		test_Button_Panel.add(button_Calibrate);
		
		JButton button_Decalibrate = new JButton("Decalibrate");
		button_Decalibrate.setBackground(Colors.getButtonColor());
		button_Decalibrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.sendResetCalibrationPacket();
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Alarm has been decalibrated");
			}
		});
		test_Button_Panel.add(button_Decalibrate);
		
		JButton main_Menu = new JButton("Main Menu");
		main_Menu.setBackground(Colors.getButtonColor());
		main_Menu.setBounds(0, 378, 364, 76);
		main_Panel.add(main_Menu);
		main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MainPanel.set_Main_Menu_Visible();
			}
		});
	}

}
