package module_PI.Raspberry_PI.main;

import java.io.File;

public class PacketProcessor implements Runnable{
	private boolean isRunning = false;
	private SerialInputListener inputListener;
	private DataPacket currentPacket;
	private EmailNotifier emailNotifier;
	private Camera camera;
	private File pictureDirectory;
	private String[] recipientEmailAddresses;
	private String emailMessage = "";
	private File pictureFile;
	
	
	public PacketProcessor(SerialInputListener inputListener, String... recipientEmailAddresses){
		this.recipientEmailAddresses = recipientEmailAddresses;
		camera = new Camera("./PI_PICS");
		pictureDirectory = new File(camera.getFilePath());
		emailNotifier = new EmailNotifier(recipientEmailAddresses[0], "");
		this.inputListener = inputListener;
	}

	@Override
	public void run() {
		isRunning = true;
		while(true){
			processPacketIfAvailable();
			pause(100);
		}
	}
	
	private void processPacketIfAvailable(){
		if(inputListener.isPacketAvailable()){
			currentPacket = inputListener.getDataPacket();
			PiApp.LogToFile(currentPacket);
			if(isTripPacket(currentPacket)){
				alertEmailRecipientsOfTrip();
			}
			if(isTriggeredPacket(currentPacket)){
				alertEmailRecipientsOfBreach();
			}
		}
	}
	
	private boolean isTripPacket(DataPacket packet){
		return(packet.getMessage() == "Alarm has been tripped");
	}
	
	private boolean isTriggeredPacket(DataPacket packet){
		return (packet.getMessage() == "Alarm has been triggered");
	}
	
	private void alertEmailRecipientsOfTrip(){
		String tripEmailMessage = "This email is to inform you that your "
				+ "security system has been tripped. This isn't the same "
				+ "as being triggered, as the potential intruder still has "
				+ "60 seconds to disarm the alarm. If this is a real "
				+ "intruder, expect another email within the next minute.";
		this.emailMessage = tripEmailMessage;
		capturePicture();
		sendEmailToRecipients();
	}
	
	private void alertEmailRecipientsOfBreach(){
		String breachEmailMessage = "Your security system alarm has been "
				+ "triggered! Please see the attached photos for more "
				+ "information.";
		this.emailMessage = breachEmailMessage;
		capturePicture();
		sendEmailToRecipients();
	}
	
	private void capturePicture(){
		String pictureFileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
		camera.takePicture(pictureFileName);
		this.pictureFile = new File(pictureDirectory + "/" + pictureFileName);
	}
	
	private void sendEmailToRecipients(){
		emailNotifier.setEmailMessage(this.emailMessage);
		PiApp.LogToFile("PacketProcessor called emailNotifier.setAttachmentFilePath with argument " + this.pictureFile.getPath());
		emailNotifier.setAttachmentFilePath(this.pictureFile.getPath());
		for(String s : recipientEmailAddresses){
			emailNotifier.setRecipientEmailAddress(s);
			emailNotifier.sendEmail();
		}
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	private void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
