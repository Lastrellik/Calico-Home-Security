/**
 * 
 */
package module_TOUCHSCREEN;

import javax.swing.JFrame;

/**
 * @author jacob
 *
 */
public class TouchscreenApp {
	/**
	 * Main class creates JFrame to house CalicoArmGUI
	 * @param args
	 */
	public static void main(String[] args){
		
		JFrame frame = new JFrame("FrameDemo");
		frame.setSize(800,525);
		MainPanel panel = new MainPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}
}
