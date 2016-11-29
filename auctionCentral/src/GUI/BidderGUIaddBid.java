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



public class BidderGUIaddBid extends JFrame{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM"			+ " d yyyy, hh:mm a");
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar;
	static Bidder currentBidder;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);

	
	public BidderGUIaddBid(Bidder theBidder, Calendar theCalendar) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		
		
		
		startGUI();
		
		

		
	}

	public void startGUI(){
		this.getContentPane().setLayout(null);
		this.setName("Auction Central");
		this.setBounds(100, 100, 804, 491);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(12, 18, 286, 16);
		this.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblYouAreSignedAs = new JLabel(loggedInAs+ currentBidder.getMyName());
		lblYouAreSignedAs.setFont(mainFont);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreSignedAs.setBounds(22, 40, 265, 16);
		this.getContentPane().add(lblYouAreSignedAs);
		
		JLabel lblYourCurrentActive = new JLabel("Your current active bids:");
		lblYourCurrentActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCurrentActive.setFont(mainFont);
		lblYourCurrentActive.setBounds(225, 89, 185, 16);
		this.getContentPane().add(lblYourCurrentActive);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setFont(mainFont);
		lblWhatWouldYou.setBounds(12, 89, 185, 16);
		this.getContentPane().add(lblWhatWouldYou);
		
		JButton btnAddABid = new JButton("add a bid");
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(27, 195, 150, 56);
		this.getContentPane().add(btnAddABid);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.setFont(mainFont);
		btnLogOut.setBounds(27, 340, 150, 65);
		this.getContentPane().add(btnLogOut);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
//		System.out.println(bids);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(border);
		scrollPane.setBorder( border );
		scrollPane.setBounds(232, 114, 542, 317);
		
		this.getContentPane().add(scrollPane);
		
		JTextArea txtrHereIsA = new JTextArea();
		scrollPane.setViewportView(txtrHereIsA);
		txtrHereIsA.setFont(mainFont);
		txtrHereIsA.setBackground(SystemColor.control);
		txtrHereIsA.setEditable(false);
		txtrHereIsA.setText(bids);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
