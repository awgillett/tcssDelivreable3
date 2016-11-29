package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import model.Auction;
import model.Calendar;
import model.NPO;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class NPOGUI {

	private JFrame frame;
	static Calendar curCalendar;
	private Auction curAuction;
	AuctionEditGUI editMenu;
	static NPO curNPO;
	String auctionInfo;
	private JLabel lblAuctionInfo;
	private JButton btnEditAuction;
	private JButton btnRequest;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM"
			+ " d yyyy, hh:mm a");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NPOGUI window = new NPOGUI(new NPO("Joey", "Joe"), new Calendar());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NPOGUI(NPO theNPO, Calendar theCalendar) {
		curNPO = theNPO;
		curCalendar = theCalendar;
		curCalendar.addAuction(curNPO, LocalDateTime.now().plusDays(15), 15, "");
		loadAuctionOverview();
		initialize();
		editMenu = new AuctionEditGUI(theNPO, theCalendar);
		editMenu.setVisible(false);
		editMenu.setModal(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.BLUE);

		JLabel lblNewLabel = new JLabel("Welcome to the NPO Main Menu");
		lblNewLabel.setBounds(0, 29, 788, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Logged in as: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 52, 366, 24);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(102, 137, 85, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblAuctionCentral = new JLabel("Auction Central");
		lblAuctionCentral.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionCentral.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblAuctionCentral.setBounds(0, 0, 788, 24);
		frame.getContentPane().add(lblAuctionCentral);

		JLabel txtUser = new JLabel(curNPO.getMyName());
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setBounds(386, 52, 392, 24);
		frame.getContentPane().add(txtUser);

		lblAuctionInfo = new JLabel(auctionInfo);
		lblAuctionInfo.setBounds(20, 87, 450, 115);
		frame.getContentPane().add(lblAuctionInfo);
		
		JLabel lblNewLabel_2 = new JLabel("What would you like to do today?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(33, 297, 306, 34);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnRequest = new JButton("Submit Auction Request");
		btnRequest.setToolTipText("Click here to submit an Auction Request");
		btnRequest.setBounds(81, 379, 154, 23);
		frame.getContentPane().add(btnRequest);
		if (curNPO.hasAuction())
			btnRequest.setEnabled(false);
		
		btnEditAuction = new JButton("Edit Current Auction");
		btnEditAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editMenu.setVisible(true);
				updateStatus();
			}
		});
		btnEditAuction.setToolTipText("Click here to edit items, add items, remove items, or cancel the auction.");
		btnEditAuction.setBounds(316, 379, 154, 23);
		frame.getContentPane().add(btnEditAuction);
		if (!curNPO.hasAuction())
			btnEditAuction.setEnabled(false);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnLogOut.setBounds(551, 379, 154, 23);
		frame.getContentPane().add(btnLogOut);
	}

	private void loadAuctionOverview() {
		if (!curNPO.hasAuction()) {
			auctionInfo = "You have no upcoming auctions.";
		} else {
			curAuction = curCalendar.getAuction(curNPO);
			curAuction.addItem("Car", "Dr Phil", "Good", "Medium", "No Notes", "Honda Civic", 1000);
			curAuction.addItem("Calculator", "Dr Phill", "Good", "Medium", "No Notes", "TI NSpire CAS", 40);
			curAuction.addItem("Football", "Sherman", "Good", "Medium", "No Notes", "Seahawks signed football", 40);
			auctionInfo = "<html>Auction Overview:<br>"
					    + "You have an auction scheduled on " + curAuction.getAuctionDate().format(dateFormat) + "<br>"
					    + "You have " + curAuction.getMyItemList().size() + " items currently listed in this auction.</html>";
		}
	}
	
	private void updateStatus() {
		loadAuctionOverview();
		lblAuctionInfo.setText(auctionInfo);
		if (!curNPO.hasAuction()) {
			btnEditAuction.setEnabled(false);
			btnRequest.setEnabled(true);
		}
		else {
			btnEditAuction.setEnabled(true);
			btnRequest.setEnabled(false);
		}
	}
	
}
