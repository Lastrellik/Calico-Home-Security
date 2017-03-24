package module_PI.Raspberry_PI;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	
	public SerialOutput(){
		serialPacketOutputStream = new LinkedList<>();
	}
	
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
