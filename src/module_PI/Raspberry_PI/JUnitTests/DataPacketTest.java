package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;
import org.junit.rules.ExpectedException;

import module_PI.Raspberry_PI.main.*;

public class DataPacketTest {

	DataPacket genericPacket = new DataPacket(12100);
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testDataPacketInt() {
		int packetInt = 54100;
		DataPacket packet = new DataPacket(packetInt);
		assertEquals(packet.getPacketAsInt(), packetInt);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_invalidLength_tooLong(){
		expectedEx.expectMessage("packetContents invalid length");
		DataPacket tooLongPacket = new DataPacket(511006);
	}	
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_invalidLength_tooShort(){
		expectedEx.expectMessage("packetContents invalid length");
		DataPacket tooShortPacket = new DataPacket(5425);
	}	
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_zeroLogLevel(){
		expectedEx.expectMessage("Byte 0 doesn't represent LogLevel");
		DataPacket zeroLevelPacket = new DataPacket(50100);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_highLogLevel(){
		expectedEx.expectMessage("Byte 6 doesn't represent LogLevel");
		DataPacket bigLogLevel = new DataPacket(56100);
	}

	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_zeroPacketType(){
		expectedEx.expectMessage("packetContents invalid length");
		DataPacket zeroPacketTypePacket = new DataPacket(05100);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketInt_highPacketType(){
		expectedEx.expectMessage("Byte 6 doesn't represent PacketType");
		DataPacket highPacketTypePacket = new DataPacket(65100);
	}

	
	@Test
	public void testDataPacketByteArrayFromByteArray() {
		byte[] packetArray = {1,2,1,0,0};
		DataPacket packet = new DataPacket(packetArray);
		assertTrue(Arrays.equals(new byte[] {1,2,1,0,0}, packet.getPacketAsArray()));	
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testDataPacketByteArrayFromByteArray_invalidLength() {
		expectedEx.expectMessage("packetContents invalid length");
		byte[] packetArray = {1,2,3,4,5,6};
		DataPacket packet = new DataPacket(packetArray);	
	}
	
	@Test
	public void testDataPacketByteArrayFromInt() {
		assertTrue(Arrays.equals(new byte[] {1,2,1,0,0}, genericPacket.getPacketAsArray()));	
	}

	@Test
	public void testGetSizeInBytes() {
		DataPacket packet = new DataPacket(32100);
		assertEquals(packet.getSizeInBytes(), 5);
	}

	@Test
	public void testGetPacketAsArray() {
		byte[] packetByteArray = {3,2,1,0,0};
		DataPacket packet = new DataPacket(32100);
		assertTrue(Arrays.equals(packetByteArray, packet.getPacketAsArray()));
	}

	@Test
	public void testGetPacketAsIntFromInt() {
		assertEquals(genericPacket.getPacketAsInt(), 12100);
	}
	
	@Test
	public void testGetPacketAsIntFromByteArray() {
		byte[] packetByteArray = {1,2,1,0,0};
		DataPacket packet = new DataPacket(packetByteArray);
		assertEquals(packet.getPacketAsInt(), 12100);
	}

	@Test
	public void testGetPacketLogLevel() {
		assertEquals(genericPacket.getPacketLogLevel(), PacketLogLevel.WARN);
	}

	@Test
	public void testGetPacketType() {
		assertEquals(genericPacket.getPacketType(), PacketType.LOG);
	}
	
	@Test
	public void testParseMessage() {
		DataPacket packetWithValidMessage = new DataPacket(11103);
		assertEquals("Alarm successful action", packetWithValidMessage.getMessage());
	}

	@SuppressWarnings("unused")
	@Test
	public void testParseMessage_messageNotInTable(){
		expectedEx.expectMessage("packetDetails 123 not in messageTable");
		DataPacket packetWithoutvalidMessage = new DataPacket(11123);
	}
	
}
