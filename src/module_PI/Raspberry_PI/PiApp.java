package module_PI.Raspberry_PI;


public class PiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();
	}

}
