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
		pause(5000);
		flush();
		pause(1000);
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
			for (byte b : readBuffer) {
				serialInputStream.add(b);
				System.out.print(b + " ");
			}
			//System.out.println("Size: " + serialInputStream.size());
			//System.out.println("peek: " + serialInputStream.peek());
		}
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
			System.out.println("Message: " + packet.getMessage());
		}
	}
	
	private void sendPacketHashBackToArduino(DataPacket packet){
		comPort.writeBytes(packet.getPacketSha1Hash(), 8);
	}
	
	private boolean isRawPacketAvailable(){
		if(!serialInputStream.isEmpty()){
			return(serialInputStream.peek() == '~' && serialInputStream.size() >= SIZE_OF_PACKET_IN_BYTES);
		} else {
			return false;
		}
	}

	public void flush() {
		byte[] b = new byte[1];
		while(comPort.bytesAvailable() > 0){
			b[0] = (byte) comPort.readBytes(b, 1);
		}
	}

	private void pause(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
