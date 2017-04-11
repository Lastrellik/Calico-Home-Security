package module_PI.Raspberry_PI.main;

import java.io.*;
import java.util.*;

public class PiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parseArgs(args);
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();		
		SerialOutput output = new SerialOutput();
    	EmailNotifier notifier = new EmailNotifier("lastrellik@gmail.com", "Today's message");
    	notifier.sendEmail();
		
		pause(10000);
		output.sendArmPacket();
	}
	
	private static void parseArgs(String[] args){
		int numOfArguments = 2;
		if(args.length != numOfArguments) throw new IllegalArgumentException("Syntax is java PiApp <username> <password>");
		parseGmailCredentials(args[0], args[1]);
	}
	
	private static void parseGmailCredentials(String username, String password){
		Properties gmailCredentials = new Properties();
		gmailCredentials.put("gmailUsername" , username);
		gmailCredentials.put("gmailPassword" , password);
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
