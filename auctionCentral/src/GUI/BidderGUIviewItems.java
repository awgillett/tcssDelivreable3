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
import model.AuctionCalendar;
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



public class BidderGUIviewItems extends JDialog{
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy, hh:mm a");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );

//	protected JFrame myFrame = new JFrame();
	
	static AuctionCalendar myCalendar;
	static Bidder currentBidder;
	static Auction myAuction;
	
	private static String welcomeBanner = "Add a bid to your account";
	private static String loggedInAs = "You are signed in as: ";
	
//	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
//	Font mainHeaderFont = new Font("Tahoma", Font.BOLD, 22);
//	Font headingFont = new Font("Tahoma", Font.BOLD, 20);
	

	Font mainFont = new Font("Tahoma", Font.PLAIN, 15);
	Font subHeaderFont = new Font("Tahoma", Font.BOLD, 20);
	Font headerFont = new Font("Tahoma", Font.BOLD, 22);
	
	
	private JTable table;
//	DefaultTableModel tableOfItems = new DefaultTableModel(new Object[][] {},	new String[] {"Item number", "Item", "Donor", "Description", "Minimum bid", "Your Bid"	});
	DefaultTableModel tableOfItems = new DefaultTableModel(new Object[][] {},	new String[] {"Item number", "Item", "Minimum bid", "Your Bid"	});

	BidderGUIaddBid addBidGUI;
	
	public BidderGUIviewItems(Bidder theBidder, AuctionCalendar theCalendar, Auction theAuction) {
		
		currentBidder = theBidder;
		myCalendar = theCalendar;
		myAuction = theAuction;
		
		
		startGUI();
		
		

		
	}

	public void startGUI(){
		this.getContentPane().setLayout(null);
		
		this.setName("Auction Central");
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(SystemColor.control);
		
		windowInits();
		populateItems();
		
//		//instructions
//		JLabel lblClickAnItem = new JLabel("Click an item to bid to place a bid");
//		lblClickAnItem.setFont(mainFont);
//		lblClickAnItem.setHorizontalAlignment(SwingConstants.CENTER);
//		lblClickAnItem.setBounds(0, 125, 782, 16);
//		getContentPane().add(lblClickAnItem);
//		
//		//main header
//		JLabel lblItemsInAuc = new JLabel("Items on auction");
//		lblItemsInAuc.setVerticalAlignment(SwingConstants.BOTTOM);
//		lblItemsInAuc.setFont(headerFont);
//		lblItemsInAuc.setHorizontalAlignment(SwingConstants.CENTER);
//		lblItemsInAuc.setBounds(0, 8, 782, 28);
//		getContentPane().add(lblItemsInAuc);
		
				
		// top banner area start
		JLabel label_1 = new JLabel("Auction Central");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(headerFont);
		label_1.setBounds(0, 8, 782, 28);
		getContentPane().add(label_1);
		
		JLabel lblWelcomeBanner = new JLabel("Browse the items on auction.");
		lblWelcomeBanner.setFont(subHeaderFont);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBanner.setBounds(168, 42, 447, 25);
		this.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblEnterABid = new JLabel("Click on an item to view more details and make a bid.");
		lblEnterABid.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterABid.setFont(mainFont);
		lblEnterABid.setBounds(217, 125, 348, 19);
		getContentPane().add(lblEnterABid);
		
		JLabel label_2 = new JLabel("You are signed in as: " + currentBidder.getMyName());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(mainFont);
		label_2.setBounds(184, 70, 414, 19);
		getContentPane().add(label_2);
		// top banner area end	

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(154, 154, 474, 218);
		scrollPane.setBorder(border);
		getContentPane().add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int ItemID = (int) tableOfItems.getValueAt(table.getSelectedRow(), 0);
				addBidGUI = new BidderGUIaddBid(currentBidder, myCalendar, ItemID);
				addBidGUI.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				addBidGUI.setVisible(true);
				
				populateItems();
				table.updateUI();
	
			}
		});
		table.setBackground(SystemColor.control);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setModel(tableOfItems);
		

		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnBack.setBounds(474, 418, 154, 23);
		getContentPane().add(btnBack);
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		
		

	}
	private void populateItems() {
		tableOfItems.setRowCount(0);
		for (Item item : myAuction.getItemList()) {
			String yourBid = "-";
			if(currentBidder.getBid(item.getMyItemID()) != null){
							yourBid = currency.format(currentBidder.getBid(item.getMyItemID()).getMyBidAmount());
			}
			tableOfItems.addRow(new Object[] { item.getMyItemID(),item.getMyItemName(), currency.format(item.getMyMinBid()), yourBid});
			
		}
	}
	private void windowInits() {
		
	}
	private void close() {
		//this.setVisible(false);
		this.dispose();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
