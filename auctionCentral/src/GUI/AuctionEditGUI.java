package GUI;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import model.Auction;
import model.Item;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuctionEditGUI extends JFrame {

	// private JFrame frame;
	Auction myAuction;
	String auctionInfo;
	AddItemGUI addItemMenu;
	NumberFormat currency = NumberFormat.getCurrencyInstance();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM" + " d yyyy, hh:mm a");
	private JTable tblItems;
	DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {},
			new String[] { "ID", "Name", "Description", "Condition", "Min Bid" });

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AuctionEditGUI window = new AuctionEditGUI();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public AuctionEditGUI(Auction theAuction) {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				populateAuctionInfo();
				populateItems();
			}
		});
		myAuction = theAuction;
		populateAuctionInfo();
		initialize();
		addItemMenu = new AddItemGUI(myAuction);
		addItemMenu.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frame = new JFrame();
		this.setBounds(100, 100, 805, 484);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label.setBounds(0, 0, 788, 24);
		this.getContentPane().add(label);

		JLabel lblWelcome = new JLabel("Welcome to the NPO Auction Edit Menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblWelcome.setBounds(0, 29, 788, 24);
		this.getContentPane().add(lblWelcome);

		JLabel label_1 = new JLabel("Logged in as: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(0, 64, 366, 24);
		this.getContentPane().add(label_1);

		JLabel lblUser = new JLabel(myAuction.getNPO().getMyName());
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(376, 64, 392, 24);
		this.getContentPane().add(lblUser);

		tblItems = new JTable();
		tblItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = tblItems.getSelectedRow();

				}
			}
		});
		tblItems.setShowVerticalLines(false);
		tblItems.setShowHorizontalLines(false);
		tblItems.setShowGrid(false);
		tblItems.setModel(tblModel);
		tblItems.getColumnModel().getColumn(0).setPreferredWidth(21);
		tblItems.getColumnModel().getColumn(2).setPreferredWidth(160);
		tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblItems.setBounds(328, 95, 451, 282);
		getContentPane().add(tblItems);

		JLabel lblNewLabel = new JLabel("Auction Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 95, 308, 24);
		getContentPane().add(lblNewLabel);

		JLabel lblInfo = new JLabel(auctionInfo);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setBounds(10, 122, 308, 125);
		getContentPane().add(lblInfo);

		JButton btnAddItem = new JButton("Add New Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemMenu.setVisible(true);

			}
		});
		btnAddItem.setToolTipText("Click here to submit an Auction Request");
		btnAddItem.setBounds(103, 411, 154, 23);
		getContentPane().add(btnAddItem);

		JButton btnRemoveItem = new JButton("Remove Selected Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveItem.setToolTipText("Click here to remove the item highlighted in the list.");
		btnRemoveItem.setBounds(338, 411, 154, 23);
		getContentPane().add(btnRemoveItem);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnBack.setBounds(573, 411, 154, 23);
		getContentPane().add(btnBack);
		populateItems();
	}

	private void populateAuctionInfo() {
		auctionInfo = "<html>Scheduled:  " + myAuction.getAuctionDate().format(dateFormat) + "<br>"
				+ "<br><br>You have " + myAuction.getMyItemList().size() + " items currently listed.</html>";
	}

	private void populateItems() {
		for (Item i : myAuction.getItemList()) {
			tblModel.addRow(new Object[] { i.getMyItemID(), i.getItemName(), i.getMyDescription(), i.getMyCondition(),
					currency.format(i.getMyMinBid()) });
		}
	}

	private void close() {
		this.setVisible(false);
	}

	private void removeItem(Item theItem) {
		String message = "<html>Are you sure you want to remove the following item?<br><br>Name: "
				+ theItem.getMyItemName() + "<br>Condition: " + theItem.getMyCondition() + "<br>Size: "
				+ theItem.getMySize() + "<br>Minimum Bid: " + currency.format(theItem.getMyMinBid()) + "</html>";

		// JOptionPane confirm = new JOptionPane();
		// JLabel info = new JLabel(message);
		// confirm.getContentPane().add(info);
		if (JOptionPane.showConfirmDialog(this, message) == 0) {
			myAuction.
		}

	}
}
