package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Auction;
import model.AuctionCalendar;
import model.NPO;

public class AuctionDetailsGUI extends JDialog {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 400;
	
	private JTabbedPane tabbedPane;
	private JPanel npoPanel1;
	private JPanel npoPanel2;
	private static AuctionCalendar myAucCal;
	private static Auction myAuction1;
	private static Auction myAuction2;
	static NPO myNPO1;
	static NPO myNPO2;
	private Dimension minimumSize = new Dimension(WIDTH, HEIGHT);
	private JLabel npoName;
	private JLabel auctionID;
	private JLabel auctionDate;
	private JLabel expectedItems;
	private JLabel notes;
	private JLabel itemList;

	public AuctionDetailsGUI(NPO theNPO1, NPO theNPO2, Auction theAuction1, Auction theAuction2, AuctionCalendar theAuctionCalendar) {
		super(new JFrame(), "Auction Details");
		this.setMinimumSize(minimumSize);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		myNPO1 = theNPO1;
		myNPO2 = theNPO2;
		myAuction1 = theAuction1;
		myAuction2 = theAuction2;
		myAucCal = theAuctionCalendar;
		
		initComponents();
		
		pack();
		setVisible(true);
		
	}

	private void initComponents() {
		tabbedPane = new JTabbedPane();
		String npoName1 = myNPO1.getMyUserName();
		String npoName2 = myNPO2.getMyUserName();
		System.out.println(npoName1 + " " + npoName2);
		tabbedPane.addTab(npoName1, null, npoPanel1);
		if (npoName2 != null) {
			tabbedPane.addTab(npoName2, null, npoPanel2);
		}
		
		
		setContentPane(tabbedPane);
		
		
		
		
		
	}
	public static void main(String[] args) {
		AuctionDetailsGUI test = new AuctionDetailsGUI(myNPO1, myNPO2, myAuction1, myAuction2, myAucCal);
		//AuctionDetailsGUI test = new AuctionDetailsGUI();
	}

}
