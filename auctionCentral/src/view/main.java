/**
 * 
 */
package view;

import java.io.*; 
import java.time.LocalDateTime;
import java.util.*;

import model.Auction;
import model.AuctionDate;
import model.Calendar;
import model.Item;
import model.NPO;
import model.Staff;
import model.Bidder;
import model.User;

/**
 * @author 
 *
 */
public class main {

	protected static Calendar myCalendar = new Calendar();
	Scanner sc = new Scanner(System.in);
	private ArrayList<User> userList = new ArrayList();
	User curUser = new User();
	int choice;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		main UI = new main();
		//1
		UI.load();

		//UI.preLoad();
		UI.welcomeScreen();
		
	}
	
	public void preLoad()
	{
		userList.add(new Staff("cseiber", "Carl"));
		userList.add(new Staff("lseiber", "Lindsey"));
		
		for (int i = 0; i < 24; i++)
		{
			char ch;

			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
			userList.add(new Bidder("Bidder" + ch, Character.toString(ch), "111-1234", "123 Avenue", ch + "@hotmail.com", "CC 12345678"));
		}
		
		int count = 20;
		int month = 11;
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				myCalendar.addAuction((NPO) u, LocalDateTime.of(2016, month, count, 12, 00), 0, "");
				if (count >= 30)
				{
					count = 0;
					month = 12;
				}
				count++;
			}
		}
		
		for (Auction a : myCalendar.getAllAuctions())
		{
			for (int i = 0; i < 10; i++)
			{
				char ch;

				ch = (char) ((i) + 'a');
				a.addItem("Item" + ch, "", "good", "small", "", "", 25);
			}
		}
		
	}

	public void welcomeScreen() {
		System.out.println("\n\nWelcome to Auction Central");
		System.out.println("Please enter a number from the following options below:");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("1. Log In");
		System.out.println("2. Register");
		System.out.println("~~Enter 0 or NEGATIVE to 1Exit");
		System.out.println("");
		System.out.print(">> ");
		
		checkInput(3);
		choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			loginScreen();
			break;
		case 2:
			registrationScreen();
			break;
		default:
			System.out.println("Thank you for using Auction Central! Have a good day!");
			saveAndExit();
			System.exit(0);
		}
	}
	
	private void checkInput(int radix){	
		while(!sc.hasNextInt(radix)){
			System.out.println("\nPlease choose within the range provided");
			System.out.println("");
			System.out.print(">> ");
			sc.next();	
		}
	}

	public void loginScreen() {
		System.out.println("\n\nAuction Central Log In");
		System.out.println("");
		System.out.println("Please enter your user name below");
		System.out.println("");
		System.out.println("");
		System.out.print(">> ");
		String userName = sc.next();

		for (User u : userList) {
			// System.out.println(u.getMyUserName().equals(userName));
			if (u.getMyUserName().equals(userName))
				curUser = u;
		}

		if (curUser.getMyUserName() == null) {
			System.out.println("User not found");
			System.out.println("");
			while (true) {
				System.out.println("1. Try again");
				System.out.println("2. Register");
				System.out.println("3. Main Menu");
				System.out.println("");
				System.out.print(">> ");
				
				checkInput(4);
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					loginScreen();
					break;
				case 2:
					registrationScreen();
					break;
				case 3:
					welcomeScreen();
					break;
				default:
					System.out.println("Please choose within the range provided");
				}
			}
		}

		switch (curUser.getUserType()) {

		case "Staff":
			StaffUI.welcomeScreen((Staff) curUser, myCalendar);
			break;
		// case "NPO": NPOUI.welcomeScreen(); break;
		case "Bidder":
			BidderUI.welcomeScreen((Bidder) curUser, myCalendar);
			break;
		case "NPO":
			NPOUI.welcomeScreen((NPO) curUser, myCalendar);
			break;

		default:
			System.out.println("Please choose within the range provided");
		}
		curUser = new User();
		welcomeScreen();

	}

	public void registrationScreen() {
		System.out.println("\n\nAuction Central Registration");
		System.out.println("");
		System.out.println("What type of user do you want to register as?");
		System.out.println("");
		while (true) {
			System.out.println("1. Register as Auction Central Staff member");
			System.out.println("2. Register as a Non Profit Representative");
			System.out.println("3. Register as a Bidder");
			System.out.println("4. Back to Main Menu");
			System.out.println("");
			System.out.print(">> ");
			
			checkInput(5);
			
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				registerStaffScreen();
			case 2:
				registerNPOScreen();
			case 3:
				registerBidderScreen();
			case 4:
				welcomeScreen();
			default:
				System.out.println("Please choose within the range provided");
			}
		}
	}

	private void registerBidderScreen() {
		System.out.println("\n\nWelcome to Bidder Registration");
		System.out.println("Please enter your information below");
		System.out.println("");
		System.out.print("Enter a user name: ");
		String uName = sc.next();
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.print("Enter your address: ");
		String address = sc.next();
		System.out.print("Enter your phone number: ");
		String phoneNumber = sc.next();
		System.out.print("Enter your email address: ");
		String email = sc.next();
		System.out.print("Enter your payment information: ");
		String payInfo = sc.next();

		System.out.println("You have entered the following information");
		System.out.println("\tUsername:\t" + uName);
		System.out.println("\tName:\t\t" + name);
		System.out.println("\tAddress:\t" + address);
		System.out.println("\tPhone Number:\t" + phoneNumber);
		System.out.println("\tEmail Address:\t" + email);
		System.out.println("\tPayment Info:\t" + payInfo);

		System.out.println("Is this correct?");
		while (true) {
			System.out.println("");
			System.out.println("1. Yes please register me");
			System.out.println("2. No, Try again");
			System.out.println("3. Main Menu");
			System.out.println("");
			System.out.print(">> ");
			checkInput(4);
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				registrationCompleteBidderScreen(uName, name, address, phoneNumber, email, payInfo);
				break;
			case 2:
				registrationScreen();
				break;
			case 3:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided");
			}
		}

	}

	public void registrationCompleteBidderScreen(String userName, String name, String theAddress, String thePhone,
			String theEmail, String thePaymentInfo) {
		userList.add(new Bidder(userName.trim(), name.trim(), theAddress.trim(), thePhone.trim(), theEmail.trim(),
				thePaymentInfo.trim()));
		System.out.println("\n\nCongratulations you have successfully registered on Auction Central");
		welcomeScreen();
	}

	public void registerStaffScreen() {
		System.out.println("\n\nWelcome to Auction Central Staff Registration");
		System.out.println("Please enter your information below");
		System.out.println("");
		System.out.print("Enter a user name: ");
		String uName = sc.next();
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.println("You have entered the following information");
		System.out.println("\tUsername: " + uName);
		System.out.println("\tName:\t" + name);
		System.out.println("Is this correct?");
		while (true) {
			System.out.println("");
			System.out.println("1. Yes please register me");
			System.out.println("2. No, Try again");
			System.out.println("3. Main Menu");
			System.out.println("");
			System.out.print(">> ");
			checkInput(4);
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				while (!checkAvailability(uName)) {
					System.out.println("Sorry that user name is not available, please choose another name");
					System.out.print("Enter another user name: ");
					uName = sc.next();
				}
				registrationCompleteStaffScreen(uName, name);
				break;
			case 2:
				registrationScreen();
				break;
			case 3:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided");
			}
		}
	}

	public void registrationCompleteStaffScreen(String userName, String name) {
		userList.add(new Staff(userName.trim(), name.trim()));
		System.out.println("\n\nCongratulations you have successfully registered on Auction Central");
		welcomeScreen();
	}

	public void registerNPOScreen() {
		System.out.println("\n\nWelcome to Auction Central NPO Registration");
		System.out.println("Please enter your information below");
		System.out.println("");
		System.out.print("Enter a user name: ");
		String uName = sc.next();
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.println("You have entered the following information");
		System.out.println("\tUsername: " + uName);
		System.out.println("\tName:\t" + name);
		System.out.println("Is this correct?");
		while (true) {
			System.out.println("");
			System.out.println("1. Yes please register me");
			System.out.println("2. No, Try again");
			System.out.println("3. Main Menu");
			System.out.print(">> ");
			checkInput(4);
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				while (!checkAvailability(uName)) {
					System.out.println("Sorry that user name is not available, please choose another name");
					System.out.print("Enter another user name: ");
					uName = sc.next();
				}
				registrationCompleteNPOScreen(uName, name);
				break;
			case 2:
				registrationScreen();
				break;
			case 3:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided");
			}
		}
	}

	public void registrationCompleteNPOScreen(String userName, String name) {
		userList.add(new NPO(userName.trim(), name.trim()));
		System.out.println("\n\nCongratulations you have successfully registered on Auction Central");
		welcomeScreen();
	}

	public boolean checkAvailability(String userName) {
		for (User u : userList) {
			if (u.getMyUserName().equals(userName))
				return false;
		}

		return true;
	}

	private void saveAndExit() {
		try {
			FileOutputStream fileOut = new FileOutputStream("./Calendar.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myCalendar);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ./Calendar.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}

		try {
			FileOutputStream fileOut2 = new FileOutputStream("./Users.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
			out2.writeObject(userList);
			out2.close();
			fileOut2.close();
			System.out.printf("Serialized data is saved in ./Users.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}


	}

	private void load() {
		try {
			FileInputStream fileIn = new FileInputStream("./Calendar.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			myCalendar = (Calendar) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Calendar class not found");
			c.printStackTrace();
			return;
		}

		try {
			FileInputStream fileIn = new FileInputStream("./Users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userList = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

	}

}

