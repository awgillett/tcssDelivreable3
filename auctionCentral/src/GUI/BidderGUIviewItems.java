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



public class BidderGUIviewItems extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM d yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar;
	static Bidder currentBidder;
	static Auction myAuction;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	private JTable table;
	DefaultTableModel tableOfItems = new DefaultTableModel(new Object[][] {},	new String[] {"Item", "Donor", "Description", "Minimum bid", "Your Bid"	});

	
	public BidderGUIviewItems(Bidder theBidder, Calendar theCalendar, Auction theAuction) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		myAuction = theAuction;
		
		
		startGUI();
		
		

		
	}
	
//	private String myItemName = "";
//	private String myDonor = "";
//	private String myCondition = "";
//	private String mySize = "";
//	private String myNotes = "";
//	private String myDescription = "";
//	private int myItemID;
//	private double myMinBid;

	public void startGUI(){
		this.getContentPane().setLayout(null);
		populateItems();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(58, 76, 659, 307);
		getContentPane().add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		table.setBackground(SystemColor.control);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setModel(tableOfItems);
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		this.setName("Auction Central");
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String bids = currentBidder.printBidsGUI(myCalendar);
		
	}
	private void populateItems() {
		tableOfItems.setRowCount(0);
		for (Item item : myAuction.getItemList()) {
			String yourBid = "-";
			if(currentBidder.getBid(item.getMyItemID()) != null){
				
				yourBid = currency.format(currentBidder.getBid(item.getMyItemID()).getMyBidAmount());
				
			}
			tableOfItems.addRow(new Object[] { item.getMyItemName(), item.getMyDonor(), item.getMyDescription(), currency.format(item.getMyMinBid()), yourBid});
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
