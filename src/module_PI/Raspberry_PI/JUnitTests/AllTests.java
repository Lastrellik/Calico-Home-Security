package module_PI.Raspberry_PI.JUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import module_PI.Raspberry_PI.main.SerialInputListener;

@RunWith(Suite.class)
@SuiteClasses({ DataPacketTest.class, PacketLogLevelTest.class, PacketTypeTest.class, SerialInputListenerTest.class,
		SerialOutputTest.class, PacketLogLevelTest.class, EmailNotifierTest.class, ActivityLoggerTest.class, CameraTest.class })
public class AllTests {
	SerialInputListener listener = new SerialInputListener();
}
