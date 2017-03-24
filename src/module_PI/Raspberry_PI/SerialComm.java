package module_PI.Raspberry_PI;

import java.util.NoSuchElementException;
import java.util.Queue;
import com.fazecast.jSerialComm.*;

abstract class SerialComm {
	protected Queue<DataPacket> serialPacketOutputStream;
	protected static Queue<Byte> serialInputStream;
	protected static Queue<DataPacket> packetInputStream;
	protected static SerialPort comPort;

	public SerialComm() {
		try {
			if(comPort == null){
				comPort = getArduinoCommPort();
				comPort.setBaudRate(9600);
			}
		} catch (NoSuchElementException n) {
			n.printStackTrace();
		}
	}

	private SerialPort getArduinoCommPort() {
		SerialPort[] comPorts = SerialPort.getCommPorts();
		for (int i = 0; i < comPorts.length; i++) {
			if (comPorts[i].getDescriptivePortName().matches("Arduino.*")) {
				return comPorts[i];
			}
		}
		throw new NoSuchElementException("Arduino Uno Cannot Be Found");
	}

}
