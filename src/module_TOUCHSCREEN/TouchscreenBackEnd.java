/**
 * Salt Lake Community College
 * Spring 2017 Semester
 * CSIS-2810 Computer Architecture
 * Instructor: Jon McGowan
 * 
 */

package module_TOUCHSCREEN;

import com.fazecast.jSerialComm.*;

public class TouchscreenBackEnd {
	public static void main(String[] args) throws InterruptedException{
		SerialPort comPort = SerialPort.getCommPorts()[1];
		byte[] readBuffer;
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
		try {
		   while (true)
		   {
			  //comPort.writeBytes(new byte[] {1, 2, 3, 4, 5, 6}, 1);
		      Thread.sleep(1000);
		      readBuffer = new byte[comPort.bytesAvailable()];
		      int numRead = comPort.readBytes(readBuffer, readBuffer.length);
		      System.out.println("Read " + numRead + " bytes.");
		      System.out.println(new String(readBuffer));
		   }
		} catch (Exception e) { e.printStackTrace(); }
		comPort.closePort();
	}
}
