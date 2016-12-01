package GUI;

import java.awt.EventQueue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
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
import model.Bid;
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
import java.awt.Dialog;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.NumberFormat;

public class BidderGUI{
	protected static final int BID_TABLE = 1;
	protected static final int AUC_TABLE = 0;
	public int currentTableMode = AUC_TABLE;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar = null;
	static Bidder currentBidder = null;
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static String loggedInAs = "You are signed in as: ";
	
	public Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	
	BidderGUIaddBid addBidGUI;
	BidderGUIviewAuctions viewAuctionsGUI;
	BidderGUIviewItems viewItems;
	
	private JTable table;
	private DefaultTableModel tblModelAuc = new DefaultTableModel(new Object[][] {}, new String[] { "Organization", "Number of Items", "Date of Auction" });
	private DefaultTableModel tblModelBid = new DefaultTableModel(new Object[][] {}, new String[] { "Item", "Minimum Bid", "Your Bid" });
//	private DefaultTableModel tblModel;
	private int selectedAuctionRow;
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
//		load();
		
		windowInits();
		startGUI();
	

		
	}

	private void windowInits() {
		
		addBidGUI = new BidderGUIaddBid(currentBidder, myCalendar);
		viewAuctionsGUI = new BidderGUIviewAuctions(currentBidder, myCalendar);
		addBidGUI.setVisible(false);
		viewAuctionsGUI.setVisible(false);
		
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
		testAuctionC.addItem(testItem5);
		testAuctionD.addItem(testItem6);

		testBidder.addBid(testCalendar, testItem1, testItem1.getMyMinBid()+42);
		testBidder.addBid(testCalendar, testItem2, testItem2.getMyMinBid()+1);
		testBidder.addBid(testCalendar, testItem3, testItem3.getMyMinBid()+3);
		
		testBidder.addBid(testCalendar, testItem4, testItem4.getMyMinBid());
//		testBidder.addBid(testCalendar, testItem5, testItem5.getMyMinBid());
//		testBidder.addBid(testCalendar, testItem6, testItem6.getMyMinBid());
		
		myCalendar = testCalendar;
		currentBidder = testBidder;
	}

	public void startGUI(){
		
		populateTable();
		

		
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
		
		JLabel lblYourCurrentActive = new JLabel("Click an Auction to see what items are up for bid");
		lblYourCurrentActive.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourCurrentActive.setFont(mainFont);
		lblYourCurrentActive.setBounds(235, 125, 535, 16);
		myFrame.getContentPane().add(lblYourCurrentActive);
		
		

		
		JButton btnRemoveABid = new JButton("Edit Your Bids");
		btnRemoveABid.setFont(mainFont);
		btnRemoveABid.setBounds(12, 317, 150, 55);
		myFrame.getContentPane().add(btnRemoveABid);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.setFont(mainFont);
		btnLogOut.setBounds(12, 385, 150, 55);
		myFrame.getContentPane().add(btnLogOut);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
		System.out.println(bids);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the bidder main menu");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeToThe.setBounds(-1, 42, 782, 16);
		myFrame.getContentPane().add(lblWelcomeToThe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(235, 154, 535, 286);
		myFrame.getContentPane().add(scrollPane);
		
		
		//here is the table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				if(currentTableMode == AUC_TABLE){
				
					String npoName = (String) tblModelAuc.getValueAt(table.getSelectedRow(), 0);
					Auction auc = myCalendar.getAuctionWithAucID(npoName);
					
					viewItems = new BidderGUIviewItems(currentBidder, myCalendar, auc);
					viewItems.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					viewItems.setVisible(true);
				}
			}
		});
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setOpaque(false);
		table.setFocusTraversalKeysEnabled(false);
		table.setBorder(null);
		table.setAutoscrolls(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBackground(SystemColor.control);
		scrollPane.setViewportView(table);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setModel(tblModelAuc);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setAlignmentY(SwingConstants.CENTER);
		
		
		
		JButton btnViewBids = new JButton("View Auctions");
		btnViewBids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(tblModelAuc);
				currentTableMode = AUC_TABLE;
				table.updateUI();
			}
		});
		btnViewBids.setFont(mainFont);
		btnViewBids.setBounds(12, 249, 150, 55);
		myFrame.getContentPane().add(btnViewBids);
		
		JButton btnViewYourBids = new JButton("View Your Bids");
		btnViewYourBids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(tblModelBid);
				currentTableMode = BID_TABLE;
				table.updateUI();
			}
		});
		btnViewYourBids.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewYourBids.setBounds(12, 181, 150, 55);
		myFrame.getContentPane().add(btnViewYourBids);
		
	}
	private void runUpdates(){
//		populateAuctions();
		
	}
	private void populateTable() {
		

			tblModelBid.setRowCount(0);
			for(Bid bid : currentBidder.getMyBids()){
				Item ThisItem = myCalendar.getItem(bid.getMyItemID());
				String bidAmount = currency.format(currentBidder.getBid(ThisItem.getMyItemID()).getMyBidAmount());
				String minBid = currency.format(ThisItem.getMyMinBid());
				
				tblModelBid.addRow(new Object[] { ThisItem.getItemName() , minBid, bidAmount});
			}
			

			tblModelAuc.setRowCount(0);
			for (Auction auc : myCalendar.getAllAuctions()) {
				tblModelAuc.addRow(new Object[] { auc.getNPOname().getMyName(), auc.getMyItemList().size(), auc.getAuctionDate().format(dateFormat)});
			}

	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	private void load() {
		
		currentBidder = new Bidder("userName","name","address","phone","email","payInfo");
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

	}
}
