package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.Calendar;
import model.NPO;
import model.Staff;
/**
 * The NPOUI class creates the user interface for the NPO user
 * @author  Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016 
 */
public class NPOUI {

	static Calendar myCalendar;
	static NPO curNPO;
	static Scanner sc = new Scanner(System.in);
	static int choice;
	static int EXIT = 1765456165;

	NPOUI() {
	}
    /**
     * creates the NPO UI screens
     * @param theUser
     * @param theCalendar
     */
	public static void welcomeScreen(NPO theUser, Calendar theCalendar) {
		choice = 0;
		curNPO = theUser;
		myCalendar = theCalendar;
		System.out.println("\n\nAuction Central Main NPO View");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Welcome " + curNPO.getMyName() + ", what would you like to do?");
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Submit an auction request");
			System.out.println("2. Add an item to my upcoming auction");
			System.out.println("3. Remove an item from my upcoming auction");
			System.out.println("4. Log out and return to main menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently have an auction scheduled, you can only"
									+ " have one auction scheduled at a time. Please select another operation.");
					welcomeScreen();
				} else
					auctionRequestScreen();
				break;
			case 2:
				if (!curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently do not have an auction scheduled, please submit an auction request to begin the "
									+ "process of scheduling an auction");
					welcomeScreen();
				} else
					addItemScreen();
				break;
			case 3:
				if (!curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently do not have an auction scheduled, please submit an auction request to begin the "
									+ "process of scheduling an auction");
					welcomeScreen();
				} else
					removeItemScreen();
				break;
			case 4:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}
	/** 
	 * checks for user input
	 */
	private static void checkInput(){	
		while(!sc.hasNextInt()){
			System.out.println("\nPlease Enter an integer:");
			System.out.println("");
			System.out.print(">> ");
			sc.next();	
		}
	}
	
    /**
     * creates the NPO UI screens
     */
	private static void welcomeScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central Main NPO View");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Welcome " + curNPO.getMyName() + ", what would you like to do?");
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Submit an auction request");
			System.out.println("2. Add an item to my upcoming auction");
			System.out.println("3. Remove an item from my upcoming auction");
			System.out.println("4. Log out and return to main menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently have an auction scheduled, you can only"
									+ " have one auction scheduled at a time. Please select another operation.");
					welcomeScreen();
				} else
					auctionRequestScreen();
				break;
			case 2:
				if (!curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently do not have an auction scheduled, please submit an auction request to begin the "
									+ "process of scheduling an auction");
					welcomeScreen();
				} else
					addItemScreen();
				break;
			case 3:
				if (!curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently do not have an auction scheduled, please submit an auction request to begin the "
									+ "process of scheduling an auction");
					welcomeScreen();
				} else
					removeItemScreen();
				break;
			case 4:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}
    /**
     * create the NPO auction request screen
     */
	public static void auctionRequestScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central NPO Auction Requests");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("");
		System.out.println("Welcome " + curNPO.getMyName()
				+ ", Would like to enter the number of items or general notes about this auction? (y/n)");
		String ans = sc.next();
		String notes = "";
		int items = 0;
		if (ans.equals("y")) {
			System.out.println("How many items do you plan on auctioning?");
			checkInput();
			items = sc.nextInt();
			System.out.println("");
			System.out.println("What notes would you like to add to the auction record?");
			notes = sc.next();
		}
		System.out.println("");
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("Please enter the following information about when you would like the auction");
			System.out.println("");
			System.out.println("The Year (ex: 2015): ");
			checkInput();
			int year = sc.nextInt();
			System.out.println("The month (ex: for May type '5'): ");
			checkInput();
			int month = sc.nextInt();
			System.out.println("The day (ex: 15): ");
			checkInput();
			int day = sc.nextInt();
			System.out.println("The time (on the hour please in 24 hour format, (ex: for 4pm type 16): ");
			checkInput();
			int hour = sc.nextInt();
			LocalDateTime auctionDate = LocalDateTime.of(year, month, day, hour, 0);
			System.out.println("");
			System.out.println("You entered the following information:");
			System.out.println("Auction Date: " + auctionDate.toString());
			System.out.println("Number of Items:" + items);
			System.out.println("Notes: " + notes);

			System.out.println("1. Looks good! Please submit my request");
			System.out.println("2. Change Auction information");
			System.out.println("3. Cancel request and return to the NPO Main Menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (myCalendar.addAuction(curNPO, auctionDate, items, notes))
					auctionCreatedScreen();
				else
				{
					System.out.println("I am sorry but the date you entered is not available, please try again.");
					auctionRequestScreen();
				}
					
				break;
			case 2:
				auctionRequestScreen();
				break;
			case 3:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}
    /**
     * create the screen that verifies that an auction was created and gives option to add items to the auction
     */
	public static void auctionCreatedScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central auction Success View");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Congratulations " + curNPO.getMyName() + "!, your auction was successfully created");
		System.out.println("");
		System.out.println("What would you like to do now " + curNPO.getMyName() + "?");
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Add an item to my upcoming auction");
			System.out.println("2. Return to the NPO Main Menu..");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (!curNPO.hasAuction()) {
					System.out.println(
							"I am sorry but our records show you currently do not have an auction scheduled, please submit an auction request to begin the "
									+ "process of scheduling an auction");
					auctionCreatedScreen();
				} else
					addItemScreen();
				break;
			case 2:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}
    /**
     * create the interface to add items to an NPO's auction
     */
	public static void addItemScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central NPO add inventory");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Welcome " + curNPO.getMyName() + ", please enter the following data below:");
		System.out.println("");
		System.out.println("Item name: ");
		String name = sc.next();
		System.out.println("Item Condition (one of: acceptable, good, very good, like new, new): ");
		String condition = sc.next();
		System.out.println("Item Size (one of: small (no dimension is greater than one foot)");
		System.out.println("medium (at least one dimension is greater than one foot but no dimension is greater than three feet)");
		System.out.println("large (at least one dimension is greater than three feet)");
		String size = sc.next();
		System.out.println("Minimum acceptable bid (positive integer)");
		while(!sc.hasNextDouble()){
			System.out.println("Please Enter a double: ");
			System.out.println();
			sc.next();
		}
		double minBid = sc.nextDouble();
		System.out.println("");
		System.out.println("Thank you, would you like to enter addtional details now? (y/n)");
		String ans = sc.next();
		String donor = "";
		String desc = "";
		String comments = "";
		if (ans.equals("y")) {
			System.out.println("Who is the donor of this item?");
			donor = sc.next();
			System.out.println("");
			System.out.println("Add item description here: ");
			desc = sc.next();
			System.out.println("");
			System.out.println("What additional comments would you like to add to the item?");
			comments = sc.next();
		}
		System.out.println("");
		System.out.println("");
		System.out.println("You entered the following information:");
		System.out.println("Item Name: " + name);
		System.out.println("Item Condition: " + condition);
		System.out.println("Item Size: " + size);
		System.out.println("Minimum acceptable bid: " + minBid);
		System.out.println("Donor: " + donor);
		System.out.println("Description: " + desc);
		System.out.println("Comments: " + comments);
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Looks good! Please add my item");
			System.out.println("2. Change item information");
			System.out.println("3. Cancel item add and return to the NPO Main menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if (myCalendar.getAuction(curNPO).addItem(name, donor, condition, size, comments, desc, minBid))
						itemConfirmationScreen(name, donor, condition, size, comments, desc, minBid);
				else
				{
					System.out.println("I am sorry, it appears you already have this item listed in your auction, please try again");
					addItemScreen();
				}
				break;
			case 2:
				addItemScreen();
				break;
			default:
				welcomeScreen();
			}
		}
	}
	public static void removeItemScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central NPO remove inventory");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Welcome " + curNPO.getMyName() + ", please enter the following data below:");
		System.out.println("");
		System.out.println("What Item would you like to remove, please enter the item name? ");
		String itemToRemove = sc.next();
		System.out.println("are you sure you want to remove " + itemToRemove + "? ");
		
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Yes, remove the displayed item");
			System.out.println("2. Choose another Item to remove");
			System.out.println("3. Cancel item remove and return to the NPO Main menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				switch (myCalendar.getAuction(curNPO).removeItem(itemToRemove)) {
				case 1:
					removeItemConfirmationScreen(itemToRemove);
					break;
				case 2:
					System.out.println("I am sorry, it appears the item you are trying to remove is not listed in your auction, please try again");
					removeItemScreen();
					break;
				case 3:
					System.out.println("I am sorry, It is too late to remove items from this auction");
					removeItemScreen();
					break;
				default:
					welcomeScreen();
			    break;
				}
			case 2:
				removeItemScreen();
				break;
			case 3:
				welcomeScreen();
				break;
			default:
				welcomeScreen();
			}
			
		}
	}
	/** 
	 * create display of the confirmation screen for an item that has been added by the NPO and has option to enter another item
	 * @param theItemName
	 * @param theDonor
	 * @param theCondition
	 * @param theSize
	 * @param theNote
	 * @param theDescription
	 * @param theMinBid
	 */
	public static void itemConfirmationScreen(String theItemName, String theDonor, String theCondition, String theSize, String theNote, String theDescription, double theMinBid)
	{
		choice = 0;
		System.out.println("\n\nAuction Central NPO confirmation");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Congratulations " + curNPO.getMyName() + "!, your item was successfully added");
		System.out.println("Item Name: " + theItemName);
		System.out.println("Item Condition: " + theCondition);
		System.out.println("Item Size: " + theSize);
		System.out.println("Minimum acceptable bid: " + theMinBid);
		System.out.println("Donor: " + theDonor);
		System.out.println("Description: " + theDescription);
		System.out.println("Comments: " + theNote);
		System.out.println("");
		System.out.println("What would you like to do now " + curNPO.getMyName() + "?");
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. Add another item to my upcoming auction");
			System.out.println("2. Return to the NPO Main Menu..");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addItemScreen();
				break;
			case 2:
				welcomeScreen();
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}
	public static void removeItemConfirmationScreen(String itemName)
	{
		choice = 0;
		System.out.println("\n\nAuction Central NPO confirmation");
		System.out.println("You are logged in as: " + curNPO.getMyName());
		System.out.println("Congratulations " + curNPO.getMyName() + "!, your item was successfully removed");
		System.out.println("You removed the item with item name " + itemName);

		System.out.println("What would you like to do now " + curNPO.getMyName() + "?");
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. remove another item from my upcoming auction");
			System.out.println("2. Return to the NPO Main Menu..");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				removeItemScreen();
				break;
//			case 2:
//				welcomeScreen();
//				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}
	}

}
