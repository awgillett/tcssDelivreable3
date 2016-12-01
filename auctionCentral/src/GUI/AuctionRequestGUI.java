package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Calendar;
import model.NPO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AuctionRequestGUI extends JDialog {

	// private final JPanel contentPanel = new JPanel();
	private Calendar myCalendar;
	private NPO myNPO;
	//JLabel lblDate;
	private JTextArea txtNotes;
	private JTextArea txtInfo;
	private JComboBox cboYear;
	private JComboBox cboMonth;
	private JComboBox cboDay;
	private JComboBox cboTime;
	private JSpinner spnItems;
	JButton btnSubmit;
	private LocalDateTime now, auctionDate;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM" + " d yyyy, hh:mm a");
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);
	private LocalDateTime date;
	private int[][] times = { { 6, 0 }, { 6, 30 }, { 7, 0 }, { 7, 30 }, { 8, 0 }, { 8, 30 }, { 9, 0 }, { 9, 30 },
			{ 10, 0 }, { 10, 30 }, { 11, 0 }, { 11, 30 }, { 12, 0 }, { 12, 30 }, { 13, 0 }, { 13, 30 }, { 14, 0 },
			{ 14, 30 }, { 15, 0 }, { 15, 30 }, { 16, 0 }, { 16, 30 }, { 17, 0 }, { 17, 30 }, { 18, 0 }, { 18, 30 },
			{ 19, 0 }, { 19, 30 }, { 20, 0 }, { 20, 30 } };

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AuctionRequestGUI dialog = new AuctionRequestGUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AuctionRequestGUI(NPO theNPO, Calendar theCalendar) {
		myCalendar = theCalendar;
		myNPO = theNPO;
		initializeGUI();
		populateYear();
		populateMonth();
		populateDays();

	}

	private void initializeGUI() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		auctionDate = LocalDateTime.now();
		now = auctionDate;
		
		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(mainFont);
		label.setBounds(0, 0, 784, 24);
		getContentPane().add(label);

		JLabel lblWelcome = new JLabel("Welcome to the Auction Request Menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(subMenuFont);
		lblWelcome.setBounds(0, 29, 784, 24);
		getContentPane().add(lblWelcome);

		JLabel lblNewLabel_1 = new JLabel("Logged in as: " + myNPO.getMyName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(detailsFont);
		lblNewLabel_1.setBounds(10, 52, 764, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblPleaseSelectA = new JLabel("Please select a Date and time for your Auction (only the current valid range will be listed)");
		lblPleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectA.setBounds(0, 110, 784, 24);
		getContentPane().add(lblPleaseSelectA);
		lblPleaseSelectA.setFont(detailsFont);

		btnSubmit = new JButton("Submit Auction Request");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAuctionRequest();
			}
		});
		btnSubmit.setBounds(66, 408, 292, 23);
		getContentPane().add(btnSubmit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnCancel.setBounds(424, 408, 292, 23);
		getContentPane().add(btnCancel);

		cboYear = new JComboBox();
		cboYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadDate();
				updateAuctionDetails();
				validateAuction();
			}
		});
		cboYear.setBounds(150, 139, 112, 20);
		getContentPane().add(cboYear);

		cboMonth = new JComboBox();
		cboMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				populateDays();
				loadDate();
				updateAuctionDetails();
				validateAuction();
			}
		});
		cboMonth.setBounds(272, 139, 112, 20);
		getContentPane().add(cboMonth);

		cboDay = new JComboBox();
		cboDay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadDate();
				updateAuctionDetails();
				validateAuction();
			}
		});
		cboDay.setBounds(394, 139, 112, 20);
		getContentPane().add(cboDay);

		cboTime = new JComboBox();
		cboTime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadDate();
				updateAuctionDetails();
				validateAuction();
			}
		});
		cboTime.setModel(new DefaultComboBoxModel(new String[] { "6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM",
				"8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM",
				"1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM",
				"5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", "8:30 PM" }));
		cboTime.setBounds(516, 139, 112, 20);
		getContentPane().add(cboTime);
		
		JLabel lblNumberOfItems = new JLabel("Number of Items: ");
		lblNumberOfItems.setBounds(66, 194, 126, 24);
		lblNumberOfItems.setFont(detailsFont);
		getContentPane().add(lblNumberOfItems);
		
		JLabel lblNewLabel = new JLabel("Comments:");
		lblNewLabel.setBounds(109, 229, 83, 24);
		lblNewLabel.setFont(detailsFont);
		getContentPane().add(lblNewLabel);
		
		txtNotes = new JTextArea();
		txtNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateAuctionDetails();
			}
		});
		txtNotes.setText("");
		txtNotes.setBounds(202, 229, 150, 168);
		getContentPane().add(txtNotes);
		
		txtInfo = new JTextArea();
		txtInfo.setText("");
		txtInfo.setBounds(424, 229, 292, 168);
		getContentPane().add(txtInfo);
		
		JLabel lblAuctionDetails = new JLabel("Auction Details");
		lblAuctionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionDetails.setBounds(424, 194, 292, 24);
		lblAuctionDetails.setFont(detailsFont);
		getContentPane().add(lblAuctionDetails);
		
		spnItems = new JSpinner();
		spnItems.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateAuctionDetails();
			}
		});
		spnItems.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		spnItems.setBounds(202, 196, 150, 20);
		getContentPane().add(spnItems);
	}

	private void loadDate() {
		try {
		int year = (int) cboYear.getSelectedItem();
		Month month = (Month) cboMonth.getSelectedItem();
		int day = (int) cboDay.getSelectedItem();
		int time = cboTime.getSelectedIndex();
		auctionDate = LocalDateTime.of(year, month.getValue(), day, times[time][0], times[time][1]);
		//lblDate.setText(auctionDate.format(dateFormat));
		} catch (Exception e) {
			//lblDate.setText("Your auction date will show here");
		}
	}
	
	private void updateAuctionDetails() {
		String auctionDetails = "Auction date requested:\n" + auctionDate.format(dateFormat)
							  + "\n\nNumber of expected items:\n" + spnItems.getValue().toString()
							  + "\n\nComments:\n" + txtNotes.getText();
		txtInfo.setText(auctionDetails);
	}
	
	private void validateAuction() {
		if (auctionDate != now)
			btnSubmit.setEnabled(true);
		else
			btnSubmit.setEnabled(false);
	}

	private void populateYear() {;
		LocalDate endOfYear = LocalDate.of(now.getYear(), 12, 31);
		cboYear.addItem(now.getYear());
		Period days = Period.between(now.toLocalDate(), endOfYear);
		int numOfDays = days.getDays() + days.getMonths() * now.getMonth().maxLength();
		if (numOfDays < now.getMonth().maxLength())
			cboYear.addItem(now.plusYears(1).getYear());
	}

	private void populateMonth() {
		LocalDateTime oneMonthFromNow = LocalDateTime.now().plusDays(now.getMonth().maxLength());
		cboMonth.addItem(now.getMonth());
		if (now.getMonth() != oneMonthFromNow.getMonth())
			cboMonth.addItem(oneMonthFromNow.getMonth());
	}

	private void populateDays() {
		cboDay.removeAllItems();
		if (cboMonth.getSelectedIndex() == 0)
			for (int i = now.getDayOfMonth() + 7; i <= now.getMonth().maxLength(); i++)
				cboDay.addItem(i);
		else if (cboMonth.getSelectedIndex() == 1)
			for (int i = now.plusDays(7).getDayOfMonth(); i <= now.plusDays(now.getMonth().maxLength())
					.getDayOfMonth(); i++)
				cboDay.addItem(i);
	}
	
	private void submitAuctionRequest() {
		int items = (int) spnItems.getValue();
		
		String message = "<html>You are about to request a new Auction for:<br>" + auctionDate.format(dateFormat)
				+ "<br><br>Please confirm this request or cancel to submit with new information</html>";

		if (JOptionPane.showConfirmDialog(this, message) == 0) {
			boolean result = myCalendar.addAuction(myNPO, auctionDate, items, txtNotes.getText());
			if (result) {
				JOptionPane.showMessageDialog(this, "Your auction was accepted! You can add items immediately.");
				close();
			}
			else
				JOptionPane.showMessageDialog(this, "Sorry, the chosen day is unavailable, please choose a new date.");

		}
	}
	
	/**
	 * Hides the window from view
	 */
	private void close() {
		//this.setVisible(false);
		this.dispose();
	}
}
