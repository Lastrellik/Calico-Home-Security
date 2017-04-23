package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import module_PI.Raspberry_PI.main.SerialOutput;

public class SerialOutputTest {
	SerialOutput output = new SerialOutput();
	
	@Test
	public void testSerialOutput() {
		assertEquals(output.getPreviousBuffer(), null);
	}

	@Test
	public void testSendArmPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,1}; // 32001 = Command, Warn, Execute Command: Arm
		output.sendArmPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));		
	}

	@Test
	public void testSendDisarmPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,2};// 32002 = Command, Warn, Execute Command: Disarm
		output.sendDisarmPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendSilencePacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,3};// 32003 = Command, Warn, Execute Command: Silence
		output.sendSilencePacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendCalibratePacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,4};// 32004 = Command, Warn, Execute Command: Calibrate
		output.sendCalibratePacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendTriggerPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,5};// 32005 = Command, Warn, Execute Command: Trigger
		output.sendTriggerPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendResetCalibrationPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,6};// 32006 = Command, Warn, Execute Command: ResetCalibration
		output.sendResetCalibrationPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}
	
	@Test
	public void testSendTestComponentsPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,7};// 32007 = Command, Warn, Execute Command: TestComponents
		output.sendTestComponentsPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendTripPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,8};// 32008 = Command, Warn, Execute Command: Trip
		output.sendTripPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	} 
	
	@Test
	public void testSendToggleLaserPacket() {
		byte[] expectedReturn = new byte[]{3,2,0,0,9};// 32009 = Command, Warn, Execute Command: Toggle Laser
		output.sendTripPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	} 

}
