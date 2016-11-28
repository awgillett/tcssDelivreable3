package GUI;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Bidder;
import model.Calendar;
import model.User;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.SystemColor;

public class BidderGUI extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
	protected static JFrame myFrame = new JFrame();
	protected static Calendar myCalendar = new Calendar();
	static Bidder currentBidder;
	
	public BidderGUI() {
		myFrame.getContentPane().setLayout(null);
		
//		currentBidder.setMyName("Test Bidder");
		
		String loggedInAs = "You are signed in as: "/* + currentBidder.getMyName()*/;
		
		
		JLabel lblYouAreSigned = new JLabel(loggedInAs);
		lblYouAreSigned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouAreSigned.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreSigned.setBounds(22, 47, 398, 16);
		myFrame.getContentPane().add(lblYouAreSigned);
		
		JLabel lblWelcomeBanner = new JLabel("Welcome to Auction Central");
		lblWelcomeBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(12, 18, 286, 16);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
	}
	public static void main(String[] args) {
		BidderGUI GUI = new BidderGUI();
	}
	public void start(){
		myFrame.setName("Auction Central");
		myFrame.setSize(300, 150);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
