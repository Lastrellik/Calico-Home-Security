package module_PI.Raspberry_PI.main;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;
	private byte[] currentBuffer;
	
	public SerialOutput(){
		PiApp.LogToFile("Pi SerialOutputListener created");
		serialPacketOutputStream = new LinkedList<>();
	}
	
	public void sendArmPacket(){
		currentBuffer = new byte[]{3,2,0,0,1}; // 32001 = Command, Warn, Execute Command: Arm
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);
	}
	
	public void sendDisarmPacket(){
		currentBuffer = new byte[]{3,2,0,0,2};// 32002 = Command, Warn, Execute Command: Disarm
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendSilencePacket(){
		currentBuffer = new byte[]{3,2,0,0,3};// 32003 = Command, Warn, Execute Command: Silence
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendCalibratePacket(){
		currentBuffer = new byte[]{3,2,0,0,4};// 32004 = Command, Warn, Execute Command: Calibrate
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}
	
	public void sendTriggerPacket(){
		currentBuffer = new byte[]{3,2,0,0,5};// 32005 = Command, Warn, Execute Command: Trigger
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
	
	public void sendResetCalibrationPacket(){
		currentBuffer = new byte[]{3,2,0,0,6};// 32006 = Command, Warn, Execute Command: ResetCalibration
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);	
	}
	
	public void sendTestComponentsPacket(){
		currentBuffer = new byte[]{3,2,0,0,7};// 32007 = Command, Warn, Execute Command: TestComponents
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);		
	}

	public void sendTripPacket(){
		currentBuffer = new byte[]{3,2,0,0,8};// 32008 = Command, Warn, Execute Command: Trip
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);
	}
	
	public void sendToggleLaserPacket(){
		currentBuffer = new byte[]{3,2,0,0,9};// 32009 = Command, Warn, Execute Command: Toggle Laser
		PiApp.LogToFile(new DataPacket(currentBuffer));
		comPort.writeBytes(currentBuffer, SIZE_OF_DATAPACKET_IN_BYTES);
	}
	
	public byte[] getPreviousBuffer(){
		return currentBuffer;
	}
}

