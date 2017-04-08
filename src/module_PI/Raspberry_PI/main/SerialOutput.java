package module_PI.Raspberry_PI.main;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;
	private byte[] currentBuffer;
	
	public SerialOutput(){
		serialPacketOutputStream = new LinkedList<>();
	}
	
	public void sendArmPacket(){
		currentBuffer = new byte[]{3,0,0,0,1}; // 30001 = Command, NA, Execute Command: Arm
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);
	}
	
	public void sendDisarmPacket(){
		currentBuffer = new byte[]{3,0,0,0,2};// 30002 = Command, NA, Execute Command: Disarm
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendSilencePacket(){
		currentBuffer = new byte[]{3,0,0,0,3};// 30003 = Command, NA, Execute Command: Silence
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendCalibratePacket(){
		currentBuffer = new byte[]{3,0,0,0,4};// 30004 = Command, NA, Execute Command: Calibrate
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendTriggerPacket(){
		currentBuffer = new byte[]{3,0,0,0,5};// 30005 = Command, NA, Execute Command: Trigger
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
	
	public void sendResetCalibrationPacket(){
		currentBuffer = new byte[]{3,0,0,0,6};// 30006 = Command, NA, Execute Command: ResetCalibration
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
	
	public void sendTestComponentsPacket(){
		currentBuffer = new byte[]{3,0,0,0,7};// 30007 = Command, NA, Execute Command: TestComponents
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public byte[] getPreviousBuffer(){
		return currentBuffer;
	}
}
