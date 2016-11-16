package view;
//match
import java.util.Collection;
import java.util.Scanner;

import model.Auction;
import model.Bid;
import model.Bidder;
import model.Calendar;
import model.Item;

public class BidderUI {
	
	static Bidder currentBidder;
	static Calendar myCalendar;
	static Scanner sc = new Scanner(System.in);
	static int choice;
	
	BidderUI(){
	}
	
	public static void welcomeScreen(Bidder bidder, Calendar theCalendar){
		
		choice = 0;
		currentBidder = bidder;
		myCalendar = theCalendar;
		
		System.out.println("You are now logged in as: " + currentBidder.getMyName());
		System.out.println("Welcome " + currentBidder.getMyName() + ", what would you like to do?");
		
		while (choice != 1 && choice != 2 && choice != 3) {
			System.out.println("");
			
			System.out.println("1. View all upcoming auctions");
			System.out.println("2. View your active bids");
			System.out.println("3. Log out and return to main menu.");

			System.out.println("");
			System.out.println(">>");
			choice = sc.nextInt();
			switch (choice) {
			
			case 1:	showAuctions(); break;
			case 2: showBids(); break;
			case 3: logOut(); break;
			
			default: System.out.println("Please choose within the range provided");
			
			}
		}
	}
	public static void showAuctions() {

		System.out.println("");
		System.out.println("This is the list of Auctions");
		System.out.println("");

		for(Auction a : myCalendar.getAllAuctions()){
				
			System.out.println(a.toString());
		}		
		
		System.out.println("");
		System.out.println("What would you like to do?");
		System.out.println("");
		
		System.out.println("1. View items from an auction");
		System.out.println("2. View your active bids");
		System.out.println("3. Log out and return to main menu.");
		System.out.println(">>");
		choice = sc.nextInt();
		switch (choice) {
		
		case 1:	showAuctionItems(); break;
		case 2: showBids(); break;
		case 3: logOut(); break;
		
		default: System.out.println("Please choose within the range provided");
		}
		

	}
	public static void showAuctionItems() {
		
		
		String npoChoice;
		
		System.out.println("");
		System.out.println("Please Enter the NPO for the auction");
		System.out.println("");
		System.out.println(">>");
		npoChoice = sc.next();
		for(Auction a : myCalendar.getAllAuctions()){
			if(a.getNPO().getMyName().equals(npoChoice)){
				showAuction(a);
				break;
			}
		}
	}

	private static void showAuction(Auction a) {
		
		System.out.println(a.toString());
		
		System.out.println("");
		System.out.println("What would you like to do?");
		System.out.println("");
		System.out.println("1. Bid on an item");
		System.out.println("2. View all Auctions");
		System.out.println("3. View your active bids");
		System.out.println("4. Log out and return to main menu.");
		System.out.println(">>");
		choice = sc.nextInt();
		switch (choice) {
		
		case 1:	makeBid(a); break;
		case 2: showAuctions(); break;
		case 3: showBids(); break;
		case 4: logOut(); break;
		
		default: System.out.println("Please choose within the range provided");
		}
	}

	public static void makeBid(Auction a) {
		int itemID;
		double bidOffer = 0;
		System.out.println("");
		System.out.println("Enter the item number?");
		System.out.println(">>");
		itemID = sc.nextInt();
		System.out.println(a.getItem(itemID));
		System.out.println("");
		System.out.println("What is your bid?");
		System.out.println(">>");
		bidOffer = sc.nextDouble();
		if(bidOffer < a.getItem(itemID).getMyMinBid()){
			System.out.println("Bid must be higher than or equal to the minumum bid.");
			makeBid(a);
		}
		
		System.out.println("Your bid is " + bidOffer + " for "+ a.getItem(itemID).getItemName()+".");
		System.out.println("Please choose one of the following");
		System.out.println("");
		System.out.println("1. Confirm and place your bid");
		System.out.println("2. Cancel bid");
		System.out.println("3. Cancel bid and return to main menu");
		
		choice = sc.nextInt();
		switch (choice) {
		
		case 1:	placeBid(a, bidOffer, itemID); break;
		case 2: makeBid(a); break;
		case 3: welcomeScreen(currentBidder, myCalendar); break;
		
		default: System.out.println("Please choose within the range provided");
		}
		
	}

	private static void placeBid(Auction a, double bidOffer, int itemID) {
		
		Bid myBid = new Bid(currentBidder.getMyUserName(), itemID, bidOffer, a.getMyID());
		currentBidder.addBid(myBid);
		
		System.out.println("Your bid of " + bidOffer + " for " + a.getItem(itemID).getItemName()+" has been placed.\n");
		welcomeScreen(currentBidder, myCalendar);
		
	}

	public static void showBids() {
		System.out.println("");
		System.out.println("This is the list of Bids");
		while (true) {
		}
	}
	public static void logOut() {
		System.out.println("");
		System.out.println("You have been logged out of the System");
		System.exit(0);
	}

}
