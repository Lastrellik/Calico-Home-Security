package module_PI.Raspberry_PI;

import java.util.LinkedList;

public class SerialInputListener extends SerialComm implements Runnable {

	private final int SIZE_OF_PACKET_IN_BYTES = 64;
	
	@Override
	public void run() {
		while (true){
			listen();
			createPacketIfAvailable();
		}
	}

	public SerialInputListener() {
		serialInputStream = new LinkedList<Byte>();
		packetInputStream = new LinkedList<DataPacket>();
		comPort.openPort();
	}
	
	public boolean isPacketAvailable(){
		return !packetInputStream.isEmpty();
	}
	
	public boolean isDataStreamEmpty(){
		return(serialInputStream.isEmpty());
	}
	
	public DataPacket getDataPacket(){
		return packetInputStream.remove();
	}

	private void listen() {
		if (comPort.bytesAvailable() > 0) {
			byte[] readBuffer = new byte[comPort.bytesAvailable()];
			comPort.readBytes(readBuffer, readBuffer.length);
			for (byte b : readBuffer) serialInputStream.add(b);
		}
		pause(100);
	}
	
	private void createPacketIfAvailable(){
		DataPacket packet;
		if(isRawPacketAvailable()){
			byte[] rawPacketData = new byte[SIZE_OF_PACKET_IN_BYTES];
			for(int i = 0; i < SIZE_OF_PACKET_IN_BYTES; i++){
				rawPacketData[i] = serialInputStream.remove();
			}
			packet = new DataPacket(rawPacketData);
			packetInputStream.add(packet);
			sendPacketHashBackToArduino(packet);
		}
	}
	
	private void sendPacketHashBackToArduino(DataPacket packet){
		comPort.writeBytes(packet.getPacketSha1Hash(), 8);
	}
	
	private boolean isRawPacketAvailable(){
		if(!serialInputStream.isEmpty()){
			return(serialInputStream.peek() == '~' && serialInputStream.size() == SIZE_OF_PACKET_IN_BYTES);
		} else {
			return false;
		}
	}

	public byte[] flush() {
		byte[] wholeStream = new byte[serialInputStream.size()];
		for (int i = 0; i < serialInputStream.size(); i++) {
			wholeStream[i] = serialInputStream.remove();
		}
		return wholeStream;
	}

	private void pause(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
