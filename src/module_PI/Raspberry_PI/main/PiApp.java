package module_PI.Raspberry_PI.main;

import java.util.*;

public class PiApp {
	public static Properties GMAIL_CREDENTIALS = new Properties();
	private static ActivityLogger activityLogger = new ActivityLogger("PI_Log.txt");
	private static String[] emailRecipients;

	/**
	 * @param args Gmail Username and Password for sending mail
	 */
	public static void main(String[] args) {
		int numOfArgs = 2;
		if(args.length <= numOfArgs) throw new IllegalArgumentException("Syntax is java PiApp <gmailusername> <gmailpassword> <recipientEmailAddress>...");
		parseArgs(args);
		LogToFile("PI startup application");
		SerialInputListener inputListener = new SerialInputListener();
		PacketProcessor packetProcessor = new PacketProcessor(inputListener, emailRecipients);
		Thread listener = new Thread(inputListener, "Listener");
		Thread processor = new Thread(packetProcessor, "Processor");
		listener.start();
		processor.start();
	}
	
	private static void parseArgs(String[] args){
		int numOfRecipients = args.length - 2;
		int recipientIndex = 0;
		emailRecipients = new String[numOfRecipients];
		GMAIL_CREDENTIALS.put("gmailUsername" , args[0]);
		GMAIL_CREDENTIALS.put("gmailPassword" , args[1]);
		for(int i = 2; i < args.length; i++){
			emailRecipients[recipientIndex++] = args[i];
		}
	}
	
	public static void LogToFile(DataPacket packetToLog){
		activityLogger.log(packetToLog);
	}
	
	public static void LogToFile(String messageToLog){
		activityLogger.log(messageToLog);
	}

}
