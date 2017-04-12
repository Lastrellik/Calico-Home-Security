package module_PI.Raspberry_PI.main;

import java.util.*;

public class PiApp {
	public static Properties GMAIL_CREDENTIALS = new Properties();

	/**
	 * @param args Gmail Username and Password for sending mail
	 */
	public static void main(String[] args) {
		int numOfArgs = 2;
		if(args.length != numOfArgs) throw new IllegalArgumentException("Syntax is java PiApp <gmailusername> <gmailpassword>");
		parseArgs(args);
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();		
		SerialOutput output = new SerialOutput();
		
		pause(10000);
		output.sendArmPacket();
	}
	
	private static void parseArgs(String[] args){
		GMAIL_CREDENTIALS.put("gmailUsername" , args[0]);
		GMAIL_CREDENTIALS.put("gmailPassword" , args[1]);
	}
	
	private static void pause(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
