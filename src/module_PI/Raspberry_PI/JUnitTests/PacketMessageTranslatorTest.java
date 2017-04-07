package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import module_PI.Raspberry_PI.main.PacketMessageTranslator;

public class PacketMessageTranslatorTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testTranslateFromInt() {
		int executeCommandInt = 1;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		assertEquals(translator.translate(executeCommandInt), "Execute Command: Arm");
	}
	
	@Test
	public void testTranslateFromInt_TooHigh() {
		int tooLargeDetailInt = 100000;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("invalid packet int: 100000");
		translator.translate(tooLargeDetailInt);
	}
	
	@Test
	public void testTranslateFromInt_TooLow() {
		int tooLowDetailInt = 0;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("invalid packet int: 0");
		translator.translate(tooLowDetailInt);
	}
	
	@Test
	public void testTranslateFromInt_BetweenDetailsAndFullLength() {
		int intBetweenDetailsAndFullLength = 8000;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("invalid packet int: 8000");
		translator.translate(intBetweenDetailsAndFullLength);
	}
	
	@Test
	public void testTranslateFromInt_FullPacketLength(){
		int intBetweenDetailsAndFullLength = 11001;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		String expected = "Execute Command: Arm";
		String actual = translator.translate(intBetweenDetailsAndFullLength);
		assertEquals(expected, actual);
	}
	

	@Test
	public void testTranslateFromInt_MessageNotFound() {
		int unmappedInt = 999;
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("packetDetails 999 not in messageTable");
		translator.translate(unmappedInt);
	}
	
	@Test
	public void testTranslateFromString_IntegerString(){
		String integerString = "001";
		PacketMessageTranslator translator = new PacketMessageTranslator();
		assertEquals(translator.translate(integerString), "Execute Command: Arm");
	}
	
	@Test
	public void testTranslateFromString_IntegerFromStringTooLong(){
		String tooLongString = "100001";
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("invalid packet int: 100001");
		translator.translate(tooLongString);
	}
	
	@Test
	public void testTranslateFromString_IntegerFromStringTooShort(){
		String tooShortString = "0";
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("invalid packet int: 0");
		translator.translate(tooShortString);
	}
	
	@Test
	public void testTranslateFromString_NotAnInteger(){
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("NotAnInteger is not an integer");
		translator.translate("NotAnInteger");
	}
	
	@Test
	public void testTranslateFromByteArray(){
		byte[] validByteArray = {1, 1, 0, 0, 1};
		PacketMessageTranslator translator = new PacketMessageTranslator();
		assertEquals(translator.translate(validByteArray), "Execute Command: Arm");
	}
	
	@Test
	public void testTranslateFromByteArray_MessageDetailsOnly(){
		byte[] messageDetailsByteArray = {0, 0, 1};
		PacketMessageTranslator translator = new PacketMessageTranslator();
		assertEquals(translator.translate(messageDetailsByteArray), "Execute Command: Arm");
	}
	
	@Test
	public void testTranslateFromByteArray_LongLength(){
		byte[] longLengthByteArray = {1, 1, 1, 0, 0, 1};
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("6 is an invalid byte length");
		translator.translate(longLengthByteArray);
	}
	
	@Test 
	public void testTranslateFromByteArray_ShortLength(){
		byte[] shortLengthByteArray = {0, 1};
		PacketMessageTranslator translator = new PacketMessageTranslator();
		expectedEx.expectMessage("2 is an invalid byte length");
		translator.translate(shortLengthByteArray);
	}
	
}
