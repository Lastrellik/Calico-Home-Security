package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import module_PI.Raspberry_PI.main.SerialInputListener;

public class SerialInputListenerTest {

	@Test
	public void testIsPacketAvailable() {
		SerialInputListener inputListener = new SerialInputListener();
		assertFalse(inputListener.isPacketAvailable());
	}

	@Test
	public void testIsDataStreamEmpty() {
		SerialInputListener inputListener = new SerialInputListener();
		assertTrue(inputListener.isDataStreamEmpty());
	}

/*TODO Have Arduino Respond To Ping Request, then write this test.
	@Test
	public void testGetDataPacket() {
		fail("Not yet implemented"); // TODO
	}
	*/
	@Test
	public void testRun() {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();	
		assertTrue(listener.isAlive());
	}

}
