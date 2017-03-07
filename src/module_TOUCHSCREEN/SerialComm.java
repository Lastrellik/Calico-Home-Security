package module_TOUCHSCREEN;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Queue;
import com.fazecast.jSerialComm.*;

abstract class SerialComm {
	protected Queue<Byte> serialOutputStream;
	protected static Queue<Byte> serialInputStream;
	protected MessageDigest digest;
	protected SerialPort comPort;

	public SerialComm() {
		try {
			digest = MessageDigest.getInstance("SHA-256");
			comPort = getArduinoCommPort();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchElementException n) {
			n.printStackTrace();
		}
	}

	private SerialPort getArduinoCommPort() {
		SerialPort[] comPorts = SerialPort.getCommPorts();
		for (int i = 0; i < comPorts.length; i++) {
			System.out.println(comPorts[i].getDescriptivePortName());
			if (comPorts[i].getDescriptivePortName().matches("Arduino.*")) {
				return comPorts[i];
			}
		}
		throw new NoSuchElementException();
	}
}
