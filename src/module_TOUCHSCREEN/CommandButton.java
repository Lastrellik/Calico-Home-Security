package module_TOUCHSCREEN;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import module_PI.Raspberry_PI.main.SerialOutput;

public class CommandButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonText;
	private CommandType commandType;
	private static SerialOutput commandOutput= new SerialOutput();
	
	public CommandButton(String buttonText, CommandType commandType){
		this.buttonText = buttonText;
		this.commandType = commandType;
		buildMetadata();
		buildActionListener();
	}
	
	private void buildMetadata(){
		setBackground(CalicoColors.BUTTON.getColor());	
		setText(buttonText);
		setForeground(CalicoColors.FONT.getColor());
	}
	
	private void buildActionListener(){
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendCommand();
			}
		});
	}
	
	private void sendCommand(){
		switch(commandType){
			case ARM: commandOutput.sendArmPacket();
			break;
			case DISARM: commandOutput.sendDisarmPacket();
			break;
			case SILENCE: commandOutput.sendSilencePacket();
			break;
			case CALIBRATE: commandOutput.sendCalibratePacket();
			break;
			case DECALIBRATE: commandOutput.sendResetCalibrationPacket();
			break;
			case TOGGLELASER: commandOutput.sendToggleLaserPacket();
			break;
			case TRIP: commandOutput.sendTripPacket();
			break;
			case TRIGGER: commandOutput.sendTriggerPacket();
			break;
			case TESTCOMPONENTS: commandOutput.sendTestComponentsPacket();
			break;
		}
	}
	

}
