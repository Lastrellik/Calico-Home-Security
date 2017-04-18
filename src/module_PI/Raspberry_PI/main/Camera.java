package module_PI.Raspberry_PI.main;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

public class Camera {
	
	private String filePath;
	private Webcam webcam;
	private Dimension[] imageResolutions = new Dimension[] {
			WebcamResolution.PAL.getSize(),
			WebcamResolution.HD720.getSize(),
			new Dimension(2000, 1000),
			new Dimension(1000, 500),
	};
	public Camera(){
		this("./");
	}
	
	public Camera(String filePath){
		PiApp.LogToFile("Camera created with picture path " + filePath);
		setFilePath(filePath);
		setWebcamDriver();
		buildWebcam();
	}
	
	private void setWebcamDriver(){
		Webcam.setDriver(new V4l4jDriver());
	}
	
	private void buildWebcam(){
		webcam = Webcam.getDefault();
		webcam.setCustomViewSizes(imageResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());
	}
	
	public void takePicture(String pictureFileName){
		PiApp.LogToFile("Webcam picture taken: " + pictureFileName);
		String pictureFileNameWithExtension = pictureFileName;
		boolean hasFileExtension = pictureFileName.matches("^[\\w,\\s-]+\\.[A-Za-z]{3}$");
		if(!hasFileExtension) pictureFileNameWithExtension = pictureFileName + ".jpg";
		try {
			webcam.open();
			ImageIO.write(webcam.getImage(), "JPG", new File(this.filePath + "/" + pictureFileNameWithExtension));
			webcam.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		PiApp.LogToFile("Webcam picture path set to " + filePath);
		if(! new File(filePath).exists()) throw new IllegalArgumentException(filePath + " is not a valid path.");
		this.filePath = filePath;
	}
}
