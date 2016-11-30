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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class BidderGUIviewAuctions extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM"			+ " d yyyy, hh:mm a");
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar;
	static Bidder currentBidder;
	
	private static String welcomeBanner = "Auction view";
	private static String loggedInAs = "You are signed in as: ";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	private JTextField txtItemNumber;
	private JTextField txtBidAmount;
	private JTable table;

	
	public BidderGUIviewAuctions(Bidder theBidder, Calendar theCalendar) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		
		
		
		startGUI();
		
		

		
	}

	public void startGUI(){
		this.getContentPane().setLayout(null);
		this.setName("Auction Central");
		this.setBounds(100, 100, 804, 491);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnAddABid = new JButton("Submit bid");
		btnAddABid.setFont(mainFont);
		btnAddABid.setBounds(624, 375, 150, 56);
		this.getContentPane().add(btnAddABid);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
		
		txtItemNumber = new JTextField();
		txtItemNumber.setBounds(55, 393, 116, 22);
		getContentPane().add(txtItemNumber);
		txtItemNumber.setColumns(10);
		
		txtBidAmount = new JTextField();
		txtBidAmount.setBounds(225, 393, 116, 22);
		getContentPane().add(txtBidAmount);
		txtBidAmount.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(192);
		table.setBounds(22, 69, 737, 293);
		getContentPane().add(table);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
