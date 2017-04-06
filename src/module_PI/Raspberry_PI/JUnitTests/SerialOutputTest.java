package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import module_PI.Raspberry_PI.main.SerialOutput;

public class SerialOutputTest {
	
	@Test
	public void testSerialOutput() {
		SerialOutput output = new SerialOutput();
		assertEquals(output.getPreviousBuffer(), null);
	}

	@Test
	public void testSendArmPacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,1}; // 30001 = Command, NA, Execute Command: Arm
		output.sendArmPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));		
	}

	@Test
	public void testSendDisarmPacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,2};// 30002 = Command, NA, Execute Command: Disarm
		output.sendDisarmPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendSilencePacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,3};// 30003 = Command, NA, Execute Command: Silence
		output.sendSilencePacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendCalibratePacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,4};// 30004 = Command, NA, Execute Command: Calibrate
		output.sendCalibratePacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendTriggerPacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,5};// 30005 = Command, NA, Execute Command: Trigger
		output.sendTriggerPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

	@Test
	public void testSendResetCalibrationPacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,6};// 30006 = Command, NA, Execute Command: ResetCalibration
		output.sendResetCalibrationPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}
	
	@Test
	public void testSendTestComponentsPacket() {
		SerialOutput output = new SerialOutput();
		byte[] expectedReturn = new byte[]{3,0,0,0,7};// 30007 = Command, NA, Execute Command: TestComponents
		output.sendTestComponentsPacket();
		assertTrue(Arrays.equals(output.getPreviousBuffer(), expectedReturn));
	}

}
