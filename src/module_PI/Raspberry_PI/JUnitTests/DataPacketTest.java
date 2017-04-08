package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import module_PI.Raspberry_PI.main.DataPacket;
import module_PI.Raspberry_PI.main.PacketLogLevel;
import module_PI.Raspberry_PI.main.PacketType;

public class DataPacketTest {

	@Test
	public void testDataPacketInt() {
		int packetInt = 54258;
		DataPacket packet = new DataPacket(packetInt);
		assertEquals(packet.getPacketAsInt(), packetInt);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_invalidLength_tooLong(){
		DataPacket zeroLevelPacket = new DataPacket(505425);
	}	
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_invalidLength_tooShort(){
		DataPacket zeroLevelPacket = new DataPacket(5425);
	}	
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_zeroLogLevel(){
		DataPacket zeroLevelPacket = new DataPacket(50542);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_highLogLevel(){
		DataPacket zeroLevelPacket = new DataPacket(56542);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_zeroPacketType(){
		DataPacket zeroPacketTypePacket = new DataPacket(05364);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketInt_highPacketType(){
		DataPacket zeroPacketTypePacket = new DataPacket(65364);
	}

	
	@Test
	public void testDataPacketByteArrayFromByteArray() {
		byte[] packetArray = {1,2,3,4,5};
		DataPacket packet = new DataPacket(packetArray);
		assertTrue(Arrays.equals(new byte[] {1,2,3,4,5}, packet.getPacketAsArray()));	
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testDataPacketByteArrayFromByteArray_invalidLength() {
		byte[] packetArray = {1,2,3,4,5,6};
		DataPacket packet = new DataPacket(packetArray);	
	}
	
	@Test
	public void testDataPacketByteArrayFromInt() {
		DataPacket packet = new DataPacket(12345);
		assertTrue(Arrays.equals(new byte[] {1,2,3,4,5}, packet.getPacketAsArray()));	
	}

	@Test
	public void testGetSizeInBytes() {
		DataPacket packet = new DataPacket(32123);
		assertEquals(packet.getSizeInBytes(), 5);
	}

	@Test
	public void testGetPacketAsArray() {
		byte[] packetByteArray = {3,2,1,2,3};
		DataPacket packet = new DataPacket(32123);
		assertTrue(Arrays.equals(packetByteArray, packet.getPacketAsArray()));
	}

	@Test
	public void testGetPacketAsIntFromInt() {
		DataPacket packet = new DataPacket(12345);
		assertEquals(packet.getPacketAsInt(), 12345);
	}
	
	@Test
	public void testGetPacketAsIntFromByteArray() {
		byte[] packetByteArray = {1,2,3,4,5};
		DataPacket packet = new DataPacket(packetByteArray);
		assertEquals(packet.getPacketAsInt(), 12345);
	}

	@Test
	public void testGetPacketLogLevel() {
		DataPacket packet = new DataPacket(12345);
		assertEquals(packet.getPacketLogLevel(), PacketLogLevel.WARN);
	}

	@Test
	public void testGetPacketType() {
		DataPacket packet = new DataPacket(12345);
		assertEquals(packet.getPacketType(), PacketType.LOG);
		
	}

}
