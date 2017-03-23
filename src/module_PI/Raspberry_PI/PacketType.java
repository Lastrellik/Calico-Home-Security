package module_PI.Raspberry_PI;

public enum PacketType {
	LOG((byte)0), COMMAND((byte)1), INFO((byte)2), REQUEST((byte)3), ERROR((byte)4);
	
	private byte packetTypeByte;
	private String[] packetTypeStrings = {"LOG", "COMMAND", "INFO", "REQUEST", "ERROR"};
	
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
		return packetTypeStrings[packetTypeByte];
	}
}
