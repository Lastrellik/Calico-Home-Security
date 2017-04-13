package module_TOUCHSCREEN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Users {
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
		try{
			File savedUsersFile = new File("src/module_TOUCHSCREEN/savedUsers.txt");
			Scanner reader = new Scanner(savedUsersFile);
			while(reader.hasNextLine()){
				usersPass.put(reader.nextLine(),reader.nextLine());
			}
			for(String s : usersPass.keySet()){
				System.out.println(s + " " + usersPass.get(s));
			}
			reader.close();
		} catch (FileNotFoundException F){
			System.err.println("SavedUsers File cannot be found!");
		}
	}
	public static void main(String[] args){
		readFromTextFile();
		
	}
}
