package module_TOUCHSCREEN;

import java.util.LinkedList;

public class SerialOutput extends SerialComm {
	
	public SerialOutput(){
		serialOutputStream = new LinkedList<>();
	}

	public void sendDataStream(String dataStream) {
		int communicationAttempts = 0;
		byte[] messageHash = getMessageHash(dataStream);
		do {
			loadStringInOutputStream(dataStream);
			while (serialOutputStream.peek() != (byte) '\n') {
				comPort.writeBytes(new byte[] { serialOutputStream.remove() }, 1);
			}
		} while (!gotCorrectHashFromDevice(messageHash) && communicationAttempts < 3);
	}

	private byte[] getMessageHash(String message) {
		byte[] messageByteArray = message.getBytes();
		return digest.digest(messageByteArray);
	}

	private boolean gotCorrectHashFromDevice(byte[] messageHash) {
		if (comPort.bytesAvailable() == 0) {
		}
		return false;
	}

	private void loadStringInOutputStream(String dataStream) {
		byte[] dataStreamByteArray = dataStream.getBytes();
		for (byte b : dataStreamByteArray)
			serialOutputStream.add(b);
		serialOutputStream.add((byte) '\n');
	}
	
}
