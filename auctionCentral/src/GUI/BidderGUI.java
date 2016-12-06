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
import javax.swing.JDialog;
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

public class BidderGUI extends JDialog{
	protected static final int BID_TABLE = 1;
	protected static final int AUC_TABLE = 0;
	public int currentTableMode = AUC_TABLE;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

	private JFrame myFrame;
	static Calendar myCalendar = null;
	static Bidder currentBidder = null;
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static String loggedInAs = "You are signed in as: ";
	

	private String clickInstructionsAucList = "Click on an auction from the list if you would like to browse its items.";
	private String clickInstructionsBidList = "Click a bid if you would like to remove it or view its details.";

	public Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	Font mainHeaderFont = new Font("Tahoma", Font.BOLD, 22);
	
	private BidderGUIviewItems viewItems;
	private BidderGUIeditBid editBid;
	
	private JTable table;
	private DefaultTableModel tblModelAuc = new DefaultTableModel(new Object[][] {}, new String[] { "Organization", "Number of Items", "Date of Auction" });
	private DefaultTableModel tblModelBid = new DefaultTableModel(new Object[][] {}, new String[] { "Item Number", "Item", "Minimum Bid", "Your Bid" });

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable(){
//			@Override
//			public void run() {
//				try {
//					BidderGUI bidderWindow = new BidderGUI(currentBidder, myCalendar);
//					bidderWindow.myFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public BidderGUI(Bidder theBidder, Calendar theCalendar) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;

		//this is for testing
//		testBidderInit();

		populateTable();
		startGUI();
	

		
	}

	public void startGUI(){

		myFrame = new JFrame();
		myFrame.getContentPane().setLayout(null);
		myFrame.setName("Auction Central");
		myFrame.setBounds(100, 100, 800, 500);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblWelcomeBanner = new JLabel("Auction Central");
		lblWelcomeBanner.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWelcomeBanner.setFont(mainHeaderFont);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(0, 8, 782, 28);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		
		JLabel lblYouAreSignedAs = new JLabel(loggedInAs + currentBidder.getMyName());
		lblYouAreSignedAs.setFont(mainFont);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreSignedAs.setBounds(289, 70, 205, 19);
		myFrame.getContentPane().add(lblYouAreSignedAs);
		
		//panel that holds the list instructions
		JPanel listLabel = new JPanel();
		listLabel.setBounds(0, 125, 782, 16);
		myFrame.getContentPane().add(listLabel);
		listLabel.setLayout(null);
		
		//list instructions for the auction list view
		JLabel lblAuctionsList = new JLabel(clickInstructionsAucList);
		lblAuctionsList.setBounds(0, 0, 782, 16);
		listLabel.add(lblAuctionsList);
		lblAuctionsList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionsList.setFont(mainFont);
		
		//list instructions for the bid list view
		JLabel lblBidsList = new JLabel(clickInstructionsBidList);
		lblBidsList.setBounds(0, 0, 782, 16);
		lblBidsList.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidsList.setFont(mainFont);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myFrame.dispose();
				MainGUI.myFrame.setVisible(true);
//				System.exit(0);
			}
		});
		btnLogOut.setFont(mainFont);
		btnLogOut.setBounds(478, 385, 150, 55);
		myFrame.getContentPane().add(btnLogOut);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the bidder main menu");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeToThe.setBounds(217, 42, 349, 25);
		myFrame.getContentPane().add(lblWelcomeToThe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(154, 154, 474, 218);
		scrollPane.setBorder(border);
		myFrame.getContentPane().add(scrollPane);
		
		
		//here is the table
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(currentTableMode == AUC_TABLE){
					String npoName = (String) tblModelAuc.getValueAt(table.getSelectedRow(), 0);
					Auction auc = myCalendar.getAuctionWithAucID(npoName);
					viewItems = new BidderGUIviewItems(currentBidder, myCalendar, auc);
					viewItems.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					viewItems.setVisible(true);
					updates();
				}
				if(currentTableMode == BID_TABLE){
					int itemID = (int) tblModelBid.getValueAt(table.getSelectedRow(), 0);
					Item item = myCalendar.getItem(itemID);
					editBid = new BidderGUIeditBid(currentBidder, myCalendar, item);
					editBid.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					editBid.setVisible(true);
					updates();
				}
			}
		});
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setFocusTraversalKeysEnabled(false);
		table.setBorder(null);
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
		table.setBorder(border);
		table.setAlignmentY(SwingConstants.CENTER);
		
		JButton btnViewBids = new JButton("View Auctions");
		btnViewBids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(tblModelAuc);
				currentTableMode = AUC_TABLE;
				listLabel.remove(lblBidsList);
				listLabel.add(lblAuctionsList);
				listLabel.updateUI();
				table.updateUI();
			}
		});
		btnViewBids.setFont(mainFont);
		btnViewBids.setBounds(316, 385, 150, 55);
		myFrame.getContentPane().add(btnViewBids);
		
		JButton btnViewYourBids = new JButton("View Your Bids");
		btnViewYourBids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(tblModelBid);
				currentTableMode = BID_TABLE;
				listLabel.remove(lblAuctionsList);
				listLabel.add(lblBidsList);
				listLabel.updateUI();
				table.updateUI();
			}
		});
		btnViewYourBids.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewYourBids.setBounds(154, 385, 150, 55);
		myFrame.getContentPane().add(btnViewYourBids);
		
	}
	private void updates(){
		populateTable();
		table.updateUI();
	}
	private void populateTable() {
			tblModelBid.setRowCount(0);
			for(Bid bid : currentBidder.getMyBids()){
				Item ThisItem = myCalendar.getItem(bid.getMyItemID());
				String bidAmount = currency.format(currentBidder.getBid(ThisItem.getMyItemID()).getMyBidAmount());
				String minBid = currency.format(ThisItem.getMyMinBid());
				
				tblModelBid.addRow(new Object[] { ThisItem.getMyItemID(), ThisItem.getItemName() , minBid, bidAmount});
			}
			

			tblModelAuc.setRowCount(0);
			for (Auction auc : myCalendar.getAllAuctions()) {
				tblModelAuc.addRow(new Object[] { auc.getNPOname().getMyName(), auc.getMyItemList().size(), auc.getAuctionDate().format(dateFormat)});
			}

	}
	public JFrame getFrame() {
		return myFrame;
	}

	/**
	 * @author aaron
	 * Creates a test bidder, test calendar, two test auctions and six test items.
	 * Used for debugging since the login/registration gui is not made yet.
	 */
	private static void testBidderInit() {
		
		Bidder testBidder = new Bidder("userName","Mr Bidder","address","phone","email","payInfo");
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
}
