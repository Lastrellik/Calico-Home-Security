package module_TOUCHSCREEN;
import javax.swing.JFrame;

public class TouchscreenApp {
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Calico Home Security");
		frame.setSize(820,525);
		MainPanel panel = new MainPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}
}
