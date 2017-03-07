package module_TOUCHSCREEN;

import java.util.LinkedList;

public class SerialInputListener extends SerialComm implements Runnable {

	@Override
	public void run() {
		while (true)
			listen();
	}

	public SerialInputListener() {
		serialInputStream = new LinkedList<>();
		comPort.openPort();
	}

	private void listen() {
		if (comPort.bytesAvailable() > 0) {
			byte[] readBuffer = new byte[comPort.bytesAvailable()];
			comPort.readBytes(readBuffer, readBuffer.length);
			for (byte b : readBuffer) serialInputStream.add(b);
		}
		pause(500);
	}

	public byte[] readAll() {
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
