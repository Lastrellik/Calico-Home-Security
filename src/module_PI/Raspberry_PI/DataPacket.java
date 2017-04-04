package module_PI.Raspberry_PI;

import java.util.Arrays;

public class DataPacket {
	private byte[] packetCodeByteArray;
	private int packetCodeInt;
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;
	private final int PACKET_TYPE_INDEX = 0;
	private final int LOG_LEVEL_INDEX = 1;
	private PacketLogLevel packetLogLevel;
	private PacketType packetType;

	public DataPacket(int packetContents) {
		packetCodeByteArray = new byte[SIZE_OF_DATAPACKET_IN_BYTES];
		packetCodeInt = packetContents;
		parseIntToByteArray(packetContents, SIZE_OF_DATAPACKET_IN_BYTES - 1);
		parseTypeAndLogLevelFromByteArray();
	}
	
	private void parseIntToByteArray(int packetCode, int index){
		if(index < 0) return;
		packetCodeByteArray[index] = (byte) (packetCode % 10);
		parseIntToByteArray(packetCode / 10, index - 1);
	}

	public DataPacket(byte[] packetContents) {
		packetCodeByteArray = Arrays.copyOf(packetContents, packetContents.length);
		packetCodeInt = convertByteArrayToInt(packetContents);
		parseTypeAndLogLevelFromByteArray();
	}
	
	private int convertByteArrayToInt(byte[] packetContents){
		int total = 0;
		int multiplier = (int) Math.pow(10,  SIZE_OF_DATAPACKET_IN_BYTES - 1);
		for(int i = 0; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
			total += packetContents[i] * multiplier;
			multiplier /= 10;
		}
		return total;
	}
	
	private void parseTypeAndLogLevelFromByteArray(){
		if(packetCodeByteArray == null) throw new NullPointerException("packetCodeByteArray hasn't been determined yet.");
		parsePacketTypeFromByteArray();
		parseLogLevelFromByteArray();
	}
	
	private void parsePacketTypeFromByteArray(){
		packetType = PacketType.getPacketTypeFromByte(packetCodeByteArray[PACKET_TYPE_INDEX]);		
	}
	
	private void parseLogLevelFromByteArray(){
		packetLogLevel = PacketLogLevel.getLogLevelFromByte(packetCodeByteArray[LOG_LEVEL_INDEX]);
	}
	
	public int getSizeInBytes(){
		return SIZE_OF_DATAPACKET_IN_BYTES;
	}
	
	public byte[] getPacketAsArray(){
		return packetCodeByteArray;
	}
	
	public int getPacketAsInt(){
		return packetCodeInt;
	}
	
	public PacketLogLevel getPacketLogLevel(){
		return packetLogLevel;
	}
	
	public PacketType getPacketType(){
		return packetType;
	}
}
