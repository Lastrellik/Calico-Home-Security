package module_PI.Raspberry_PI;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;
	
	public SerialOutput(){
		serialPacketOutputStream = new LinkedList<>();
	}
	
	public void sendArmPacket(){
		byte[] buffer = {3,0,0,0,1}; // 30001 = Command, NA, Execute Command: Arm
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);
	}
	
	public void sendDisarmPacket(){
		byte[] buffer = {3,0,0,0,2};// 30002 = Command, NA, Execute Command: Disarm
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendSilencePacket(){
		byte[] buffer = {3,0,0,0,3};// 30003 = Command, NA, Execute Command: Silence
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendCalibratePacket(){
		byte[] buffer = {3,0,0,0,4};// 30004 = Command, NA, Execute Command: Calibrate
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendTriggerPacket(){
		byte[] buffer = {3,0,0,0,5};// 30005 = Command, NA, Execute Command: Trigger
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
	
	public void sendResetCalibrationPacket(){
		byte[] buffer = {3,0,0,0,6};// 30006 = Command, NA, Execute Command: ResetCalibration
		comPort.writeBytes(buffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
}
