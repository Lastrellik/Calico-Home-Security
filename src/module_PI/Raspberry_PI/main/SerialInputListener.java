package module_PI.Raspberry_PI.main;

import java.util.LinkedList;

public class SerialInputListener extends SerialComm implements Runnable {

	private final int SIZE_OF_PACKET_IN_BYTES = 5;

	@Override
	public void run() {
		while (true) {
			listen();
			createPacketIfAvailable();
			pause(100);
		}
	}

	public SerialInputListener() {
		serialInputStream = new LinkedList<Byte>();
		packetInputStream = new LinkedList<DataPacket>();
		comPort.openPort();
		flushInputStream();
	}

	private void flushInputStream(){
		byte[] streamDisposalByteArray = new byte[1];
		while (comPort.bytesAvailable() > 0){
			comPort.readBytes(streamDisposalByteArray, 1);
			pause(1);
		}
	}
	
	public boolean isPacketAvailable() {
		return !packetInputStream.isEmpty();
	}

	public boolean isDataStreamEmpty() {
		return (serialInputStream.isEmpty());
	}

	public DataPacket getDataPacket() {
		return packetInputStream.remove();
	}

	private void listen() {
		if (comPort.bytesAvailable() > 0) {
			byte[] readBuffer = new byte[comPort.bytesAvailable()];
			comPort.readBytes(readBuffer, readBuffer.length);
			for (byte b : readBuffer) {
				serialInputStream.add(b);
				System.out.println((char)b);
			}
		}
	}

	private void createPacketIfAvailable() {
		DataPacket packet;
		if (isRawPacketAvailable()) {
			byte[] rawPacketData = new byte[SIZE_OF_PACKET_IN_BYTES];
			for (int i = 0; i < SIZE_OF_PACKET_IN_BYTES; i++) {
				rawPacketData[i] = serialInputStream.remove();
			}
			packet = new DataPacket(rawPacketData);
			packetInputStream.add(packet);
		}
	}

	private boolean isRawPacketAvailable() {
		return (serialInputStream.size() >= SIZE_OF_PACKET_IN_BYTES);
	}

	private void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
