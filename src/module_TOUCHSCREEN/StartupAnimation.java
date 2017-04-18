package module_TOUCHSCREEN;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.File;
import java.awt.FlowLayout;

public class StartupAnimation extends JPanel{
	
	private BufferedImage logo = null;
	
	public StartupAnimation(){
		
		try {                
	          logo = ImageIO.read(getClass().getResource("logo.png"));     
	       } catch (IOException ex) {
	            System.out.println("Fail");
	       }
		setBackground(Colors.getPanelColor());
		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(0, 39, 364, 188);


	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(logo, 0, 0, null);  
    }
}
