package module_TOUCHSCREEN;

import java.util.Scanner;

public class TouchscreenApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialInputListener inputListener = new SerialInputListener();
		Thread listener = new Thread(inputListener, "Listener");
		listener.start();
		DataPacket packet;
		Scanner input = new Scanner(System.in);
		boolean arm;
		SerialOutput output = new SerialOutput();
		while(true){
			//System.out.println(inputListener.isPacketAvailable());
			System.out.println("Arm?");
			arm = input.nextBoolean();
			if(arm){
				System.out.println("Okie dokie");
				output.sendCommandMessage("0");
				output.sendNextCommand();				
			} else {
				output.sendCommandMessage("1");
				output.sendNextCommand();
			}
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
