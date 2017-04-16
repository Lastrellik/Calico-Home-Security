package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import module_PI.Raspberry_PI.main.*;

public class ActivityLoggerTest {
	
	@Rule
	public ExpectedException ExpectedEx = ExpectedException.none();

	public static ActivityLogger logger;
	
	@BeforeClass
	public static void setupLogger(){
		logger = new ActivityLogger("TestLog.txt");
	}
	
	@Test
	public void testActivityLogger(){
		assertEquals(System.getProperty("java.util.logging.SimpleFormatter.format"), 
				"%1$tF %1$tT %5$s%6$s%n");
	}
	
	@Test
	public void testActivityLogger_DefaultLogLevel(){
		assertEquals(logger.getLogLevel(), PacketLogLevel.TRACE);
	}
	
	@Test
	public void testLog(){
		logger.log(PacketLogLevel.TRACE, PacketType.DATA, "Trace Data message");
		assertEquals(logger.getPreviousLogEntry(), "Level:TRACE Type:DATA\tTrace Data message");
	}
	
	@Test
	public void testLog_AboveLogLevel(){
		PacketLogLevel originalLogLevel = logger.getLogLevel();
		logger.log(PacketLogLevel.TRACE, PacketType.DATA, "Trace Data message");
		logger.setLogLevel(PacketLogLevel.ERROR);
		logger.log(PacketLogLevel.TRACE, PacketType.DATA, "Shouldn't get logged");
		assertEquals(logger.getPreviousLogEntry(), "Level:TRACE Type:DATA\tTrace Data message");
		logger.setLogLevel(originalLogLevel);
	}
	
	@Test
	public void testLog_EqualLogLevel(){
		PacketLogLevel originalLogLevel = logger.getLogLevel();
		logger.setLogLevel(PacketLogLevel.ERROR);
		logger.log(PacketLogLevel.ERROR, PacketType.DATA, "Error Data message");
		assertEquals(logger.getPreviousLogEntry(), "Level:ERROR Type:DATA\tError Data message");
		logger.setLogLevel(originalLogLevel);
	}
	
	@Test
	public void testLog_BelowLogLevel(){
		logger.log(PacketLogLevel.ERROR, PacketType.DATA, "Error Data message");
		assertEquals(logger.getPreviousLogEntry(), "Level:ERROR Type:DATA\tError Data message");
	}
	
	@Test
	public void testLog_FromDataPacket(){
		logger.log(new DataPacket(11100));
		assertEquals(logger.getPreviousLogEntry(), "Level:ERROR Type:LOG\tAlarm object successfully created");
	}
	
	@Test
	public void testLog_FromString(){
		logger.log("This is a test log");
		assertEquals(logger.getPreviousLogEntry(), "This is a test log");
	}
	
	@Test
	public void testGetPreviousLogEntry(){
		logger.log(PacketLogLevel.DEBUG, PacketType.LOG, "Debug Log entry");
		assertEquals(logger.getPreviousLogEntry(), "Level:DEBUG Type:LOG\tDebug Log entry");
	}
	
	@Test
	public void testGetLogLevel(){
		PacketLogLevel originalLogLevel = logger.getLogLevel();
		logger.setLogLevel(PacketLogLevel.DEBUG);
		assertEquals(logger.getLogLevel(), PacketLogLevel.DEBUG);
		logger.setLogLevel(originalLogLevel);
	}
	
	@Test
	public void testGetLogFileName(){
		assertEquals(logger.getLogFileName(), "TestLog.txt");
	}

}
