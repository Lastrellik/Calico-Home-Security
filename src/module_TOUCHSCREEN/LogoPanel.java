package module_TOUCHSCREEN;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LogoPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage calicoLogoImage;
	private ImageIcon calicoLogo;
	private JLabel picLabel;
	private String pathToPictureFile;
	private File pictureFile;
	
	public LogoPanel(String pathToPictureFile){
		this.pathToPictureFile = pathToPictureFile;
		buildMetadata();
		buildPicture();
	}
	
	private void buildMetadata(){
		setBackground(CalicoColors.PANELBACKGROUND.getColor());
	}
	
	private void buildPicture(){
		pictureFile = new File(pathToPictureFile);
		try {
			calicoLogoImage = ImageIO.read(pictureFile);
			calicoLogo = new ImageIcon(calicoLogoImage);
			picLabel = new JLabel(calicoLogo);
			add(picLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
