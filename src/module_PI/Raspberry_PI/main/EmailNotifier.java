package module_PI.Raspberry_PI.main;

import java.io.*;
import java.util.*;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailNotifier {

	private String gmailUsername;
	private String gmailPassword;

	private String recipientEmailAddress;
	private String emailSubject = "Calico Home Security";
	private String emailMessage;
	private String attachmentFilePath;

	private Properties mailSessionProperties;
	private Session javaMailSession;
	private Message javaMailMessage;
	private BodyPart messageBodyPart;
	private Multipart multipart;
	private DataSource attachmentDataSource;

	public EmailNotifier(String recipientEmailAddress, String message) {
		this(recipientEmailAddress, message, null);
	}

	public EmailNotifier(String recipientEmailAddress, String message, String pathToAttachment) {
		this.loadGmailCredentials();
		this.setRecipientEmailAddress(recipientEmailAddress);
		this.emailMessage = message;
		this.setAttachmentFilePath(pathToAttachment);
		buildPropertiesAndCreateSession();
	}

	private void loadGmailCredentials() {
		try {
			Properties gmailProperties = new Properties();
			FileInputStream in = new FileInputStream("gmailCredentials");
			gmailProperties.load(in);
			gmailUsername = gmailProperties.getProperty("gmailUsername");
			gmailPassword = gmailProperties.getProperty("gmailPassword");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildPropertiesAndCreateSession() {
		this.mailSessionProperties = new Properties();
		mailSessionProperties.put("mail.smtp.auth", "true");
		mailSessionProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailSessionProperties.put("mail.smtp.port", "465");
		mailSessionProperties.put("mail.smtp.ssl.enable", "true");
		createSession();
	}

	private void buildEmailMetadata() {
		try {
			buildMessageBodyPart();
			if (this.attachmentFilePath != null)
				buildAttachmentBodyPart();
			buildMessageData();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	private void createSession() {
		javaMailSession = Session.getInstance(mailSessionProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmailUsername, gmailPassword);
			}
		});
	}

	private void buildMessageBodyPart() throws MessagingException {
		multipart = new MimeMultipart();
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailMessage, "text/html");
		multipart.addBodyPart(messageBodyPart);
	}

	private void buildAttachmentBodyPart() throws MessagingException {
		attachmentDataSource = new FileDataSource(attachmentFilePath);
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(attachmentDataSource));
		messageBodyPart.setFileName(attachmentFilePath);
		multipart.addBodyPart(messageBodyPart);
	}

	private void buildMessageData() throws AddressException, MessagingException {
		javaMailMessage = new MimeMessage(javaMailSession);
		javaMailMessage.setFrom(new InternetAddress(gmailUsername));
		javaMailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmailAddress));
		javaMailMessage.setSubject(emailSubject);
		javaMailMessage.setContent(multipart);
	}

	public void sendEmail() {
		buildEmailMetadata();
		try {
			buildTransportAndSendMessage();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void buildTransportAndSendMessage() throws MessagingException {
		Transport transport = javaMailSession.getTransport();
		transport.connect();
		transport.sendMessage(javaMailMessage, javaMailMessage.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public String getAttachmentFilePath() {
		return this.attachmentFilePath;
	}

	public void setAttachmentFilePath(String attachmentFilePath) {
		if (attachmentFilePath != null && !new File(attachmentFilePath).exists())
			throw new IllegalArgumentException("File " + attachmentFilePath + " can't be found");
		this.attachmentFilePath = attachmentFilePath;
	}

	public String getRecipientEmailAddress() {
		return this.recipientEmailAddress;
	}

	public void setRecipientEmailAddress(String recipientEmailAddress) {
		String hilariousEmailAddressRegex = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\]"
				+ "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]"
				+ "]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \""
				+ "\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]"
				+ "+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:"
				+ "[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@("
				+ "?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)"
				+ "?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)"
				+ "*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\"."
				+ "\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\"
				+ "[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;"
				+ ":\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\"
				+ "\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?["
				+ " \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?"
				+ ":(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\"
				+ "]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\""
				+ ".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]])"
				+ ")|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t]"
				+ ")*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<"
				+ ">@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?"
				+ ":(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])"
				+ "+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)"
				+ "?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?"
				+ ":(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\."
				+ "|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()"
				+ "<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\"
				+ "\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])"
				+ "*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ "
				+ "\\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r"
				+ "\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:"
				+ "(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\"
				+ "\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\"
				+ "] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:["
				+ "^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?["
				+ " \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?="
				+ "[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:("
				+ "?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]"
				+ "+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\"
				+ "\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,"
				+ ";:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]"
				+ "]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:"
				+ "[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\""
				+ ".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\"."
				+ "\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:"
				+ "[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ "
				+ "\\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\""
				+ "()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:("
				+ "?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|("
				+ "?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*"
				+ "(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t"
				+ "])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ "
				+ "\\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n"
				+ ")?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r"
				+ "\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:("
				+ "?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:"
				+ "\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\"."
				+ "\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^"
				+ "\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])"
				+ "*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\"
				+ "\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t"
				+ "])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\"
				+ "\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t"
				+ "])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\""
				+ "()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ "
				+ "\\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r"
				+ "\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r"
				+ "\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\]"
				+ " \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]"
				+ "\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\"."
				+ "\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^"
				+ "\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+"
				+ "(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|"
				+ "(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:"
				+ "\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))"
				+ "|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()"
				+ "<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\"
				+ "[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t]"
				+ ")*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:"
				+ "\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r"
				+ "\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=["
				+ "\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*"
				+ ":(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])"
				+ "+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*"
				+ "\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\0"
				+ "31]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\"
				+ "\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:"
				+ "\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|"
				+ "\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>"
				+ "@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\"
				+ "[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])"
				+ "*))*)?;\\s*)";
		if (!recipientEmailAddress.matches(hilariousEmailAddressRegex)) {
			throw new IllegalArgumentException(recipientEmailAddress + " is not a valid email address.");
		}
		this.recipientEmailAddress = recipientEmailAddress;
	}

	public String getEmailMessage() {
		return this.emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
}