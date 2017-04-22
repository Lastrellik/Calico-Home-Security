package module_PI.Raspberry_PI.main;

import java.util.*;

public class PacketMessageTranslator {
	
	private Map<Integer, String> messageTable = new Hashtable<>();
	private final int MAX_PACKET_DETAILS_INT = 55999;
	private final int NUM_OF_BYTES_IN_PACKET_DETAILS = 3;
	private final int NUM_OF_BYTES_IN_PACKET = 5;
	private final int MAX_DETAILS_ONLY_INT = 999;
	private final int MIN_FULL_PACKET_INT = 11001;
	
	public PacketMessageTranslator(){
		buildTable();
	}
	
	private void buildTable(){
		messageTable.put(1, "Execute Command: Arm");
		messageTable.put(2, "Execute Command: Disarm");
		messageTable.put(3, "Execute Command: Silence");
		messageTable.put(4, "Execute Command: Calibrate");
		messageTable.put(5, "Execute Command: Trigger");
		messageTable.put(6, "Execute Command: Reset Calibration");
		messageTable.put(7, "Execute Command: Test Components");
		messageTable.put(8, "Execute Command: Trip");
		messageTable.put(9, "Execute Command: Toggle Laser");
		messageTable.put(100, "Alarm object successfully created");
		messageTable.put(101, "Alarm base photoresistor reading determined");
		messageTable.put(102, "Alarm failed action");
		messageTable.put(103, "Alarm successful action");
		messageTable.put(104, "Alarm failed to arm");
		messageTable.put(105, "Alarm successfully armed");
		messageTable.put(106, "Alarm successful calibration");
		messageTable.put(107, "Alarm has begun arming");
		messageTable.put(108, "Alarm has been triggered");
		messageTable.put(109, "Alarm calibration is being reset");
		messageTable.put(110, "Alarm is being disarmed");
		messageTable.put(111, "Alarm is being silenced");
		messageTable.put(112, "Alarm has been tripped");
		messageTable.put(150, "Button object successfully created");
		messageTable.put(151, "Button has been pressed");
		messageTable.put(200, "Buzzer object has been created");
		messageTable.put(201, "Buzzer has started to sound");
		messageTable.put(202, "Buzzer has stopped sounding");
		messageTable.put(250, "Component object successfully created");
		messageTable.put(300, "ComponentTester default constructor called");
		messageTable.put(301, "ComponentTester object successfully created");
		messageTable.put(350, "Laser object successfully created");
		messageTable.put(351, "Laser has been turned on");
		messageTable.put(352, "Laser has been turned off");
		messageTable.put(353, "Laser has been toggled");
		messageTable.put(400, "LED object successfully created");
		messageTable.put(401, "LED has been turned on");
		messageTable.put(402, "LED has been turned off");
		messageTable.put(403, "LED flashing");
		messageTable.put(404, "LED toggling");
		messageTable.put(450, "Photoresistor object has been successfully created");
		messageTable.put(451, "Photoresistor taking reading");
	}
	
	public String translate(int packetDetails){
		if(isIntOutOfScope(packetDetails)) throw new IllegalArgumentException("invalid packet int: " + packetDetails);
		int correctedInt = parsePacketDetailsFromFullPacketInt(packetDetails);
		if(!messageTable.containsKey(correctedInt)) throw new IllegalArgumentException("packetDetails " + packetDetails + " not in messageTable");
		return messageTable.get(correctedInt);
	}
	
	private int parsePacketDetailsFromFullPacketInt(int packetAsInt){
		int packetModThisIsCorrectLength = (int) (Math.pow(10, NUM_OF_BYTES_IN_PACKET_DETAILS));
		return packetAsInt % packetModThisIsCorrectLength;
	}
	
	private boolean isIntOutOfScope(int packetDetails){
		return (packetDetails < 1 
				|| packetDetails > MAX_PACKET_DETAILS_INT
				|| packetDetails > MAX_DETAILS_ONLY_INT && packetDetails < MIN_FULL_PACKET_INT);
	}
	
	public String translate(String packetDetailsAsString){
		return translate(Integer.parseInt(packetDetailsAsString));
	}
	
	public String translate(byte[] packetAsByteArray){
		if(packetIsInvalidLength(packetAsByteArray)) throw new IllegalArgumentException(packetAsByteArray.length + " is an invalid byte length");
		return packetByteArrayToMessage(packetAsByteArray);
	}
	
	private boolean packetIsInvalidLength(byte[] packetAsByteArray){
		return(packetAsByteArray.length != NUM_OF_BYTES_IN_PACKET 
				&& packetAsByteArray.length != NUM_OF_BYTES_IN_PACKET_DETAILS);
	}
	
	private String packetByteArrayToMessage(byte[] packetAsByteArray){
		return translate(parseDetailsIntFromByteArray(packetAsByteArray));
	}
	
	private int parseDetailsIntFromByteArray(byte[] packetAsByteArray){
		StringBuilder parsedIntFromByteArray = new StringBuilder();
		int packetEndIndex = packetAsByteArray.length - 1;
		int packetBeginningIndex = packetAsByteArray.length - NUM_OF_BYTES_IN_PACKET_DETAILS;
		for(int i = packetBeginningIndex; i <= packetEndIndex; i++){
			parsedIntFromByteArray.append((byte)packetAsByteArray[i]);
		}
		return Integer.parseInt(parsedIntFromByteArray.toString());
	}
}
