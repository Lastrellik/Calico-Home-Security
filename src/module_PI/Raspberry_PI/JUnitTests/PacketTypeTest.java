package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import module_PI.Raspberry_PI.main.PacketType;

public class PacketTypeTest {

	@Test
	public void testLogByte() {
		assertEquals(PacketType.LOG.getByte(), 1);
	}
	
	@Test
	public void testDataByte() {
		assertEquals(PacketType.DATA.getByte(), 2);
	}
	
	@Test
	public void testCommandByte() {
		assertEquals(PacketType.COMMAND.getByte(), 3);
	}
	
	@Test
	public void testErrorByte() {
		assertEquals(PacketType.ERROR.getByte(), 4);
	}
	
	@Test
	public void testRequestByte() {
		assertEquals(PacketType.REQUEST.getByte(), 5);
	}
	
	@Test
	public void testLogByteToString() {
		assertEquals(PacketType.LOG.toString(), "LOG");
	}
	
	@Test
	public void testDataByteToString() {
		assertEquals(PacketType.DATA.toString(), "DATA");
	}
	
	@Test
	public void testCommandByteToString() {
		assertEquals(PacketType.COMMAND.toString(), "COMMAND");
	}
	
	@Test
	public void testErrorByteToString() {
		assertEquals(PacketType.ERROR.toString(), "ERROR");
	}
	
	@Test
	public void testRequestByteToString() {
		assertEquals(PacketType.REQUEST.toString(), "REQUEST");
	}

}
