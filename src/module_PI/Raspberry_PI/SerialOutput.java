package module_PI.Raspberry_PI;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	
	public SerialOutput(){
		serialPacketOutputStream = new LinkedList<>();
	}
	
	//FOR TESTING PURPOSES ONLY! I ANTICPATE THIS TO BE MODIFIED
	public void sendArmPacket(){
		byte[] buffer = {0};
		comPort.writeBytes(buffer, 1);
	}
	
	public void sendDisarmPacket(){
		byte[] buffer = {1};
		comPort.writeBytes(buffer, 1);		
	}
	//Above code is for testing pursposes only
	
	public void sendCommandPacket(int packetContents){
		DataPacket commandPacket = new DataPacket(packetContents);
		serialPacketOutputStream.add(commandPacket);
	}
	
	public void sendCommandPacket(byte[] packetContents){
		DataPacket commandPacket = new DataPacket(packetContents);
		serialPacketOutputStream.add(commandPacket);
	}
	
	public boolean isOutputStreamEmpty(){
		return(serialPacketOutputStream.isEmpty());
	}
	
	public void sendNextCommand(){
		if(!isOutputStreamEmpty()){
			DataPacket nextCommand = serialPacketOutputStream.remove();
			System.out.println(comPort.writeBytes(nextCommand.getPacketAsArray(), nextCommand.getSizeInBytes()));
		}
	}
}
