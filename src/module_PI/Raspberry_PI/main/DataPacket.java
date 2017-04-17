package module_PI.Raspberry_PI.main;

import java.util.Arrays;

public class DataPacket {
	private static PacketMessageTranslator packetMessageTranslator = new PacketMessageTranslator();
	private String message;
	private byte[] packetCodeByteArray;
	private int packetCodeInt;
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;
	private final int PACKET_TYPE_INDEX = 0;
	private final int LOG_LEVEL_INDEX = 1;
	private final int NUM_OF_MESSAGE_BYTES_IN_PACKET = 3;
	private PacketLogLevel packetLogLevel;
	private PacketType packetType;

	public DataPacket(int packetContents) {
		if(packetContents < 10000 || packetContents > 99999){
			throw new IllegalArgumentException("packetContents invalid length");
		}
		packetCodeByteArray = new byte[SIZE_OF_DATAPACKET_IN_BYTES];
		packetCodeInt = packetContents;
		parseIntToByteArray(packetContents, SIZE_OF_DATAPACKET_IN_BYTES - 1);
		parseTypeAndLogLevelFromByteArray();
		parseMessage();
	}
	
	private void parseIntToByteArray(int packetCode, int index){
		if(index < 0) return;
		packetCodeByteArray[index] = (byte) (packetCode % 10);
		parseIntToByteArray(packetCode / 10, index - 1);
	}

	public DataPacket(byte[] packetContents) {
		if(packetContents.length != SIZE_OF_DATAPACKET_IN_BYTES){
			throw new IllegalArgumentException("packetContents invalid length");
		}
		packetCodeByteArray = Arrays.copyOf(packetContents, packetContents.length);
		packetCodeInt = convertByteArrayToInt(packetContents);
		parseTypeAndLogLevelFromByteArray();
		parseMessage();
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
	
	private void parseMessage(){
		int packetInt_modThisInt_isMessageInt = (int) Math.pow(10, NUM_OF_MESSAGE_BYTES_IN_PACKET);
		int messageInt = this.getPacketAsInt() % packetInt_modThisInt_isMessageInt;
		this.message = packetMessageTranslator.translate(messageInt);
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
	
	public String getMessage(){
		return this.message;
	}
}
