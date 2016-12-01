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
import model.Calendar;
import model.Item;
import model.NPO;
import model.User;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BidderGUI{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy");
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar = null;
	static Bidder currentBidder = null;
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static String loggedInAs = "You are signed in as: ";
	
	public Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	
	BidderGUIaddBid addBidGUI;
	BidderGUIviewAuctions viewAuctionsGUI;
	private JTable table;
	private DefaultTableModel tblModelAuctions = new DefaultTableModel(new Object[][] {}, new String[] { "Organization", "Number of Items", "Date of Auction" });
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					
					
					
					BidderGUI bidderWindow = new BidderGUI(currentBidder, myCalendar);
					bidderWindow.myFrame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

	}
	
	public BidderGUI(Bidder theBidder, Calendar theCalendar) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;

		//this is for testing
		testBidderInit();
		
		startGUI();
	

		
	}

	/**
	 * @author aaron
	 * Creates a test bidder, test calendar, two test auctions and six test items.
	 * Used for debugging since the login/registration gui is not made yet.
	 */
	private static void testBidderInit() {
		
		Bidder testBidder = new Bidder("userName","name","address","phone","email","payInfo");
		Calendar testCalendar = new Calendar();
		NPO testNpoA = new NPO("NPOa", "goodwill");
		NPO testNpoB = new NPO("NPOb", "st matthews");
		NPO testNpoC = new NPO("NPOc", "compassion int");
		NPO testNpoD = new NPO("NPOd", "red cross");
		
		Auction testAuctionA = new Auction(testNpoA, LocalDateTime.now().plusDays(7), 10, "Notes", 1);
		Auction testAuctionB = new Auction(testNpoB, LocalDateTime.now().plusDays(10), 10, "Notes", 1);

		Auction testAuctionC = new Auction(testNpoC, LocalDateTime.now().plusDays(11), 10, "Notes", 1);
		Auction testAuctionD = new Auction(testNpoD, LocalDateTime.now().plusDays(12), 10, "Notes", 1);

		testCalendar.addAuction(testAuctionA);
		testCalendar.addAuction(testAuctionB);

		testCalendar.addAuction(testAuctionC);
		testCalendar.addAuction(testAuctionD);
		
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
		

		
		runUpdates();
		
		addBidGUI = new BidderGUIaddBid(currentBidder, myCalendar);
		viewAuctionsGUI = new BidderGUIviewAuctions(currentBidder, myCalendar);
		addBidGUI.setVisible(false);
		viewAuctionsGUI.setVisible(false);
		myFrame.getContentPane().setLayout(null);
		myFrame.setName("Auction Central");
		myFrame.setBounds(100, 100, 800, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
		
		
		JLabel lblWelcomeBanner = new JLabel("Auction Central");
		lblWelcomeBanner.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWelcomeBanner.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(-1, 8, 782, 28);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblYouAreSignedAs = new JLabel(loggedInAs + currentBidder.getMyName());
		lblYouAreSignedAs.setFont(mainFont);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreSignedAs.setBounds(-1, 70, 782, 16);
		myFrame.getContentPane().add(lblYouAreSignedAs);
		
		JLabel lblYourCurrentActive = new JLabel("Here is a list of scheduled auctions");
		lblYourCurrentActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCurrentActive.setFont(mainFont);
		lblYourCurrentActive.setBounds(235, 125, 318, 16);
		myFrame.getContentPane().add(lblYourCurrentActive);
		
		JButton btnViewAuctions = new JButton("View Auctions");
		btnViewAuctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewAuctionsGUI.setVisible(true);
			}
		});
		btnViewAuctions.setFont(mainFont);
		btnViewAuctions.setBounds(12, 148, 150, 65);
		myFrame.getContentPane().add(btnViewAuctions);
		
		
		JButton btnAddABid = new JButton("add a bid");
		btnAddABid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBidGUI.setVisible(true);
			}
		});
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(12, 226, 150, 56);
		myFrame.getContentPane().add(btnAddABid);
		
		JButton btnRemoveABid = new JButton("remove a bid");
		btnRemoveABid.setFont(mainFont);
		btnRemoveABid.setBounds(12, 297, 150, 65);
		myFrame.getContentPane().add(btnRemoveABid);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.setFont(mainFont);
		btnLogOut.setBounds(12, 375, 150, 65);
		myFrame.getContentPane().add(btnLogOut);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
		System.out.println(bids);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the bidder main menu");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeToThe.setBounds(-1, 42, 782, 16);
		myFrame.getContentPane().add(lblWelcomeToThe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 154, 535, 286);
		myFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.control);
		scrollPane.setViewportView(table);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		
		table.setModel(tblModelAuctions);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setAlignmentY(SwingConstants.CENTER);
		
	}
	private void runUpdates(){
		populateAuctions();
		
	}
	private void populateAuctions() {
		tblModelAuctions.setRowCount(0);
		for (Auction auc : myCalendar.getAllAuctions()) {
//			System.out.println(auc.getNPOname().getMyName() +" "+ auc.getMyItemList().size() +" "+ auc.getAuctionDate());
			tblModelAuctions.addRow(new Object[] { auc.getNPOname().getMyName(), auc.getMyItemList().size(), auc.getAuctionDate().format(dateFormat)});
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
