package module_PI.Raspberry_PI.main;

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
				PiApp.LogToFile("Arduino found on comPort " + comPort.getDescriptivePortName());
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
		if(comPorts.length != 0) return comPorts[0]; //To account for arduino ports that don't match Arduino.*
		throw new NoSuchElementException("Arduino Uno Cannot Be Found");
	}

}
