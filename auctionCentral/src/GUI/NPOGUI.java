package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import model.Auction;
import model.AuctionCalendar;
import model.NPO;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class NPOGUI extends JDialog{

	private JFrame frame;
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);
	static AuctionCalendar curCalendar;
	private Auction curAuction;
	private AuctionEditGUI editMenu;
	private AuctionRequestGUI requestMenu;
	static NPO curNPO;
	String auctionInfo;
	private JLabel lblAuctionInfo;
	private JButton btnEditAuction;
	private JButton btnRequest;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM" + " d yyyy, hh:mm a");

//<<<<<<< HEAD
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NPOGUI window = new NPOGUI(new NPO("Joey", "Joe"), new Calendar());
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//=======
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NPOGUI window = new NPOGUI(new NPO("Joey", "Joe"), new AuctionCalendar());
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//>>>>>>> refs/heads/PatrickBranch

	/**
	 * Create the application.
	 */
	public NPOGUI(NPO theNPO, AuctionCalendar theCalendar) {
		curNPO = theNPO;
		curCalendar = theCalendar;
		//curCalendar.addAuction(curNPO, LocalDateTime.now().plusDays(15), 15, "");
		loadAuctionOverview();
		initialize();
	}
//<<<<<<< HEAD
//	
//	protected JFrame getFrame(){
//		return frame;
//	}
//
//	/**
//	 * Initialize the GUI components of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 800, 500);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.setBackground(Color.BLUE);
//
//		JLabel lblNewLabel = new JLabel("Welcome to the NPO Main Menu");
//		lblNewLabel.setBounds(0, 29, 788, 24);
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setFont(subMenuFont);
//		frame.getContentPane().add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("Logged in as: " + curNPO.getMyName());
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setFont(detailsFont);
//		lblNewLabel_1.setBounds(10, 52, 764, 24);
//		frame.getContentPane().add(lblNewLabel_1);
//
//		JLabel lblNewLabel_3 = new JLabel();
//		lblNewLabel_3.setFont(detailsFont);
//		lblNewLabel_3.setBounds(102, 137, 85, 14);
//		frame.getContentPane().add(lblNewLabel_3);
//
//		JLabel lblAuctionCentral = new JLabel("Auction Central");
//		lblAuctionCentral.setHorizontalAlignment(SwingConstants.CENTER);
//		lblAuctionCentral.setFont(mainFont);
//		lblAuctionCentral.setBounds(0, 0, 788, 24);
//		frame.getContentPane().add(lblAuctionCentral);
//
//		lblAuctionInfo = new JLabel(auctionInfo);
//		lblAuctionInfo.setHorizontalAlignment(SwingConstants.CENTER);
//		lblAuctionInfo.setBounds(0, 87, 784, 115);
//		lblAuctionInfo.setFont(detailsFont);
//		frame.getContentPane().add(lblAuctionInfo);
//
//		JLabel lblNewLabel_2 = new JLabel("What would you like to do today?");
//		lblNewLabel_2.setFont(detailsFont);
//		lblNewLabel_2.setBounds(33, 297, 306, 34);
//		frame.getContentPane().add(lblNewLabel_2);
//
//		btnRequest = new JButton("Submit Auction Request");
//		btnRequest.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				requestMenu = new AuctionRequestGUI(curNPO, curCalendar);
//				requestMenu.setModal(true);
//				requestMenu.setVisible(true);
//				updateStatus();
//			}
//		});
//		btnRequest.setToolTipText("Click here to submit an Auction Request");
//		btnRequest.setBounds(81, 379, 154, 23);
//		frame.getContentPane().add(btnRequest);
//		if (curNPO.hasAuction())
//			btnRequest.setEnabled(false);
//
//		btnEditAuction = new JButton("Edit Current Auction");
//		btnEditAuction.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				editMenu = new AuctionEditGUI(curNPO, curCalendar);
//				//editMenu.setModal(true);
//				editMenu.setVisible(true);
//				updateStatus();
//			}
//		});
//		btnEditAuction.setToolTipText("Click here to edit items, add items, remove items, or cancel the auction.");
//		btnEditAuction.setBounds(316, 379, 154, 23);
//		frame.getContentPane().add(btnEditAuction);
//		if (!curNPO.hasAuction())
//			btnEditAuction.setEnabled(false);
//
//		JButton btnLogOut = new JButton("Log Out");
//		btnLogOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				System.exit(0);
//			}
//		});
//		btnLogOut.setBounds(551, 379, 154, 23);
//		frame.getContentPane().add(btnLogOut);
//	}
//
//	/**
//	 * Loads the details of the upcoming auction (if any).
//	 */
//	private void loadAuctionOverview() {
//		if (!curNPO.hasAuction()) {
//			auctionInfo = "You have no upcoming auctions.";
//		} else {
//			curAuction = curCalendar.getAuction(curNPO);
//			curAuction.addItem("Car", "Dr Phil", "Good", "Medium", "No Notes", "Honda Civic", 1000);
//			curAuction.addItem("Calculator", "Dr Phill", "Good", "Medium", "No Notes", "TI NSpire CAS", 40);
//			curAuction.addItem("Football", "Sherman", "Good", "Medium", "No Notes", "Seahawks signed football", 40);
//			auctionInfo = "<html>Auction Overview:<br>" + "Auction Date: "
//					+ curAuction.getAuctionDate().format(dateFormat) + "<br>" + "You have "
//					+ curAuction.getMyItemList().size() + " items currently listed in this auction.</html>";
//		}
//	}
//
//	private void updateAuctionDetails() {
//		if (!curNPO.hasAuction()) {
//			auctionInfo = "You have no upcoming auctions.";
//		} else {
//			curAuction = curCalendar.getAuction(curNPO);
//			auctionInfo = "<html>Auction Overview:<br>" + "Auction Date: "
//					+ curAuction.getAuctionDate().format(dateFormat) + "<br>" + "You have "
//					+ curAuction.getMyItemList().size() + " items currently listed in this auction.</html>";
//		}
//	}
//
//	/**
//	 * Update all views with the most current information.
//	 */
//	private void updateStatus() {
//		updateAuctionDetails();
//		lblAuctionInfo.setText(auctionInfo);
//		if (!curNPO.hasAuction()) {
//			btnEditAuction.setEnabled(false);
//			btnRequest.setEnabled(true);
//		} else {
//			btnEditAuction.setEnabled(true);
//			btnRequest.setEnabled(false);
//		}
//=======

	/**
	 * Initialize the GUI components of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.BLUE);

		JLabel lblNewLabel = new JLabel("Welcome to the NPO Main Menu");
		lblNewLabel.setBounds(0, 29, 788, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(subMenuFont);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Logged in as: " + curNPO.getMyName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(detailsFont);
		lblNewLabel_1.setBounds(10, 52, 764, 24);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setFont(detailsFont);
		lblNewLabel_3.setBounds(102, 137, 85, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblAuctionCentral = new JLabel("Auction Central");
		lblAuctionCentral.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionCentral.setFont(mainFont);
		lblAuctionCentral.setBounds(0, 0, 788, 24);
		frame.getContentPane().add(lblAuctionCentral);

		lblAuctionInfo = new JLabel(auctionInfo);
		lblAuctionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionInfo.setBounds(0, 87, 784, 115);
		lblAuctionInfo.setFont(detailsFont);
		frame.getContentPane().add(lblAuctionInfo);

		JLabel lblNewLabel_2 = new JLabel("What would you like to do today?");
		lblNewLabel_2.setFont(detailsFont);
		lblNewLabel_2.setBounds(33, 297, 306, 34);
		frame.getContentPane().add(lblNewLabel_2);

		btnRequest = new JButton("Submit Auction Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestMenu = new AuctionRequestGUI(curNPO, curCalendar);
				requestMenu.setModal(true);
				requestMenu.setVisible(true);
				updateStatus();
			}
		});
		btnRequest.setToolTipText("Click here to submit an Auction Request");
		btnRequest.setBounds(81, 379, 154, 23);
		frame.getContentPane().add(btnRequest);
		if (curNPO.hasAuction())
			btnRequest.setEnabled(false);

		btnEditAuction = new JButton("Edit Current Auction");
		btnEditAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editMenu = new AuctionEditGUI(curNPO, curCalendar);
				editMenu.setModal(true);
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
				MainGUI.myFrame.setVisible(true);
				//System.exit(0);
			}
		});
		btnLogOut.setBounds(551, 379, 154, 23);
		frame.getContentPane().add(btnLogOut);
	}

	/**
	 * Loads the details of the upcoming auction (if any).
	 */
	private void loadAuctionOverview() {
		if (!curNPO.hasAuction()) {
			auctionInfo = "You have no upcoming auctions.";
		} else {
			curAuction = curCalendar.getAuction(curNPO);
			curAuction.addItem("Car", "Dr Phil", "Good", "Medium", "No Notes", "Honda Civic", 1000);
			curAuction.addItem("Calculator", "Dr Phill", "Good", "Medium", "No Notes", "TI NSpire CAS", 40);
			curAuction.addItem("Football", "Sherman", "Good", "Medium", "No Notes", "Seahawks signed football", 40);
			auctionInfo = "<html>Auction Overview:<br>" + "Auction Date: "
					+ curAuction.getAuctionDate().format(dateFormat) + "<br>" + "You have "
					+ curAuction.getMyItemList().size() + " items currently listed in this auction.</html>";
		}
	}

	private void updateAuctionDetails() {
		if (!curNPO.hasAuction()) {
			auctionInfo = "You have no upcoming auctions.";
		} else {
			curAuction = curCalendar.getAuction(curNPO);
			auctionInfo = "<html>Auction Overview:<br>" + "Auction Date: "
					+ curAuction.getAuctionDate().format(dateFormat) + "<br>" + "You have "
					+ curAuction.getMyItemList().size() + " items currently listed in this auction.</html>";
		}
	}

	/**
	 * Update all views with the most current information.
	 */
	private void updateStatus() {
		updateAuctionDetails();
		lblAuctionInfo.setText(auctionInfo);
		if (!curNPO.hasAuction()) {
			btnEditAuction.setEnabled(false);
			btnRequest.setEnabled(true);
		} else {
			btnEditAuction.setEnabled(true);
			btnRequest.setEnabled(false);
		}
	}
	public JFrame getFrame() {
		return frame;
//>>>>>>> refs/heads/JesseBranch
	}

}
