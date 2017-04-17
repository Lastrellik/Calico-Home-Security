package module_PI.Raspberry_PI.JUnitTests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.*;

import module_PI.Raspberry_PI.main.EmailNotifier;

public class EmailNotifierTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	public static EmailNotifier testEmailNotifier = new EmailNotifier("testAddress@email.com", "testMessage");

	@Test
	public void testEmailNotifier_EmailAddress() {
		EmailNotifier emailNotifier = new EmailNotifier("test@test.com", null);
		assertEquals(emailNotifier.getRecipientEmailAddress(), "test@test.com");
	}
	
	@Test
	public void testEmailNotifier_Message() {
		EmailNotifier emailNotifier = new EmailNotifier("test@test.com", "test");
		assertEquals(emailNotifier.getEmailMessage(), "test");
	}
	
	@Test
	public void testEmailNotifier_EmailAddressMessageAndAttachment(){
		EmailNotifier emailNotifier = new EmailNotifier("test@test.com", "test", "README.md");
		assertEquals(emailNotifier.getAttachmentFilePath(), "README.md");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testEmailNotifier_AttachmentDoesntExist(){
		expectedException.expectMessage("File nonexistantfile.txt can't be found");
		EmailNotifier emailNotifier = new EmailNotifier("test@test.com", "test", "nonexistantfile.txt");	
	}

	@SuppressWarnings("unused")
	@Test
	public void testEmailNotifier_InvalidEmailAddress(){
		expectedException.expectMessage("notAProperEmailAddress is not a valid email address");
		EmailNotifier emailNotifier = new EmailNotifier("notAProperEmailAddress", "test");
	}
	
	@Test
	public void testGetAttachmentFilePath(){
		testEmailNotifier.setAttachmentFilePath("README.md");
		assertEquals(testEmailNotifier.getAttachmentFilePath(), "README.md");
	}
	
	@Test
	public void testSetAttachmentFilePath(){
		testEmailNotifier.setAttachmentFilePath("README.md");
		assertEquals(testEmailNotifier.getAttachmentFilePath(), "README.md");
	}
	
	@Test
	public void testSetAttachmentFilePath_NonexistantFile(){
		expectedException.expectMessage("File thisfiledoesntexist.txt can't be found");
		testEmailNotifier.setAttachmentFilePath("thisfiledoesntexist.txt");
	}
	
	@Test
	public void testGetRecipientEmailAddress(){
		testEmailNotifier.setRecipientEmailAddress("Testemail@test.com");
		assertEquals(testEmailNotifier.getRecipientEmailAddress(), "Testemail@test.com");
	}
	
	@Test
	public void testSetRecepientEmailAddress(){
		testEmailNotifier.setRecipientEmailAddress("Testemail@test.com");
		assertEquals(testEmailNotifier.getRecipientEmailAddress(), "Testemail@test.com");		
	}
	
	@Test
	public void testSetRecipientEmailAddress_InvalidEmailAddress(){
		expectedException.expectMessage("notanemailaddress is not a valid email address");
		testEmailNotifier.setRecipientEmailAddress("notanemailaddress");
	}
	
	@Test
	public void testRemoveAttachment(){
		testEmailNotifier.setAttachmentFilePath("./PI_PICS");
		testEmailNotifier.removeAttachment();
		assertTrue(testEmailNotifier.getAttachmentFilePath() == null);
	}
	
	@Test
	public void testHasAttachment(){
		testEmailNotifier.removeAttachment();
		assertFalse(testEmailNotifier.hasAttachment());
	}
}
