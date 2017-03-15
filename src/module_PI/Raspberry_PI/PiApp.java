package module_PI.Raspberry_PI;

public class PiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();
		DataPacket packet;
		while(true){
			//System.out.println(inputListener.isPacketAvailable());
			if(inputListener.isPacketAvailable()){
				packet = inputListener.getDataPacket();
				System.out.println("Packet Type: " + packet.getPacketType());
				System.out.print("Packet raw hash: ");
				for(byte b : packet.getPacketSha1Hash()) System.out.print(b + " ");
				System.out.println();
				System.out.println("Packet Message: " + packet.getMessage());
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
