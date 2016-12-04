package GUI;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.Dialog;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;



public class BidderGUIviewItems extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static Calendar myCalendar;
	static Bidder currentBidder;
	static Auction myAuction;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	
	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	Font headingFont = new Font("Tahoma", Font.BOLD, 20);
	private JTable table;
//	DefaultTableModel tableOfItems = new DefaultTableModel(new Object[][] {},	new String[] {"Item number", "Item", "Donor", "Description", "Minimum bid", "Your Bid"	});
	DefaultTableModel tableOfItems = new DefaultTableModel(new Object[][] {},	new String[] {"Item number", "Item", "Minimum bid", "Your Bid"	});

	BidderGUIaddBid addBidGUI;
	
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
		windowInits();
		populateItems();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(58, 76, 659, 270);
		getContentPane().add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int ItemID = (int) tableOfItems.getValueAt(table.getSelectedRow(), 0);
				
				addBidGUI = new BidderGUIaddBid(currentBidder, myCalendar, ItemID);
								
				addBidGUI.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				addBidGUI.setVisible(true);
	
			}
		});
		table.setBackground(SystemColor.control);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setModel(tableOfItems);
		
		JLabel lblClickAnItem = new JLabel("Click an item to bid to place a bid");
		lblClickAnItem.setFont(mainFont);
		lblClickAnItem.setHorizontalAlignment(SwingConstants.LEFT);
		lblClickAnItem.setBounds(58, 47, 299, 16);
		getContentPane().add(lblClickAnItem);
		
		JLabel lblNewLabel = new JLabel("Items on auction");
		lblNewLabel.setFont(headingFont);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(58, 13, 443, 21);
		getContentPane().add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnBack.setBounds(620, 359, 97, 25);
		getContentPane().add(btnBack);
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		
		
		
		this.setName("Auction Central");
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(SystemColor.control);
		
//		String bids = currentBidder.printBidsGUI(myCalendar);
		
	}
	private void populateItems() {
		tableOfItems.setRowCount(0);
		for (Item item : myAuction.getItemList()) {
			String yourBid = "-";
			if(currentBidder.getBid(item.getMyItemID()) != null){
							yourBid = currency.format(currentBidder.getBid(item.getMyItemID()).getMyBidAmount());
			}
//			tableOfItems.addRow(new Object[] { item.getMyItemID(),item.getMyItemName(), item.getMyDonor(), item.getMyDescription(), currency.format(item.getMyMinBid()), yourBid});
			tableOfItems.addRow(new Object[] { item.getMyItemID(),item.getMyItemName(), currency.format(item.getMyMinBid()), yourBid});
			
		}
	}
	private void windowInits() {
		
//		addBidGUI = new BidderGUIaddBid(currentBidder, myCalendar);
//		addBidGUI.setVisible(false);
		
	}
	private void close() {
		//this.setVisible(false);
		this.dispose();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
