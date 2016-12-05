package GUI;

import java.awt.EventQueue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Auction;
import model.Bidder;
import model.AuctionCalendar;
import model.Item;
import model.NPO;
import model.User;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;



public class BidderGUI{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM"			+ " d yyyy, hh:mm a");

	protected JFrame myFrame = new JFrame();
	
	static AuctionCalendar myCalendar;
	static Bidder currentBidder;
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static String loggedInAs = "You are signed in as: ";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					
					//this is for testing
					testBidderInit();
					
					BidderGUI bidderWindow = new BidderGUI(currentBidder, myCalendar);
					bidderWindow.myFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

	}
	
	public BidderGUI(Bidder theBidder, AuctionCalendar theCalendar) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		
		
		
		startGUI();
		
		

		
	}

	/**
	 * @author aaron
	 * Creates a test bidder, test calendar, two test auctions and six test items.
	 * Used for debugging since the login/registration gui is not made yet.
	 */
	private static void testBidderInit() {
		
		Bidder testBidder = new Bidder("userName","name","address","phone","email","payInfo");
		AuctionCalendar testCalendar = new AuctionCalendar();
		NPO testNpoA = new NPO("NPOa", "npoa");
		NPO testNpoB = new NPO("NPOb", "npob");
		
		Auction testAuctionA = new Auction(testNpoA, LocalDateTime.now().plusDays(7), 10, "Notes", 1);
		Auction testAuctionB = new Auction(testNpoB, LocalDateTime.now().plusDays(10), 10, "Notes", 1);

		testCalendar.addAuction(testAuctionA);
		testCalendar.addAuction(testAuctionB);
		
		Item testItem1 = new Item("itemA", "NPOA", "good", "small", "NOTE", "DESC", 10., 1);
		Item testItem2 = new Item("itemB", "NPOA", "good", "small", "NOTE", "DESC", 15., 2);
		Item testItem3 = new Item("itemC", "NPOA", "good", "small", "NOTE", "DESC", 15., 3);
		Item testItem4 = new Item("itemD", "NPOB", "good", "small", "NOTE", "DESC", 10., 4);
		Item testItem5 = new Item("itemE", "NPOB", "good", "small", "NOTE", "DESC", 35., 5);
		Item testItem6 = new Item("itemF", "NPOB", "good", "small", "NOTE", "DESC", 100., 6);
		
		testAuctionA.addItem(testItem1);
		testAuctionA.addItem(testItem2);
		testAuctionA.addItem(testItem3);
		
		testAuctionB.addItem(testItem4);
		testAuctionB.addItem(testItem5);
		testAuctionB.addItem(testItem6);

		testBidder.addBid(testCalendar, testItem1, testItem1.getMyMinBid());
		testBidder.addBid(testCalendar, testItem2, testItem2.getMyMinBid());
		testBidder.addBid(testCalendar, testItem3, testItem3.getMyMinBid());
		
		testBidder.addBid(testCalendar, testItem4, testItem4.getMyMinBid());
//		testBidder.addBid(testCalendar, testItem5, testItem5.getMyMinBid());
//		testBidder.addBid(testCalendar, testItem6, testItem6.getMyMinBid());
		
		myCalendar = testCalendar;
		currentBidder = testBidder;
	}

	public void startGUI(){
		myFrame.getContentPane().setLayout(null);
		myFrame.setName("Auction Central");
		myFrame.setBounds(100, 100, 804, 491);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
		
		
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(12, 18, 286, 16);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblYouAreSignedAs = new JLabel(loggedInAs+ currentBidder.getMyName());
		lblYouAreSignedAs.setFont(mainFont);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreSignedAs.setBounds(22, 40, 265, 16);
		myFrame.getContentPane().add(lblYouAreSignedAs);
		
		JLabel lblYourCurrentActive = new JLabel("Your current active bids:");
		lblYourCurrentActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCurrentActive.setFont(mainFont);
		lblYourCurrentActive.setBounds(225, 89, 185, 16);
		myFrame.getContentPane().add(lblYourCurrentActive);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setFont(mainFont);
		lblWhatWouldYou.setBounds(12, 89, 185, 16);
		myFrame.getContentPane().add(lblWhatWouldYou);
		
		JButton btnViewAuctions = new JButton("View Auctions");
		btnViewAuctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnViewAuctions.setFont(mainFont);
		btnViewAuctions.setBounds(27, 118, 150, 65);
		myFrame.getContentPane().add(btnViewAuctions);
		
		JButton btnAddABid = new JButton("add a bid");
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(27, 195, 150, 56);
		myFrame.getContentPane().add(btnAddABid);
		
		JButton btnRemoveABid = new JButton("remove a bid");
		btnRemoveABid.setFont(mainFont);
		btnRemoveABid.setBounds(27, 264, 150, 65);
		myFrame.getContentPane().add(btnRemoveABid);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.setFont(mainFont);
		btnLogOut.setBounds(27, 340, 150, 65);
		myFrame.getContentPane().add(btnLogOut);
		
		JTextArea txtrHereIsA = new JTextArea();
		txtrHereIsA.setFont(mainFont);
		txtrHereIsA.setBackground(SystemColor.control);
		txtrHereIsA.setEditable(false);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
		System.out.println(bids);
		txtrHereIsA.setText(bids);
		txtrHereIsA.setBounds(232, 114, 542, 317);
		myFrame.getContentPane().add(txtrHereIsA);
		

		

		

		
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
