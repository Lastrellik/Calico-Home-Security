package module_PI.Raspberry_PI;

import java.util.Arrays;

public class DataPacket {
	private byte[] packetCodeByteArray;
	private int packetCodeInt;
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 5;

	public DataPacket(int packetContents) {
		packetCodeByteArray = new byte[SIZE_OF_DATAPACKET_IN_BYTES];
		packetCodeInt = packetContents;
		parseIntToByteArray(packetContents, SIZE_OF_DATAPACKET_IN_BYTES - 1);
	}
	
	private void parseIntToByteArray(int packetCode, int index){
		if(index < 0) return;
		packetCodeByteArray[index] = (byte) (packetCode % 10);
		parseIntToByteArray(packetCode / 10, index - 1);
	}

	public DataPacket(byte[] packetContents) {
		packetCodeByteArray = Arrays.copyOf(packetContents, packetContents.length);
		packetCodeInt = convertByteArrayToInt(packetContents);
	}
	
	private int convertByteArrayToInt(byte[] packetContents){
		int total = 0;
		int multiplier = 10000;
		for(int i = 0; i < 5; i++){
			total += packetContents[i] * multiplier;
			multiplier /= 10;
		}
		return total;
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
}
