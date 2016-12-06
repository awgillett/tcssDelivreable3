package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
//import java.util.Calendar;
import java.util.Scanner;
import model.AuctionCalendar;
import model.Staff;
/**
 * The StaffUI class creates the user interface for the AuctionCenral user
 * @author  Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016 
 */
public class StaffUI {

	static Staff curStaff;
	static AuctionCalendar myCalendar;
	static Scanner sc = new Scanner(System.in);
	static int choice;
	static int EXIT = 1765456165;

	StaffUI() {
	}
    /**
     * create the AuctionCentral staff UI screens
     * @param theUser
     * @param theCalendar
     */
	public static void welcomeScreen(Staff theUser, AuctionCalendar theCalendar) {
		choice = 0;
		curStaff = theUser;
		myCalendar = theCalendar;
		System.out.println("\n\nAuction Central Main Staff View");
		System.out.println("You are logged in as: " + curStaff.getMyName());
		System.out.println("Welcome " + curStaff.getMyName() + ", what would you like to do?");
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. View all upcoming auctions");
			System.out.println("2. Log out and return to main menu.");
			System.out.println("");
			System.out.print (">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				showAuctions();
				break;
			case 2:
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
	 * create the AuctionCentral UI screens
	 */
	private static void welcomeScreen() {
		choice = 0;
		System.out.println("\n\nAuction Central Main Staff View");
		System.out.println("You are logged in as: " + curStaff.getMyName());
		System.out.println("Welcome " + curStaff.getMyName() + ", what would you like to do?");
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("1. View all upcoming auctions");
			System.out.println("2. Log out and return to main menu.");
			System.out.println("");
			System.out.print(">> ");
			checkInput();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				showAuctions();
				break;
			case 2:
				System.out.println("You have been logged out of the System");
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}

	}
    /**
     * create ActuctionCentral staff UI to display the auctions in the calendar
     */
	public static void showAuctions() {
		choice = 0;
		StringBuilder sb = new StringBuilder();
		YearMonth now = YearMonth.now();
		LocalDate date;
		boolean nextMonth = false;
		int numDays = now.lengthOfMonth();
		int[][] days = new int[35][2];
		int curDay = LocalDateTime.now().getDayOfMonth();
		int dayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
		int count = 0;
		int totalAuctions = 0;
		for (int i = curDay - dayOfWeek; count < 35; i++) {
			if ((count < 7 && count < dayOfWeek % 7 + 1) || count > numDays + dayOfWeek)
				days[count][0] = 0;
			else {
				if (!nextMonth)
					date = LocalDate.of(now.getYear(), now.getMonthValue(), i);
				else
					date = LocalDate.of(now.getYear(), now.getMonthValue() + 1, i);

				days[count][0] = i;
				days[count][1] = myCalendar.getAuctionDayCount(date);
				totalAuctions += days[count][1];

				if (i >= numDays) {
					i = 0;
					nextMonth = true;
				}
			}
			count++;
		}

		count = 0;
		System.out.println("\n\nAuction Central Calendar View");
		System.out.println("Logged in User: " + curStaff.getMyName());
		System.out.println("");
		System.out.println("Hello " + curStaff.getMyName() + ", here are the days with upcoming auctions");
		System.out.println("Total upcoming auctions: " + totalAuctions);
		System.out.println("");
		System.out.println("");
		System.out.println("Year-Month: " + now.toString());
		System.out.println("    Su\t      M\t       T\tW\tTh\t  F\t   Sa");
		for (int i = 0; i < 35; i++) {
			int day = days[i][0];
			int auctions = days[i][1];
			if (day == 0)
				sb.append("|    :   ");
			else {
				if (day < 10)
					sb.append("| 0" + day + " : " + auctions + " ");
				else
					sb.append("| " + day + " : " + auctions + " ");
			}
			if (i % 7 == 6) {
				sb.append("|");
				System.out.println(sb.toString());
				sb.setLength(0);
			}
		}
		choice = 0;
		while (choice != 1 && choice != 2 && choice != EXIT) {
			System.out.println("");
			System.out.println("");
			System.out.println("What would you like to do " + curStaff.getMyName() + "?");
			System.out.println("");
			System.out.println("1. Return to Staff Menu");
			System.out.println("2. Log out and return to main menu");
			System.out.println("");
			System.out.print(">> ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				welcomeScreen();
				break;
			case 2:
				System.out.println("You have been logged out of the System");
				break;
			default:
				System.out.println("Please choose within the range provided!");
			}
		}

	}

}
