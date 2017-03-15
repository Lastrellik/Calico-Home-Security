package module_PI.Raspberry_PI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataPacket {

	private final int SIZE_OF_SHA1_IN_BYTES = 8;
	private final int SIZE_OF_DATAPACKET_IN_BYTES = 64;
	private int arrayCounter = 1;
	private byte[] sha1MessageHash;
	private byte[] packetAsArray;
	private String messageString;
	private PacketType packetType;
	protected MessageDigest digest;

	public DataPacket(String message, PacketType packetType) {
		this.messageString = message.toUpperCase();
		this.packetType = packetType;
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
		setSha1FromRawPacket(packetContents);
		setPacketTypeFromRawPacket(packetContents);
		setMessageFromRawPacket(packetContents);
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
	
	PacketType getPacketType(){
		  return packetType;
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
		append(packetType.getByte());
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

	private void setSha1FromRawPacket(byte[] packetContents) {
		for (int i = 0; i < SIZE_OF_SHA1_IN_BYTES; i++) {
			sha1MessageHash[i] = packetContents[i + 1];
		}
	}

	private void setPacketTypeFromRawPacket(byte[] packetContents) {
		byte packetTypeBytePositionInArray = SIZE_OF_SHA1_IN_BYTES + 1;
		packetType = PacketType.getPacketTypeFromByte(packetContents[packetTypeBytePositionInArray]);
	}

	private void setMessageFromRawPacket(byte[] packetContents) {
		  for(int i = SIZE_OF_SHA1_IN_BYTES + 2; i < SIZE_OF_DATAPACKET_IN_BYTES; i++){
			    messageString += (char)packetContents[i];		
		  }
	}
}
