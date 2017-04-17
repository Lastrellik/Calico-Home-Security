package module_PI.Raspberry_PI.main;

import java.io.*;
import java.util.logging.*;

public class ActivityLogger {
	//http://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
	private Logger javaLogger = Logger.getLogger(ActivityLogger.class.getName());
	private FileHandler fileHandler;
	private String logFileName;
	private String previousLogEntry;
	private PacketLogLevel logLevel;
	
	public ActivityLogger(String logFileName){
		this.logFileName = logFileName;
		setFormatForLogMessages();
		setDefaultLogLevel();
		buildJavaLogger();
	}
	
	private void buildJavaLogger(){
		try {
			fileHandler = new FileHandler(logFileName, true);
			javaLogger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setFormatForLogMessages(){
		System.setProperty("java.util.logging.SimpleFormatter.format", 
	            "%1$tF %1$tT %5$s%6$s%n");
	}
	
	private void setDefaultLogLevel(){
		this.logLevel = PacketLogLevel.TRACE;
	}
	
	public void log(PacketLogLevel logLevel, PacketType packetType, String message){
		if(isLoggingAtOrBelowThisLevel(logLevel)){
			String logEntry = "Level:" + logLevel + " Type:" + packetType + "\t" + message;
			log(logEntry);
		}
	}	
	
	public void log(DataPacket packet){
		log(packet.getPacketLogLevel(), packet.getPacketType(), packet.getMessage());
	}
	
	public void log(String messageToLog){
		javaLogger.info(messageToLog);
		setPreviousLogEntry(messageToLog);
	}
	
	private boolean isLoggingAtOrBelowThisLevel(PacketLogLevel logLevel){
		return (logLevel.getByte() <= this.logLevel.getByte());
	}
	
	private void setPreviousLogEntry(String previousLogEntry){
		this.previousLogEntry = previousLogEntry;
	}
	
	public PacketLogLevel getLogLevel(){
		return logLevel;
	}
	
	public void setLogLevel(PacketLogLevel logLevel){
		this.logLevel = logLevel;
	}
	
	public String getLogFileName(){
		return logFileName;
	}
	
	public void setLogFileName(String newFileName){
		this.logFileName = newFileName;
		buildJavaLogger();
	}
	
	public String getPreviousLogEntry(){
		return previousLogEntry;
	}
}
