
package view;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//match
import java.util.Collection;
import java.util.Scanner;

import model.Auction;
import model.Bid;
import model.Bidder;

/* The BidderUI class displays bidder options to navigate the system.
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */

import model.Calendar;
import model.Item;

public class BidderUI {
	
	static Bidder currentBidder;
	static Calendar myCalendar;
	static Scanner sc = new Scanner(System.in);
	static int choice;
	static int EXIT = 1765456165;
	
	BidderUI(){
	}

	/**
	 * @author aaron
	 * displays the welcome screen for a Bidder
	 * @param bidder the bidder that is logged into the system
	 * @param theCalendar the system calendar
	 */
	public static void welcomeScreen(Bidder bidder, Calendar theCalendar){
		
		choice = 0;
		currentBidder = bidder;
		myCalendar = theCalendar;

		System.out.println("You are now logged in as: " + currentBidder.getMyName());
		System.out.println("Welcome " + currentBidder.getMyName() + ", what would you like to do?");
		
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			
			System.out.println("1. View all upcoming auctions");
			System.out.println("2. View your active bids");
			System.out.println("3. Log out and return to main menu.");

			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {

			case 1:
				showAuctions();
				break;
			case 2:
				showBids();
				break;
			case 3:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;

			default:
				System.out.println("Please choose within the range provided");
			
			}
		}
	}
	
	private static void checkInput(){	
		while(!sc.hasNextInt()){
			System.out.println("\nPlease choose within the range provided");
			System.out.println("");
			System.out.print(">> ");
			sc.next();	
		}
	}
	
	/**
	 * @author aaron
	 * displays all of the scheduled auctions for the bidder.
	 */
	public static void showAuctions() {

		choice = 0;
		System.out.println("");
		System.out.println("This is the list of Auctions");
		System.out.println("");

		for(Auction a : myCalendar.getAllAuctions()){
				
			System.out.println(a.toString());
		}		
		
		while (choice != 1 && choice != 2 && choice != 3 && choice != EXIT) {
			System.out.println("");
			System.out.println("What would you like to do?");
			System.out.println("");
			
			System.out.println("1. Bid on an item");
			System.out.println("2. View your active bids");
			System.out.println("3. Log out and return to main menu.");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
	
			case 1:
				makeBid();
				break;
			case 2:
				showBids();
				break;
			case 3:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;
	
			default:
				System.out.println("Please choose within the range provided");
			}
		}
	}

	public static void makeBid() {
		choice = 0;
		int itemID;
		double bidOffer = 0;
		System.out.println("");
		System.out.println("Enter the item number?");
		System.out.println(">>");
		checkInput();
		itemID = sc.nextInt();
		
		Item itemToBidOn = currentBidder.getItemToBidOn(itemID, myCalendar);
		
		if(itemToBidOn == null){
			System.out.println("\nSorry, we can not find an item with that item number.\n");
			welcomeScreen(currentBidder, myCalendar);
		}
				
		System.out.println("");
		System.out.println("How much would you like to bid?");
		System.out.println(">>");
		while(!sc.hasNextDouble()){
			System.out.println("Please enter a double: ");
			System.out.println();
			System.out.print(">> ");
			sc.next();
		}
		bidOffer = sc.nextDouble();
		
		
		
		while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != EXIT) {
			System.out.println("Your bid is $" + bidOffer + " for "+ itemToBidOn.getItemName()+".");
			System.out.println("Please choose one of the following");
			System.out.println("");
			System.out.println("1. Confirm and place your bid");
			System.out.println("2. Cancel this bid");
			System.out.println("3. Cancel bid and return to main menu");
			System.out.print(">> " );
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
	
			case 1:
				placeBid(bidOffer, itemToBidOn);
				break;
			case 2:
				System.out.println("Bid canceled");
				makeBid();
				break;
			case 3:
				welcomeScreen(currentBidder, myCalendar);
				break;
			case 4:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;
	
			default:
				System.out.println("Please choose within the range provided");
			}
		}
		
	}

	private static void placeBid(double bidOffer, Item itemToBidOn) {
		
		int result = currentBidder.addBid(myCalendar, itemToBidOn, bidOffer);
		switch (result) {
		
		case 1:
			System.out.println("Congratulations! Your bid of $" + bidOffer + " for " + itemToBidOn.getItemName() + " has been placed.\n");
			welcomeScreen(currentBidder, myCalendar);
			break;
		case 2:
			System.out.println("Your bid is below the minimum bid amount of $" + itemToBidOn.getMyMinBid() + 
					" please adjust your bid and try again.");
			makeBid();
			break;
		case 3:
			System.out.println("You already have a bid for this item.");
			makeBid();
			break;
		case 4:
			System.out.println("Sorry, online bidding is closed for this auction.");
			break;

		default:
			makeBid();
		}
	}

	/**
	 * @author aaron
	 * UI method that allows the bidder to view all of their current bids
	 * as well as giving the option to cancel a current bid.
	 */
	public static void showBids() {
		choice = 0;

		
		while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != EXIT) {
			
			System.out.println("");
			System.out.println("Current bids for " + currentBidder.getMyName());
			
			System.out.println(currentBidder.printBids(myCalendar));
			
			System.out.println("What would you like to do?");
			System.out.println("Please choose one of the following");
			System.out.println("");
			System.out.println("1. View all upcoming auctions");
			System.out.println("2. Cancel an active bid");
			System.out.println("3. Return to main menu.");
			System.out.println("4. Log out and return to main menu.");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {

			case 1:
				showAuctions();
				break;
			case 2:
				cancelBid();
				break;
			case 3:
				welcomeScreen(currentBidder, myCalendar);
				break;
			case 4:
				System.out.println("You have been logged out of the System");
				choice = EXIT;
				break;
			default:
				System.out.println("Please choose within the range provided");

			}
		}
	}

	private static void cancelBid() {
		
		// for reference. The possible return values from currentBidder.bidRemovalRequest()
//		int itemFoundAndRemovable = 1;
//		int itemFoundButNotRemovable = 2;
//		int itemNotFound = 3;
		
		int itemID;
		System.out.println("");
		System.out.println("Enter the item number?");
		System.out.println(">>");
		checkInput();
		itemID = sc.nextInt();
		
		switch (currentBidder.bidRemovalRequest(itemID, myCalendar)) {
		case 1://itemFoundAndRemovable
			bidCancelConfirmReq(itemID);
			break;
		case 2://itemFoundButNotRemovable
			System.out.println("Your bid for item number " + itemID + " has not been canceled");
			System.out.println("because the auction is within two days of today.");
			printBids();
			break;
		case 3://itemNotFound
			System.out.println("You do not have a bid placed for item " + itemID);
			printBids();
			break;
		default:
			System.out.println("Please choose within the range provided");
		}
		welcomeScreen(currentBidder, myCalendar);
		
	}

	private static void bidCancelConfirmReq(int itemID) {
		choice = 0;


		while (choice != 1 && choice != 2 && choice != EXIT) {

			System.out.println("");
			System.out.println("You would like to cancel your bid for item number " + itemID);
			System.out.println("");
			System.out.println("Please choose one of the following");
			System.out.println("1. Confirm bid cancelation.");
			System.out.println("2. Do not cancel bid. Return to main menu.");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				confirmedBidCancel(itemID);
				break;
			case 2:
				welcomeScreen(currentBidder, myCalendar);
				break;
			default:
				System.out.println("Please choose within the range provided");
			}
		}
	}

	private static void confirmedBidCancel(int itemID) {
		
		if(currentBidder.removeTheBid(itemID)){
			System.out.println("Your bid for item number " + itemID + " has been canceled.");
			printBids();
		}else{
			// This should never print
			System.out.println("Your bid for item number " + itemID + " has not been canceled.");
		}
		welcomeScreen(currentBidder, myCalendar);
	}

	private static void printBids() {
		
		System.out.println("Your current list of active bids:");
		System.out.println(currentBidder.printBids(myCalendar));
	}
	
	
}
