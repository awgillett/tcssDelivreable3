package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import model.*;
import model.Calendar;


public class HomeGUI implements ActionListener{
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static Font details = new Font("Tahoma", Font.PLAIN, 15);
	private static Font welcome = new Font("Tahoma", Font.BOLD, 22);
	private static Font subWelcome = new Font("Tahoma", Font.BOLD, 20);
	private int baseX = 200;
	private int baseY = 0;
	NPO startNPO;
	Bidder startBidder;
	Staff startStaff;
	
	NPOGUI startNPOGUI;

	
	private JTextField userText;
	protected static Calendar myCalendar = new Calendar();
	protected JFrame myFrame;
	private ArrayList<User> userList = new ArrayList();
	User curUser;
	JLabel message = new JLabel();


	public HomeGUI(ArrayList<User> theUserList, JFrame theFrame){
		userList = theUserList;
		myFrame = theFrame;
		curUser = null;
	}

	protected void startGUI() {

		myFrame.getContentPane().setLayout(null);

		addTextBox();
		addTextBoxLabel();
		addTitle();
		addLoginButton();
		addRegisterButton();
	}
	
	private void addTextBoxLabel(){
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(baseX + 20, baseY + 85, 80, 25);
		userLabel.setFont(details);
		myFrame.getContentPane().add(userLabel);
	}
	
	private void addTextBox(){
		userText = new JTextField(20);
		userText.setBounds(baseX + 110, baseY + 85, 160, 25);
		
		myFrame.getContentPane().add(userText);
		userText.addActionListener(this);
	}
	
	private void addTitle(){
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(welcome);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(baseX + 20, baseY + 10, 400, 20);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblYouAreSignedAs = new JLabel("Please sign in or register:");
		lblYouAreSignedAs.setFont(details);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreSignedAs.setBounds(baseX + 20, baseY + 55, 300, 30);
		myFrame.getContentPane().add(lblYouAreSignedAs);
	}
	
	private void addLoginButton(){
		JButton loginButton = new JButton("login");
		loginButton.setBounds(baseX + 20, baseY + 120, 154, 23);
		loginButton.addActionListener(this);
		loginButton.setToolTipText("Enter username above and click here to login.");
		myFrame.getContentPane().add(loginButton);
		
	}
	
	private void addRegisterButton(){
		JButton registerButton = new JButton("register");
		registerButton.setBounds(baseX + 200, baseY + 120, 154, 23);
		myFrame.getContentPane().add(registerButton);
		registerButton.setToolTipText("Click here to begin registration.");
		registerButton.addActionListener(new ActionListener() {
			
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	MainGUI.myFrame.getContentPane().removeAll();
            	RegisterGUI RegisterPanel = new RegisterGUI(myFrame, userList);
            	RegisterPanel.runTest();
            }
        });
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(User user : userList){
			if(userText.getText().equals(user.getMyUserName())){
				curUser = user;
				break;
			}
		}
		
		if (curUser != null){
			System.out.println(curUser.getMyUserName() + " is logged in as " + curUser.getUserType());
			if(curUser.getUserType().equals("NPO")){
				//go to NPOGUI
				startNPO = new NPO(curUser.getMyUserName(),curUser.getMyName());
				startNPOGUI = new NPOGUI(startNPO, myCalendar); 
				startNPOGUI.getFrame().setVisible(true);

			}else if(curUser.getUserType().equals("Bidder")){
				//go to BidderGUI
				System.out.println(curUser.getMyUserName() + " is logged in as " + curUser.getUserType());
				//startBidder = new Bidder(curUser.getMyUserName(),curUser.getMyName());
				//Bidder BidderGUI = new Bidder(aBidder, myCalendar); 
				//BidderGUI.setVisible(true);
			}else{
				//go to StaffGUI
				startStaff = new Staff(curUser.getMyUserName(),curUser.getMyName());
				//StaffGUI startStaff = new NPOGUI(startStaff, myCalendar);
				//startNPOGUI.setModal(true);
				//startStaff.setVisible(true);
			}
			
			
		} else{
			JOptionPane.showMessageDialog(null, "username doesn't exist, please try again");
			System.out.println("username doesn't exist, please try again");
			message.setText("Please try again");
			userText.setText("");
		}
		
	}

}