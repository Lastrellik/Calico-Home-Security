package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import module_PI.Raspberry_PI.main.PacketLogLevel;

public class PacketLogLevelTest {

	@Test
	public void testErrorByte() {
		assertEquals(PacketLogLevel.ERROR.getByte(), 1);
	}
	
	@Test
	public void testWarnByte() {
		assertEquals(PacketLogLevel.WARN.getByte(), 2);
	}
	
	@Test
	public void testDebugByte() {
		assertEquals(PacketLogLevel.DEBUG.getByte(), 3);
	}
	
	@Test
	public void testInfoByte() {
		assertEquals(PacketLogLevel.INFO.getByte(), 4);
	}
	
	@Test
	public void testTraceByte() {
		assertEquals(PacketLogLevel.TRACE.getByte(), 5);
	}
	
	@Test
	public void testErrorToString() {
		assertEquals(PacketLogLevel.ERROR.toString(), "ERROR");
	}
	
	@Test
	public void testWarnToString() {
		assertEquals(PacketLogLevel.WARN.toString(), "WARN");
	}
	
	@Test
	public void testDebugToString() {
		assertEquals(PacketLogLevel.DEBUG.toString(), "DEBUG");
	}
	
	@Test
	public void testInfoToString() {
		assertEquals(PacketLogLevel.INFO.toString(), "INFO");
	}
	
	@Test
	public void testTraceToString() {
		assertEquals(PacketLogLevel.TRACE.toString(), "TRACE");
	}

}
