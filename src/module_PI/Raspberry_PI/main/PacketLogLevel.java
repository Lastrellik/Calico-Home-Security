package module_PI.Raspberry_PI.main;

public enum PacketLogLevel {
	ERROR((byte)1), WARN((byte)2), DEBUG((byte)3), INFO((byte)4), TRACE((byte)5);
	
	private byte packetLogLevelByte;
	private String[] packetTypeStrings = {"ERROR", "WARN", "DEBUG", "INFO", "TRACE"};

	private PacketLogLevel(byte b){
		packetLogLevelByte = b;
	}
	
	public static PacketLogLevel getLogLevelFromByte(byte b){
		for(PacketLogLevel p : PacketLogLevel.values()){
			if(p.getByte() == b) return p;
		} 
		throw new IllegalArgumentException("Byte " + b + " doesn't represent LogLevel");
	}
	
	public byte getByte(){
		return packetLogLevelByte;
	}
	
	@Override
	public String toString(){
		int packetTypeStringIndex = packetLogLevelByte - 1;
		return packetTypeStrings[packetTypeStringIndex];
	}
}
