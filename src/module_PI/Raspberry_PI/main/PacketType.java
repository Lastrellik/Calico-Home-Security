package module_PI.Raspberry_PI.main;

public enum PacketType {
	LOG((byte)1), DATA((byte)2), COMMAND((byte)3), ERROR((byte)4), REQUEST((byte)5);
	
	private byte packetTypeByte;
	private String[] packetTypeStrings = {"LOG", "DATA", "COMMAND", "ERROR", "REQUEST"};
	
	private PacketType(byte b){
		packetTypeByte = b;
	}
	
	public static PacketType getPacketTypeFromByte(byte b){
		for(PacketType p : PacketType.values()){
			if(p.getByte() == b) return p;
		} 
		throw new IllegalArgumentException("Byte " + b + " doesn't represent PacketType");
	}
	
	public byte getByte(){
		return packetTypeByte;
	}
	
	@Override
	public String toString(){
		int packetTypeStringIndex = packetTypeByte - 1;
		return packetTypeStrings[packetTypeStringIndex];
	}
}
