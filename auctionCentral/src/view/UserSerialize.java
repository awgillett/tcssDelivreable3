/**
 * match
 */
package view;

import java.io.*;
import java.util.*;

import model.NPO;
import model.User;

/**
 * @author phucv
 *
 */
public class UserSerialize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection <User> myUserList = new ArrayList<User>();
		setUserList(myUserList);

		try{
			
	        FileOutputStream fileOut =
            new FileOutputStream("/Users/phucv/Documents/GitHub/AuctionCentral/userlist.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myUserList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /AuctionCentral/userlist.ser");
		}catch(IOException i){
			i.printStackTrace();
		}

	}
	
	public static void setUserList(Collection<User> myUserList){
		myUserList.add(new NPO ("JamesS", "James Smith"));
		myUserList.add(new NPO ("MichaelT", "Michael Taylor"));
		myUserList.add(new NPO ("RobertA", "Robert Anderson"));
		myUserList.add(new NPO ("MariaG", "Maria Garcia"));
		myUserList.add(new NPO ("DavidC", "David Cole"));
		myUserList.add(new NPO ("PaulW", "Paul Walker"));
		myUserList.add(new NPO ("JohnT", "John Terry"));
		myUserList.add(new NPO ("AllenL", "Allen Lee"));
		myUserList.add(new NPO ("KayM", "Kay Martin"));
		myUserList.add(new NPO ("TonyC", "Tony Clark"));
		myUserList.add(new NPO ("MaryW", "Mary White"));
		myUserList.add(new NPO ("PeterN", "Peter Nelson"));
		myUserList.add(new NPO ("HenryC", "Henry Carter"));
		myUserList.add(new NPO ("AdamB", "Adam Burden"));
		myUserList.add(new NPO ("SteveP", "Steve Pratt"));
		myUserList.add(new NPO ("JohnH", "John Howard"));
		myUserList.add(new NPO ("StevenB", "Steven Brooks"));
		myUserList.add(new NPO ("LynnG", "Lynn Gray"));
		myUserList.add(new NPO ("ElizabethK", "Elizabeth Kelly"));
		myUserList.add(new NPO ("SusanB", "Susan Bennett"));
		myUserList.add(new NPO ("JenniferC", "Jennifer Coleman"));
		myUserList.add(new NPO ("LindaP", "Linda Peterson"));
		myUserList.add(new NPO ("LisaB", "Lisa Barnes"));
		myUserList.add(new NPO ("KarenH", "Karen Hughes"));
	}

}
