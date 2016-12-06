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
import model.Bid;
import model.Bidder;
import model.AuctionCalendar;
import model.Item;
import model.NPO;
import model.User;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

	static AuctionCalendar myCalendar;
	static Bidder currentBidder;
	static Item theItem;

	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	Font subHeaderFont = new Font("Tahoma", Font.BOLD, 20);
	Font headerFont = new Font("Tahoma", Font.BOLD, 22);
	
	private JTextField txtBidAmount;
	JButton btnAddABid;
	private JTextArea itemInfo;
	private JScrollPane scrollPane;
	
	public BidderGUIaddBid(Bidder theBidder, AuctionCalendar theCalendar, int itemID) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		theItem = myCalendar.getItem(itemID);
		startGUI();
	}

	public void startGUI(){
		
		this.getContentPane().setLayout(null);
		this.setName("Auction Central");
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// top banner area start
		JLabel label_1 = new JLabel("Auction Central");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(headerFont);
		label_1.setBounds(0, 8, 782, 28);
		getContentPane().add(label_1);
		
		JLabel lblWelcomeBanner = new JLabel("Add a bid to your account");
		lblWelcomeBanner.setFont(subHeaderFont);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(261, 42, 260, 25);
		this.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblEnterABid = new JLabel("Enter a bid in the field below.");
		lblEnterABid.setFont(mainFont);
		lblEnterABid.setBounds(298, 125, 187, 19);
		getContentPane().add(lblEnterABid);
		
		JLabel label_2 = new JLabel("You are signed in as: " + currentBidder.getMyName());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(mainFont);
		label_2.setBounds(184, 70, 414, 19);
		getContentPane().add(label_2);
		// top banner area end
		
		btnAddABid = new JButton("Submit bid");
		btnAddABid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtBidAmount.getText().isEmpty()){
					checkIfCanBid(Double.parseDouble(txtBidAmount.getText()));
				}
			}
		});
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(314, 418, 154, 23);
		btnAddABid.setEnabled(true);
		this.getContentPane().add(btnAddABid);
		
		txtBidAmount = new JTextField();
		txtBidAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBidAmount.setBounds(185, 418, 107, 22);
		getContentPane().add(txtBidAmount);
		txtBidAmount.setColumns(10);
		txtBidAmount.setEnabled(true);
		
	

		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		button.setFont(mainFont);
		button.setBounds(474, 418, 154, 23);
		getContentPane().add(button);
		
		
		
		JLabel  lblYourBid = new JLabel("Enter your bid amount.");
		lblYourBid.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourBid.setBounds(161, 390, 131, 16);
		getContentPane().add(lblYourBid);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 154, 474, 218);
		getContentPane().add(scrollPane);
		
		itemInfo = new JTextArea();
		itemInfo.setWrapStyleWord(true);
		itemInfo.setLineWrap(true);
		itemInfo.setEditable(false);
		scrollPane.setViewportView(itemInfo);
		itemInfo.setText(infoPane());
		
		JLabel label = new JLabel("$");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(161, 421, 7, 16);
		getContentPane().add(label);
		

		
	}
	
	public String infoPane(){
		
		String itemName = theItem.getMyItemName();
		String auctionDate = myCalendar.getAuction(theItem.getMyItemID()).getAuctionDate().format(dateFormat);
		String minBidAmount = currency.format(theItem.getMyMinBid());
		String description = theItem.getMyDescription();
		
		String info = "Item:  "+itemName+"\r\nAuction Date:  "
				+auctionDate+"\r\nMinimum Bid Amount:  "+minBidAmount+"\r\nItem Description:  "+description;
		return info;
	}
	
	private boolean checkIfCanBid(double bidOffer) {
		boolean goodBid = false;
		final int bidAccepted = 1;
		final int bidBelowMinBid = 2;
		final int bidForItemAlreadyExists = 3;
		final int auctionHasStarted = 4;
		
		int reply = currentBidder.addBidGUI(myCalendar, theItem, bidOffer);
		int bidderResponse;
		
		switch (reply){
		case bidAccepted:
			String message = "You would like to place a bid of "+currency.format(bidOffer) + " for " +theItem.getItemName() +
				".\n Please click yes to confirm or no to cancel.";
			bidderResponse = JOptionPane.showConfirmDialog(this, message);
			if(bidderResponse == 0){
				currentBidder.placeBid(currentBidder.createBid(bidOffer, theItem, myCalendar));
				close();
			}
			break;
		case bidBelowMinBid:
			message = "Your bid is below the minimum bid of " + currency.format(theItem.getMyMinBid()) + 
				".\n Please enter a higher bid.";
			JOptionPane.showMessageDialog(this, message);
			break;
		case bidForItemAlreadyExists:
			message = "You already have a bid for this item.";
			JOptionPane.showMessageDialog(this, message);
			break;
		case auctionHasStarted:
			message = "Bidding has closed for this auction.";
			JOptionPane.showMessageDialog(this, message);
			break;
		default:
			
		}
		return goodBid;
	}

	private void close() {
		this.dispose();
	}
}
