package module_PI.Raspberry_PI.main;

import java.io.*;
import java.util.*;

public class PiApp {

	/**
	 * @param args Gmail Username and Password for sending mail
	 */
	public static void main(String[] args) {
		parseArgs(args);
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();		
		SerialOutput output = new SerialOutput();
		
		pause(10000);
		output.sendArmPacket();
	}
	
	private static void parseArgs(String[] args){
		Properties gmailCredentials = new Properties();
		gmailCredentials.put("gmailUsername" , args[0]);
		gmailCredentials.put("gmailPassword" , args[1]);
		saveGmailProperties(gmailCredentials);
		
	}
	private static void saveGmailProperties(Properties gmailCredentials){
		try {
			FileOutputStream out = new FileOutputStream("gmailCredentials");
			gmailCredentials.store(out, "Gmail Credentials");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void pause(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
