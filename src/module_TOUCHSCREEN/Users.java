package module_TOUCHSCREEN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Users {
	static String users [] = new String[10];
	static String passwords[] = new String[10];
	/**
	 * Parameters are set to static so that other classes can make changes easily without getters and setters.
	 */
	
	/**
	 * Users is supposed to create an array of users to be managed
	 */
	private static Map<String, String> usersPass = new HashMap<String, String>();
	
	public static Map<String, String> get_Users_Pass(){
		return usersPass;
	}
	public static void readFromTextFile() {
		int count = 0;
		try{
			File savedUsersFile = new File("src/module_TOUCHSCREEN/savedUsers.txt");
			Scanner reader = new Scanner(savedUsersFile);
			while(reader.hasNextLine()){
				usersPass.put(reader.nextLine(),reader.nextLine());
			}
			for(String s : usersPass.keySet()){
				count++;
				users[count] = s;
				passwords[count] = usersPass.get(s);
				System.out.println(s + " " + usersPass.get(s));
			}
			reader.close();
		} catch (FileNotFoundException F){
			System.err.println("SavedUsers File cannot be found!");
		}

	}
	/**
	 * @throws IOException 
	 * 
	 */
	public static void writeToFile() throws IOException{
		try{
			File savedUsersFile = new File("src/module_TOUCHSCREEN/savedUsers.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter("src/module_TOUCHSCREEN/savedUsers.txt"));
			for(String s : usersPass.keySet()){
				out.write(s);
				out.newLine();
				out.write(usersPass.get(s));
				out.newLine();
			}
			out.close();
		} catch (FileNotFoundException F){
			System.err.println("SavedUsers File cannot be found!");
		}
	}
	/**
	 * Removes a user from the hash map by accepting a string and searching for that value.
	 */
	public static void removeUser(String user){
		usersPass.remove(user);
	}
	/**
	 * Adds a user from the hash map by accepting a string for user and password but won't add passed hard coded value of 10.
	 * @throws IOException 
	 */
	public static void addUser(StringBuilder create_User, String password){
		usersPass.put(String.valueOf(create_User).trim(), password);
		try {
			writeToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return users returns the user array
	 */
	public String[] getUsers(){
		return users;
	}
	/**
	 * 
	 * @return passwords returns the passwords array
	 */
	public String[] getPasswords(){
		return passwords;
	}
	public static void main(String[] args){
		Users test = new Users();
		readFromTextFile();
		String[] temp = test.getUsers();
		
		for(String s: temp){
			System.out.println(s);
		}
	}
}
