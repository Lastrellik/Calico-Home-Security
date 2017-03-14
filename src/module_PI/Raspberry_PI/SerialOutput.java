package module_PI.Raspberry_PI;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	
	public SerialOutput(){
		serialPacketOutputStream = new LinkedList<>();
	}
	
	public void sendCommandMessage(String commandMessage){
		DataPacket commandPacket = new DataPacket(commandMessage, "COMMAND");
		serialPacketOutputStream.add(commandPacket);
	}
	
	public boolean isOutputStreamEmpty(){
		return(serialPacketOutputStream.isEmpty());
	}
	
	public void sendNextCommand(){
		if(!isOutputStreamEmpty()){
			DataPacket nextCommand = serialPacketOutputStream.remove();
			comPort.writeBytes(nextCommand.getPacketAsArray(), nextCommand.getSizeInBytes());
		}
	}
}
