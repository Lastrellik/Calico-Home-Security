package module_PI.Raspberry_PI;


public class PiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();
		SerialOutput output = new SerialOutput();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.sendCommandMessage("0");
		output.sendNextCommand();
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.sendCommandMessage("1");
		output.sendNextCommand();
	}

}
