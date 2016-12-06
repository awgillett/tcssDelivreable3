package GUI;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Staff;
import model.User;
import model.Auction;
import model.AuctionCalendar;
import model.NPO;

public class StaffGUI extends JDialog{

	// Class constants
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int ADDITIONAL_MONTHS_VIEWABLE = 3;

	// Class fields
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);
	private JPanel myContentPane;
	private JPanel calendarDates;
	private Dimension minimumSize = new Dimension(WIDTH, HEIGHT);
	private Calendar curCal = new GregorianCalendar();
	private JLabel mnthYr;
	static Staff curStaff;
	static AuctionCalendar theAucCal;
	private String labelNPO1 = null;
	private String labelNPO2 = null;
	private String buttonText;
	NPO testNPO = new NPO("A+ Charity", "Jose");
	NPO testNPO1 = new NPO("Charity House", "Fillipa");
	NPO testNPO3 = new NPO("OK Charity", "Tran");
	Auction myAuc1;
	Auction myAuc2;

	private AuctionDetailsGUI details;

	LocalDateTime today = LocalDateTime.now();
	LocalDateTime tomorrow = today.plusDays(2);
	LocalDateTime yesterday = tomorrow.minusDays(2);
	LocalDateTime future1 = today.plusDays(10);
	LocalDateTime future2 = today.plusDays(20);
	LocalDateTime future3 = today.plusDays(30);
	LocalDateTime future4 = today.plusDays(40);

	Collection<Auction> myAuctionList;

	/**
	 * GUI constructor
	 */
	public StaffGUI(Staff theStaff, AuctionCalendar theAuctionCalendar) {
		//super("Auction Central");
		curStaff = theStaff;
		theAucCal = theAuctionCalendar;

		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setMinimumSize(minimumSize);

		//testData();
		myAuctionList = new ArrayList(theAucCal.getAllAuctions());
		

		System.out.println(yesterday);
		System.out.println(today);
		System.out.println(tomorrow);

		initComponents();

		pack();
		setVisible(true);
	}

	/**
	 * Initialize components within main content pane.
	 */
	private void initComponents() {
		// Set up content pane.
		myContentPane = new JPanel();
		setContentPane(myContentPane);
		myContentPane.setLayout(new BoxLayout(myContentPane, BoxLayout.Y_AXIS));

		createHeader();
		createCalendar();
		updateMonth();

		// Close the staff view.
		JButton logout = new JButton("Logout");
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainGUI.myFrame.setVisible(true);
				//System.exit(0);
			}
		});
		myContentPane.add(logout);
	}

	/**
	 * Displays current view and user logged in.
	 */
	private void createHeader() {
		// Create welcome header.
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		
		JLabel welcome = new JLabel("<html>" + "Staff View" + "<br>" + "Logged in as " + curStaff.getMyUserName()
				+ "<html>");
		welcome.setFont(mainFont);
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel totalScheduled = new JLabel("Total Auctions Scheduled: " + myAuctionList.size());
		//totalScheduled.setFont(subMenuFont);
		totalScheduled.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel possibleScheduled = new JLabel("Number of Auctions Allowed");
		totalScheduled.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		header.add(welcome);
		header.add(totalScheduled);
		header.add(possibleScheduled);
		myContentPane.add(header);
	}

	/**
	 * Creates complete calendar component.
	 */
	private void createCalendar() {
		// The main content panel for the calendar component.
		JPanel calendarTop = new JPanel();
		calendarTop.setLayout(new BoxLayout(calendarTop, BoxLayout.Y_AXIS));

		// The header panel containing next/prev month buttons.
		JPanel calendarHeader = new JPanel(new GridLayout(0, 3));

		// The days of the week
		JPanel calendarDays = new JPanel(new GridLayout(0, 7));

		// The actual dates in a grid.
		calendarDates = new JPanel(new GridLayout(0, 7));
		calendarDates.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2));

		// calendarHeader content.
		String month = curCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = curCal.get(Calendar.YEAR);

		// Label displaying month/year currently being viewed.
		mnthYr = new JLabel(" " + month + " " + year + " ");
		mnthYr.setVerticalAlignment(SwingConstants.CENTER);
		mnthYr.setHorizontalAlignment(SwingConstants.CENTER);

		int actualMonth = curCal.get(Calendar.MONTH);

		// Buttons for navigating calendar.
		JButton nextMonth = new JButton(">>>");
		nextMonth.setFont(subMenuFont);
		JButton prevMonth = new JButton("<<<");
		prevMonth.setFont(subMenuFont);

		// Updates calendar to previous month.
		prevMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMonth.setEnabled(true);
				curCal.add(Calendar.MONTH, -1);
				updateMonth();
				if (curCal.get(Calendar.MONTH) == actualMonth) {
					prevMonth.setEnabled(false);
				}
			}
		});

		// Updates calendar to next month.
		nextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevMonth.setEnabled(true);
				curCal.add(Calendar.MONTH, +1);
				updateMonth();
				if (curCal.get(Calendar.MONTH) == ((actualMonth + ADDITIONAL_MONTHS_VIEWABLE) % actualMonth) - 1) {
					nextMonth.setEnabled(false);
				}
			}
		});
		// nextMonth.setFont(subMenuFont);

		// Add to header panel.
		calendarHeader.add(prevMonth);
		calendarHeader.add(mnthYr);
		calendarHeader.add(nextMonth);

		// Days panel content.
		String[] myDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		for (int i = 0; i < 7; i++) {
			JLabel temp = new JLabel(myDays[i]);
			temp.setVerticalAlignment(SwingConstants.CENTER);
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			calendarDays.add(temp);
		}

		calendarTop.add(calendarHeader);
		calendarTop.add(calendarDays);
		calendarTop.add(calendarDates);
		myContentPane.add(calendarTop, BorderLayout.CENTER);
	}

	/**
	 * Displays dates for currently selected month.
	 */
	void updateMonth() {
		// Clear the slate for all new date buttons.
		calendarDates.removeAll();
		calendarDates.repaint();
		int tomorrowDate = curCal.get(Calendar.DAY_OF_MONTH) + 1; // Tomorrow
		System.out.println("Tomorrow = " + tomorrowDate);
		curCal.set(Calendar.DAY_OF_MONTH, 1);

		// calendarHeader content.
		String month = curCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = curCal.get(Calendar.YEAR);

		mnthYr.setText(" " + month + " " + year + " ");
		mnthYr.setFont(subMenuFont);

		int curDayValue = curCal.get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = curCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int tempCurDate = curCal.get(Calendar.DAY_OF_MONTH);

		
		// reduce date to # between 0-6
		if (tempCurDate > 6) {
			tempCurDate = tempCurDate / 6;
		}

		// find start position for first day of month.
		for (int j = 0; j < 6; j++) {
			if (tempCurDate / 1 != 1) {
				tempCurDate--;
				curDayValue--;
			}
		}

		// Total # of buttons needed to cover all dates in month and blank lead
		// in buttons.
		int numDatesNeeded = daysInMonth + curDayValue;

		// Buttons representing calendar dates.
		for (int j = 0; j < numDatesNeeded; j++) {
			JButton temp = new JButton();
			calendarDates.add(temp);
			temp.setVisible(false);

			if (j == curDayValue) {
				buttonText = Integer.toString(tempCurDate);
				temp.setBackground(Color.GREEN);
				// Iterate through auction list.
				for (Auction a : myAuctionList) {
					// Compare current month/day to scheduled auctions.
					if (a.getAuctionDate().getYear() == year
							&& a.getAuctionDate().getMonth().toString().equals(month.toUpperCase())
							&& a.getAuctionDate().getDayOfMonth() == tempCurDate) {
						// Date match found. Set labelNPO1 text.
						if (labelNPO1 == null) {
							labelNPO1 = a.getNPO().getMyUserName();
							myAuc1 = a;
							buttonText = "<html>" + Integer.toString(tempCurDate) + "<br>" + ": " + labelNPO1
									+ "<html>";
							temp.setBackground(Color.YELLOW);
							System.out.println(labelNPO1 + "***");
							// At least 1 auction scheduled. Button will open
							// JDialog.
							temp.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									System.out.println("Press");
									details = new AuctionDetailsGUI(myAuc1.getNPO(), null, myAuc1, null, theAucCal);
									// details.setModal(true);
								}
							});

							// 2nd date match. Set labelNPO2 text.
						} else if (labelNPO1 != null) {
							labelNPO2 = a.getNPO().getMyUserName();
							myAuc2 = a;
							buttonText = "<html>" + Integer.toString(tempCurDate) + "<br>" + ": " + labelNPO1 + "<br>"
									+ ":" + labelNPO2 + "<html>";
							temp.setBackground(Color.RED);
							System.out.println(labelNPO2 + "~~~");
							temp.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									System.out.println("Press");
									details = new AuctionDetailsGUI(myAuc1.getNPO(), myAuc2.getNPO(), myAuc1, myAuc2,
											theAucCal);
									// details.setVisible(true);
								}
							});
						} // end else if
					}
				} // end for

				temp.setText(buttonText);
				temp.setVisible(true);
				curDayValue++;
				tempCurDate++;
				labelNPO1 = null;
				labelNPO2 = null;
			} // end if
		}
	}

	private void testData() {
		theAucCal.addAuction(testNPO, yesterday, 25, " ");
		theAucCal.addAuction(testNPO, today, 25, " ");
		theAucCal.addAuction(testNPO, future2, 25, " ");
		theAucCal.addAuction(testNPO1, future2, 25, " ");
		theAucCal.addAuction(testNPO3, future1, 25, " ");
		theAucCal.addAuction(testNPO1, future4, 25, " ");
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StaffGUI window = new StaffGUI(new Staff("Dracula", "Vlad"), new AuctionCalendar());
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
