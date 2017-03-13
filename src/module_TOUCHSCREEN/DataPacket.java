package module_TOUCHSCREEN;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataPacket {

	private final int SIZE_OF_SHA1_IN_BYTES = 8;
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 64;
	private int arrayCounter = 1;
	private byte[] sha1MessageHash;
	private byte[] packetAsArray;
	private String messageString;
	private String packetTypeString;
	protected MessageDigest digest;

	public DataPacket(String message, String packetType) {
		this.messageString = message.toUpperCase();
		this.packetTypeString = packetType.toUpperCase();
		sha1MessageHash = new byte[SIZE_OF_SHA1_IN_BYTES];
		packetAsArray = new byte[SIZE_OF_DATAPACKET_IN_BYTES];
		packetAsArray[0] = '~';
		initializeMessageDigestForSha1();
		appendPacketHeader();
		appendMessage();
	}

	public DataPacket(byte[] packetContents) {
		sha1MessageHash = new byte[SIZE_OF_SHA1_IN_BYTES];
		messageString = "";
		initializeMessageDigestForSha1();
		this.packetAsArray = packetContents;
		parseSha1FromRawPacket(packetContents);
		parsePacketTypeFromRawPacket(packetContents);
		parseMessageFromRawPacket(packetContents);
	}

	public byte[] getPacketAsArray() {
		return packetAsArray;
	}

	public byte[] getPacketSha1Hash() {
		return sha1MessageHash;
	}

	public int getSizeInBytes() {
		return SIZE_OF_DATAPACKET_IN_BYTES;
	}
	
	String getPacketType(){
		  return packetTypeString;
	}

	String getMessage(){
	  return messageString;
	}

	private void appendPacketHeader() {
		appendHash();
		appendPacketTypeByte();
	}

	private void initializeMessageDigestForSha1() {
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private void appendHash() {
		byte[] sha1Hash = digest.digest(messageString.getBytes());
		for (int i = 0; i < SIZE_OF_SHA1_IN_BYTES; i++) {
			this.sha1MessageHash[i] = sha1Hash[i];
		}
		append(sha1MessageHash);
	}

	private void appendPacketTypeByte() {
		byte packetTypeByte = 0;
		if (packetTypeString == "LOG") {
			packetTypeByte = 0;
		} else if (packetTypeString == "COMMAND") {
			packetTypeByte = 1;
		} else if (packetTypeString == "INFO") {
			packetTypeByte = 2;
		} else if (packetTypeString == "REQUEST"){
			packetTypeByte = 3;
		}
		append(packetTypeByte);
	}

	private void appendMessage() {
		append(messageString.getBytes());
	}

	private void append(byte[] rawData) {
		for (int i = 0; i < rawData.length; i++) {
			append(rawData[i]);
		}
	}

	private void append(byte singleRawByte) {
		packetAsArray[arrayCounter++] = singleRawByte;
	}

	private void parseSha1FromRawPacket(byte[] packetContents) {
		for (int i = 0; i < SIZE_OF_SHA1_IN_BYTES; i++) {
			sha1MessageHash[i] = packetContents[i + 1];
		}
	}

	private void parsePacketTypeFromRawPacket(byte[] packetContents) {
		byte packetTypeBytePositionInArray = SIZE_OF_SHA1_IN_BYTES + 1;
		switch (packetContents[packetTypeBytePositionInArray]) {
			case 0:
				packetTypeString = "LOG";
				break;
			case 1:
				packetTypeString = "COMMAND";
				break;
			case 2:
				packetTypeString = "INFO";
				break;
			case 3:
				packetTypeString = "REQUEST";
				break;
		}
	}

	private void parseMessageFromRawPacket(byte[] packetContents) {
		  for(int i = SIZE_OF_SHA1_IN_BYTES + 2; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
			    messageString += (char)packetContents[i];		
		  }
	}
}
