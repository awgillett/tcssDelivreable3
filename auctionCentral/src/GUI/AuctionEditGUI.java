package GUI;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import model.Auction;
import model.AuctionCalendar;
import model.Item;
import model.NPO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AuctionEditGUI extends JDialog {

	private AuctionCalendar myCalendar;
	Auction myAuction;
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);
	private String auctionInfo;
	private String selectedItemInfo;
	private AddItemGUI addItemMenu;
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM" + " d yyyy, hh:mm a");
	private int selectedItem = -1;
	private JTable tblItems;
	private JLabel lblInfo;
	private DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {},
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
	 * Initialize the GUI
	 */
	// public AuctionEditGUI(Auction theAuction) {
	public AuctionEditGUI(NPO theNPO, AuctionCalendar theCalendar) {
		myCalendar = theCalendar;
		myAuction = theCalendar.getAuction(theNPO);
		selectedItemInfo = "";
		populateAuctionInfo();
		initialize();
	}

	/**
	 * Initialize the GUI components of the frame.
	 */
	private void initialize() {
		// frame = new JFrame();
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(mainFont);
		label.setBounds(0, 0, 788, 24);
		this.getContentPane().add(label);

		JLabel lblWelcome = new JLabel("Welcome to the NPO Auction Edit Menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(subMenuFont);
		lblWelcome.setBounds(0, 29, 788, 24);
		this.getContentPane().add(lblWelcome);

		JLabel lblNewLabel_1 = new JLabel("Logged in as: " + myAuction.getNPO().getMyName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(detailsFont);
		lblNewLabel_1.setBounds(10, 52, 764, 24);
		this.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(328, 95, 451, 282);
		getContentPane().add(scrollPane);

		tblItems = new JTable();
		scrollPane.setViewportView(tblItems);
		tblItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedItem = tblItems.getSelectedRow();
				if (e.getClickCount() >= 2) {
					displayItemInfo();
				}
			}
		});
		tblItems.setShowVerticalLines(false);
		tblItems.setShowHorizontalLines(false);
		tblItems.setShowGrid(false);
		tblItems.setModel(tblModel);
		tblItems.setBackground(SystemColor.control);
		tblItems.getColumnModel().getColumn(0).setPreferredWidth(21);
		tblItems.getColumnModel().getColumn(2).setPreferredWidth(160);
		tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblNewLabel = new JLabel("Auction Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 95, 308, 24);
		getContentPane().add(lblNewLabel);

		lblInfo = new JLabel(auctionInfo);
		lblInfo.setFont(detailsFont);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setBounds(10, 122, 308, 125);
		getContentPane().add(lblInfo);

		JButton btnAddItem = new JButton("Add New Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemMenu = new AddItemGUI(myAuction);
				addItemMenu.setModal(true);
				addItemMenu.setVisible(true);
				updateStatus();

			}
		});
		btnAddItem.setToolTipText("Click here to submit an Auction Request");
		btnAddItem.setBounds(31, 411, 154, 23);
		getContentPane().add(btnAddItem);

		JButton btnRemoveItem = new JButton("Remove Selected Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItem();
			}
		});
		btnRemoveItem.setToolTipText("Click here to remove the last item highlighted in the list.");
		btnRemoveItem.setBounds(216, 411, 168, 23);
		getContentPane().add(btnRemoveItem);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnBack.setBounds(600, 411, 154, 23);
		getContentPane().add(btnBack);

		JButton btnCancelAuction = new JButton("Cancel Auction");
		btnCancelAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAuction();
			}
		});
		btnCancelAuction.setToolTipText("Click here to Canel Auction Request");
		btnCancelAuction.setBounds(415, 411, 154, 23);
		getContentPane().add(btnCancelAuction);

		populateItems();
		disableListEdits();
	}

	/**
	 * Loads and displays the auction date and number of items.
	 */
	private void populateAuctionInfo() {
		auctionInfo = "<html>Scheduled:<br><br>" + myAuction.getAuctionDate().format(dateFormat) + "<br>"
				+ "<br><br>You have " + myAuction.getMyItemList().size() + " items currently listed.</html>";
	}

	/**
	 * Loads summary information about each item within the auction.
	 */
	private void populateItems() {
		tblModel.setRowCount(0);
		for (Item i : myAuction.getItemList()) {
			tblModel.addRow(new Object[] { i.getMyItemID(), i.getItemName(), i.getMyDescription(), i.getMyCondition(),
					currency.format(i.getMyMinBid()) });
		}
	}

	/**
	 * Hides the window from view
	 */
	private void close() {
		// this.setVisible(false);
		this.dispose();
	}

	/**
	 * Displays a confirmation message and then removes the item if confirmed.
	 * 
	 * @param theItem
	 *            the item requested to remove
	 */
	private void removeItem() {
		if (selectedItem != -1) {
			Item theItem = myAuction.getItem((int) tblItems.getValueAt(selectedItem, 0));
			String message = "<html>Are you sure you want to remove the following item?<br><br>Name: "
					+ theItem.getMyItemName() + "<br>Condition: " + theItem.getMyCondition() + "<br>Size: "
					+ theItem.getMySize() + "<br>Minimum Bid: " + currency.format(theItem.getMyMinBid()) + "</html>";

			if (JOptionPane.showConfirmDialog(this, message) == 0) {
				int result = myAuction.removeItem(theItem);
				if (result == 1)
					JOptionPane.showMessageDialog(this, "Item has been successfully removed from Inventory!");
				else if (result == 2)
					JOptionPane.showMessageDialog(this, "Sorry, this item was not found in inventory."); // This
																											// should
																											// never
																											// occur
																											// with
																											// the
																											// GUI
				else
					JOptionPane.showMessageDialog(this,
							"Items can no longer be removed from this auction since its scheduled to commence in less than two days.");
				selectedItem = -1;
				updateStatus();
			}
		} else
			JOptionPane.showMessageDialog(this, "Please select an item to remove");

	}

	/**
	 * Loads the most current information for display to the user.
	 */
	private void updateStatus() {
		populateAuctionInfo();
		populateItems();
		lblInfo.setText(auctionInfo);
	}

	/**
	 * Displays a confirmation message to the user and deletes the users auction
	 * if confirmed.
	 */
	private void cancelAuction() {
		String message = "<html>This auction is scheduled for: " + myAuction.getAuctionDate().format(dateFormat)
				+ "<br><br>You have " + myAuction.getMyItemList().size() + " items currently listed.<br><br>"
				+ "Are you sure you want to CANCEL THIS AUCTION? (This operation CANNOT be undone)</html>";

		if (JOptionPane.showConfirmDialog(this, message) == 0) {
			if (myCalendar.deleteAuction(myAuction.getNPO().getMyName()) == 1) {
				JOptionPane.showMessageDialog(this, "Your auction has been successfully cancelled!");
				close();
			}
		}
	}

	/**
	 * Provides a detailed view for one item selected within the list.
	 */
	private void displayItemInfo() {
		Item curItem = myAuction.getItem((int) tblItems.getValueAt(selectedItem, 0));
		selectedItemInfo = "<html>Detailed information for Item #" + curItem.getMyItemID() + "<br><br>Name: "
				+ curItem.getItemName() + "<br>Description: " + curItem.getMyDescription() + "<br>Condition: "
				+ curItem.getMyCondition() + "<br>Size: " + curItem.getMySize() + "<br>Donor: " + curItem.getMyDonor()
				+ "<br>Minimum Bid: " + currency.format(curItem.getMyMinBid()) + "<br>Notes: " + curItem.getMyNotes()
				+ "</html>";
		JOptionPane.showMessageDialog(this, selectedItemInfo);
	}
	
	private void disableListEdits() {
		for (int c = 0; c < tblItems.getColumnCount(); c++)
		{
		    Class<?> col_class = tblItems.getColumnClass(c);
		    tblItems.setDefaultEditor(col_class, null);        // remove editor
		}
	}
}
