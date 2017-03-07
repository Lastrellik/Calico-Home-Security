package module_TOUCHSCREEN;

public class TouchscreenApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();
		while(true){
			for(byte b : inputListener.readAll()){
				System.out.print((char)b);}
			try {
				Thread.sleep(1000);
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
