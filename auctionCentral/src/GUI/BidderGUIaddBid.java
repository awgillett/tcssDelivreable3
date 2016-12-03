package GUI;

import java.awt.EventQueue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
import javax.swing.ScrollPaneConstants;



public class BidderGUIaddBid extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM d yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar;
	static Bidder currentBidder;
	static Item theItem;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	private String message = "Enter your bid amount.";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	private JTextField txtBidAmount;
	private JTable table;

	DefaultTableModel itemDetails = new DefaultTableModel(new Object[][] {},new String[] {"Item number", "Item", "Donor", "Description", "Minimum bid", "Your Bid"});
	private JScrollPane scrollPane;
	JButton btnAddABid;
//	JLabel lblYourBid;
	
	
	public BidderGUIaddBid(Bidder theBidder, Calendar theCalendar, int itemID) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		theItem = myCalendar.getItem(itemID);
		
		
		
		startGUI();
		
		

		
	}

	public void startGUI(){
		populateItems();
		
		this.getContentPane().setLayout(null);
		this.setName("Auction Central");
		this.setBounds(100, 100, 804, 491);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(0, 18, 786, 48);
		this.getContentPane().add(lblWelcomeBanner);
		
		btnAddABid = new JButton("Submit bid");
		btnAddABid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(Double.parseDouble(txtBidAmount.getText()));
				
				checkIfCanBid(Double.parseDouble(txtBidAmount.getText()));
				
				
			}
		});
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(441, 375, 150, 56);
		btnAddABid.setEnabled(true);
		this.getContentPane().add(btnAddABid);
		
		
		txtBidAmount = new JTextField();
		txtBidAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBidAmount.setBounds(247, 393, 172, 22);
		getContentPane().add(txtBidAmount);
		txtBidAmount.setColumns(10);
		txtBidAmount.setEnabled(true);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(603, 375, 150, 56);
		getContentPane().add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(border);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(57, 231, 696, 69);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setModel(itemDetails);
		table.setBackground(SystemColor.control);
		
		
		
		JLabel  lblYourBid = new JLabel(message);
		lblYourBid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourBid.setBounds(217, 364, 202, 16);
		getContentPane().add(lblYourBid);
		
		JLabel lblItemToBid = new JLabel("Item to Bid on");
		lblItemToBid.setBounds(57, 202, 108, 16);
		getContentPane().add(lblItemToBid);
		
	}
	private boolean checkIfCanBid(double bidOffer) {
		boolean goodBid = false;
		int bidAccepted = 1;
		int bidBelowMinBid = 2;
		int bidForItemAlreadyExists = 3;
		int auctionHasStarted = 4;
		
		int reply = currentBidder.addBidGUI(myCalendar, theItem, bidOffer);
		
		switch (reply){
		case 1:
			message = "";
			System.out.println(message);
			break;
		case 2:
			message = "Your bid is below the minimum bid.";
			System.out.println(message);
			break;
		case 3:
			message = "You already have a bid for this item.";
			System.out.println(message);
			break;
		case 4:
			message = "Bidding has closed for this auction.";
			System.out.println(message);
			break;
		default:
			
		}
		return goodBid;
	}

	private void populateItems() {
	itemDetails.setRowCount(0);
		String yourBid = "-";
		if(currentBidder.getBid(theItem.getMyItemID()) != null){
						yourBid = currency.format(currentBidder.getBid(theItem.getMyItemID()).getMyBidAmount());
		}
		itemDetails.addRow(new Object[] { theItem.getMyItemID(),theItem.getMyItemName(), theItem.getMyDonor(), theItem.getMyDescription(), currency.format(theItem.getMyMinBid()), yourBid});
	}
	private void close() {
		//this.setVisible(false);
		this.dispose();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
