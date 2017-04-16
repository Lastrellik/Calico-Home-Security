package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;
import org.junit.rules.ExpectedException;

import module_PI.Raspberry_PI.main.Camera;

public class CameraTest {
	
	private Camera camera = new Camera();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testCamera(){
		assertEquals(camera.getFilePath(), "./");
	}
	
	@Test
	public void testCamera_withFilePath(){
		String filePath = "testPath";
		File file = new File(filePath);
		file.mkdir();
		Camera cameraWithPath = new Camera(filePath);
		assertEquals(cameraWithPath.getFilePath(), filePath);
		file.delete();
	}
	
	@Test
	public void testCamera_pathDoesntExist(){
		ex.expectMessage("invalidpath is not a valid path.");
		@SuppressWarnings("unused")
		Camera camera = new Camera("invalidpath");
	}
	
	@Test
	public void testTakePicture_withoutJpgExtension(){
		camera.takePicture("testPicture");
		File testImageFile = new File("testPicture.jpg");
		assertTrue(testImageFile.exists());
		testImageFile.delete();
	}
	
	@Test
	public void testTakePicture_withJpgExtension(){
		camera.takePicture("testPicture.jpg");
		File testImageFile = new File("testPicture.jpg");
		assertTrue(testImageFile.exists());
		testImageFile.delete();
	}
	
	@Test
	public void testTakePicture_nonDefaultDirectory(){
		String filePath = "testFilePath";
		File fileFolder = new File(filePath);
		fileFolder.mkdir();
		Camera nonDefaultDirectoryCamera = new Camera(filePath);
		nonDefaultDirectoryCamera.takePicture("testPicture");
		File testImageFile = new File("./testFilePath/testPicture.jpg");
		assertTrue(testImageFile.exists());
		testImageFile.delete();
		fileFolder.delete();
	}
	
	@Test
	public void testGetFilePath(){
		assertEquals(camera.getFilePath(), "./");
	}
	
	@Test
	public void testSetFilePath(){
		String filePath = "testFilePath";
		File fileFolder = new File(filePath);
		fileFolder.mkdir();
		Camera cameraWithDifferentPath = new Camera();
		cameraWithDifferentPath.setFilePath(filePath);
		assertEquals(cameraWithDifferentPath.getFilePath(), "testFilePath");
		fileFolder.delete();
	}
	
	@Test
	public void testSetFilePath_invalidPath(){
		Camera cameraWithDifferentPath = new Camera();
		ex.expectMessage("invalidfilepath is not a valid path.");
		cameraWithDifferentPath.setFilePath("invalidfilepath");
	}

}
