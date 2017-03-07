/**
 * Salt Lake Community College
 * Spring 2017 Semester
 * CSIS-2810 Computer Architecture
 * Instructor: Jon McGowan
 * 
 */

/*package module_TOUCHSCREEN;

import com.fazecast.jSerialComm.*;

public class TouchscreenBackEnd {
	public static void main(String[] args) throws InterruptedException{
		SerialPort comPort = SerialPort.getCommPorts()[1];
		byte[] readBuffer;
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
		try {
		   while (true){
			   if(comPort.bytesAvailable() > 0){
				   Thread.sleep(100);
				   readBuffer = new byte[comPort.bytesAvailable()];
				   int numRead = comPort.readBytes(readBuffer, readBuffer.length);
				   System.out.println(new String(readBuffer));
				   
			   }
			  
			  //comPort.writeBytes(new byte[] {1, 2, 3, 4, 5, 6}, 1);
		      Thread.sleep(1000);
		      //System.out.println("Read " + numRead + " bytes.");
		   }
		} catch (Exception e) { e.printStackTrace(); }
		comPort.closePort();
	}
}*/
