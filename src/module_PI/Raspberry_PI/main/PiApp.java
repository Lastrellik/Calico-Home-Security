package module_PI.Raspberry_PI.main;


public class PiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();		
		SerialOutput output = new SerialOutput();
		
		pause(10000);
		output.sendTestComponentsPacket();
		output.sendCalibratePacket();
		output.sendArmPacket();
		output.sendTriggerPacket();
		output.sendSilencePacket();
		
		while(true){
			output.sendArmPacket();
			pause(5000);
			output.sendTriggerPacket();
			pause(2000);
			output.sendSilencePacket();
			pause(2000);
			output.sendDisarmPacket();
			pause(5000);
			output.sendResetCalibrationPacket();
			pause(5000);
			output.sendCalibratePacket();
			pause(5000);
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
