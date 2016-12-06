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
//aipsndipfnps
import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.border.Border;

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
import javax.swing.JToggleButton;
import javax.swing.JTextPane;



public class BidderGUIeditBid extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static AuctionCalendar myCalendar;
	static Bidder currentBidder;
	static Item theItem;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	private String message = "Enter your bid amount.";
	

	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	Font subHeaderFont = new Font("Tahoma", Font.BOLD, 20);
	Font headerFont = new Font("Tahoma", Font.BOLD, 22);
	
	JButton btnRemoveBid;
//	JLabel lblYourBid;
	
	
	public BidderGUIeditBid(Bidder theBidder, AuctionCalendar theCalendar, Item item) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		theItem = item;
		
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
		
		JLabel lblWelcomeBanner = new JLabel("Remove a bid from yuor account.");
		lblWelcomeBanner.setFont(subHeaderFont);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(168, 42, 447, 25);
		this.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblEnterABid = new JLabel("More information about your bid.");
		lblEnterABid.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterABid.setFont(mainFont);
		lblEnterABid.setBounds(247, 125, 289, 19);
		getContentPane().add(lblEnterABid);
		
		JLabel label_2 = new JLabel("You are signed in as: " + currentBidder.getMyName());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(mainFont);
		label_2.setBounds(184, 70, 414, 19);
		getContentPane().add(label_2);
		// top banner area end	
		
		btnRemoveBid = new JButton("Remove bid");
		btnRemoveBid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfCanRemove();
			}
		});
		btnRemoveBid.setFont(mainFont);
		btnRemoveBid.setBounds(314, 418, 154, 23);
		btnRemoveBid.setEnabled(true);
		this.getContentPane().add(btnRemoveBid);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		button.setFont(mainFont);
		button.setBounds(474, 418, 154, 23);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(154, 154, 474, 218);
		getContentPane().add(scrollPane);
		
		JTextArea txtpnItemNameAuction = new JTextArea();
		txtpnItemNameAuction.setLineWrap(true);
		txtpnItemNameAuction.setWrapStyleWord(true);
		txtpnItemNameAuction.setEditable(false);
		scrollPane.setViewportView(txtpnItemNameAuction);
		txtpnItemNameAuction.setText(infoPane());
		
	}
	
	public String infoPane(){
		
		String itemName = theItem.getMyItemName();
		String auctionDate = myCalendar.getAuction(theItem.getMyItemID()).getAuctionDate().format(dateFormat);
		currentBidder.getBid(theItem.getMyItemID()).getMyBidAmount();
		String bidAmount = currency.format(currentBidder.getBid(theItem.getMyItemID()).getMyBidAmount());
		String description = theItem.getMyDescription();
		
		String info = "Item name:  "+itemName+"\r\nAuction Date:  "
				+auctionDate+"\r\nBid Amount:  "+bidAmount+"\r\nItem Description:  "+description;
		return info;
	}
	
	private int checkIfCanRemove() {
		
		int result = currentBidder.bidRemovalRequest(theItem.getMyItemID(), myCalendar);
		int bidderResponse;
		int buffer = currentBidder.getBidCancelBuffer();
		
		
		switch (result){
		case 1:
			message = "Are you sure that you want to cancel this bid? \n This action can not be undone.";
			bidderResponse = JOptionPane.showConfirmDialog(this, message);
			
			if(bidderResponse == 0){
				currentBidder.removeTheBid(theItem.getMyItemID());
				close();
			}
			break;
		case 2:
			message = "Sorry, bids can not be canceled within "+ buffer +" days of the auction.";
			JOptionPane.showMessageDialog(this, message);
			break;
		case 3:
			message = "Sorry, you have no bid for this item.";
			JOptionPane.showMessageDialog(this, message);
			break;
		default:
			
		}
		return result;
	}

	private void close() {
		this.dispose();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
