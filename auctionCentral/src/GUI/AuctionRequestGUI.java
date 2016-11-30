package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Calendar;
import model.NPO;

import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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

public class AuctionRequestGUI extends JDialog {

	//private final JPanel contentPanel = new JPanel();
	private Calendar myCalendar;
	private NPO myNPO;
	private JLabel lblDateError;
	private JComboBox cboYear;
	private JComboBox cboMonth;
	private JComboBox cboDay;
	private LocalDateTime now;
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AuctionRequestGUI dialog = new AuctionRequestGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AuctionRequestGUI(/*NPO theNPO, Calendar theCalendar*/) {
		//myCalendar = theCalendar;
		//myNPO = theNPO;
		initializeGUI();
		populateYear();
		populateMonth();
		populateDays();
	}
	
	private void initializeGUI() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 500);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("");//"Logged in as: " + myNPO.getMyName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(detailsFont);
		lblNewLabel_1.setBounds(10, 52, 764, 24);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a Date for your Auction");
		lblPleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectA.setBounds(0, 110, 784, 24);
		getContentPane().add(lblPleaseSelectA);
		lblPleaseSelectA.setFont(detailsFont);
		
		lblDateError = new JLabel("");
		lblDateError.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateError.setBounds(0, 170, 784, 24);
		lblDateError.setFont(detailsFont);
		lblDateError.setForeground(Color.RED);
		getContentPane().add(lblDateError);
		
		JButton btnSubmit = new JButton("Submit Auction Request");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isValidDate();
			}
		});
		btnSubmit.setBounds(66, 408, 292, 23);
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(424, 408, 292, 23);
		getContentPane().add(btnCancel);
		
		cboYear = new JComboBox();
		cboYear.setBounds(199, 139, 112, 20);
		getContentPane().add(cboYear);
		
		cboMonth = new JComboBox();
		cboMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				populateDays();
			}
		});
		cboMonth.setBounds(321, 139, 112, 20);
		getContentPane().add(cboMonth);
		
		cboDay = new JComboBox();
		cboDay.setBounds(443, 139, 112, 20);
		getContentPane().add(cboDay);
	}
	
	private void isValidDate() {
		
	}
	
	private void populateYear() {
		now = LocalDateTime.now();
		LocalDate endOfYear = LocalDate.of(now.getYear(), 12, 31);
		cboYear.addItem(now.getYear());
		Period days = Period.between(now.toLocalDate(), endOfYear);
		int numOfDays = days.getDays() + days.getMonths() * now.getMonth().maxLength();
		if (numOfDays < now.getMonth().maxLength())
			cboYear.addItem(now.plusYears(1).getYear());
	}
	
	private void populateMonth() {
		now = LocalDateTime.now();
		LocalDateTime oneMonthFromNow = LocalDateTime.now().plusDays(now.getMonth().maxLength());
		cboMonth.addItem(now.getMonth());
		if (now.getMonth() != oneMonthFromNow.getMonth())
			cboMonth.addItem(oneMonthFromNow.getMonth());
	}
	
	private void populateDays() {
		cboDay.removeAllItems();
		now = LocalDateTime.now();
		if (cboMonth.getSelectedIndex() == 0)
			for (int i = now.getDayOfMonth()+7; i <= now.getMonth().maxLength(); i++)
				cboDay.addItem(i);
		else if (cboMonth.getSelectedIndex() == 1)
			for (int i = now.plusDays(7).getDayOfMonth(); i <= now.plusDays(now.getMonth().maxLength()).getDayOfMonth(); i++)
				cboDay.addItem(i);
	}
}
