package GUI;

//<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//=======
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.ButtonGroup;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//>>>>>>> refs/heads/JesseBranch
import javax.swing.border.EmptyBorder;

import model.*;

//<<<<<<< HEAD
//=======
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
//>>>>>>> refs/heads/JesseBranch

public class RegisterGUI implements ActionListener {
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static Font details = new Font("Tahoma", Font.PLAIN, 15);
	private static Font welcome = new Font("Tahoma", Font.BOLD, 22);
	private static Font subWelcome = new Font("Tahoma", Font.BOLD, 20);
	
//<<<<<<< HEAD
//	int WINDOWWIDTH = 800;
//	int WINDOWHEIGHT = 500;
//=======
	private int WINDOWWIDTH = 800;
	private int WINDOWHEIGHT = 500;
	private int baseX = 60;
	private int baseY = 50;
//>>>>>>> refs/heads/JesseBranch
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();


	private JPanel contentPane;
	private JTextField nameText;
	private JTextField usernameText;
	private JTextField phoneText;
	private JTextField addressText;
	private JTextField paymentText;
	private JTextField emailText;
	private JButton cancelButton, registerButton;
//<<<<<<< HEAD
//
	private String UserType;
//=======
//	private int UserType = 0;
//>>>>>>> refs/heads/JesseBranch
	
	protected JFrame myFrame;
	private ArrayList<User> userList = new ArrayList();



	/**
	 * Create the frame.
	 */
	public RegisterGUI(ArrayList<User> theUserList) {
		myFrame = new JFrame("Auction Central registration");;
		userList = theUserList;
		contentPane = new JPanel();
		contentPane.setLayout(null);;
		
//<<<<<<< HEAD
//=======
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		setBounds(100, 100, 450, 350);
////		contentPane = new JPanel();
////		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
////		setContentPane(contentPane);
////		contentPane.setLayout(null);
////		
////		
////		JLabel label = new JLabel("You are register as: ");
////		label.setFont( new Font("Tahoma", Font.PLAIN, 15));
////		label.setBounds(4,13, 165,20);
////		contentPane.add(label);
////		
////		JRadioButton staff = new JRadioButton("Staff");
////		staff.setBounds(144,0,70,50);
////		contentPane.add(staff);
////		
////		JRadioButton NPO = new JRadioButton("NPO");
////		NPO.setBounds(224, 0, 50, 50);
////		contentPane.add(NPO);
////
////		
////		JRadioButton bidder = new JRadioButton("Bidder");
////		bidder.setBounds(298, 0 , 100, 50);
////		contentPane.add(bidder);
////		
////		JLabel label2 = new JLabel("Please enter the following information:");
////		label2.setFont( new Font("Tahoma", Font.PLAIN, 15));
////		label2.setBounds(14,50, 264,20);
////		contentPane.add(label2);
////		
////		JLabel nameLabel = new JLabel("Name: ");
////		nameLabel.setFont( new Font("Tahoma", Font.PLAIN, 15));
////		nameLabel.setBounds(26, 94, 63, 14);
////		contentPane.add(nameLabel);
////		
////		JLabel usernameLabel = new JLabel("Username: ");
////		usernameLabel.setFont( new Font("Tahoma", Font.PLAIN, 15));
////		usernameLabel.setBounds(26, 124, 84, 14);
////		contentPane.add(usernameLabel);
////		
////		nameText = new JTextField();
////		nameText.setBounds(110, 93, 165, 20);
////		contentPane.add(nameText);
////		nameText.setColumns(10);
////		
////		usernameText = new JTextField();
////		usernameText.setColumns(10);
////		usernameText.setBounds(110, 123, 165, 20);
////		contentPane.add(usernameText);
//>>>>>>> refs/heads/JesseBranch

	}
	
	public void run(){
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setBounds(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		myFrame.setContentPane(contentPane);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
		contentPane.setLayout(null);
		
		
			
		addRegisterOptionButton();
		addRegisterButton();
		addCancelButton();
		myFrame.setVisible(true);
		
		
	}
	
	private void addRegisterOptionButton(){
		
//<<<<<<< HEAD
//		JLabel label = new JLabel("What would you like to register as: ");		
//		label.setFont(details);
//		label.setBounds(125,13, 265,20);
//=======
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(welcome);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(baseX + 160, baseY - 40, 400, 20);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel label = new JLabel("What would you like to register as: ");		
		label.setFont(details);
		label.setBounds(baseX + 125,baseY + 13, 265,20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(label);
		
		JButton Staff = new JButton("Staff");
		Staff.setFont(details);
//<<<<<<< HEAD
//		Staff.setBounds(80,50,154,23);
//		contentPane.add(Staff);
//		
//
//		Staff.addActionListener(this);
//		
//		JButton NPO = new JButton("NPO");
//		NPO.setFont(details);
//		NPO.setBounds(260, 50,154, 23);
//=======
		Staff.setBounds(baseX + 80,baseY + 50,154,23);
		Staff.setToolTipText("Click here to register as a Staff member.");
		contentPane.add(Staff);
		Staff.addActionListener(this);
		JButton NPO = new JButton("NPO");
		NPO.setFont(details);
		NPO.setBounds(baseX + 260, baseY + 50, 154, 23);
		NPO.setToolTipText("Click here to register as a Nonprofit Organization (NPO) contact person.");
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(NPO);
//<<<<<<< HEAD
//
//=======

//>>>>>>> refs/heads/JesseBranch
		NPO.addActionListener(this);

		JButton bidder = new JButton("Bidder");
		bidder.setFont(details);
//<<<<<<< HEAD
//		bidder.setBounds(440, 50 , 154, 23);
//=======
		bidder.setBounds(baseX + 440, baseY + 50 , 154, 23);
		bidder.setToolTipText("Click here to register as a Bidder.");
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(bidder);
		
//<<<<<<< HEAD
//=======

//>>>>>>> refs/heads/JesseBranch
		bidder.addActionListener(this);
		
	}
	
	private void addBidderRegisterPanel(){
		contentPane.removeAll();
		addStaffNPORegisterPanel();
//<<<<<<< HEAD
//		cancelButton.setBounds(305, 327, 154,23);
//		registerButton.setBounds(124, 327, 154,23);
//=======
		cancelButton.setBounds(baseX + 305, baseY + 327, 154,23);
		registerButton.setBounds(baseX + 124, baseY + 327, 154,23);
//>>>>>>> refs/heads/JesseBranch
		
		phoneText = new JTextField();
		phoneText.setColumns(10);
//<<<<<<< HEAD
//		phoneText.setBounds(210, 229, 165, 20);
//=======
		phoneText.setBounds(baseX + 210, baseY + 229, 165, 20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(phoneText);
		
//<<<<<<< HEAD
//=======

//>>>>>>> refs/heads/JesseBranch
		
		JLabel phoneLabel = new JLabel("Phone #: ");
		phoneLabel.setFont(details);
//<<<<<<< HEAD
//		phoneLabel.setBounds(125, 230, 84, 14);
//=======
		phoneLabel.setBounds(baseX + 125, baseY + 230, 84, 14);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(phoneLabel);
		
		JLabel addressLabel = new JLabel("Address: ");
		addressLabel.setFont(details);
//<<<<<<< HEAD
//		addressLabel.setBounds(125, 200, 63, 14);
//=======
		addressLabel.setBounds(baseX + 125, baseY + 200, 63, 14);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(addressLabel);
		
		addressText = new JTextField();
		addressText.setColumns(10);
//<<<<<<< HEAD
//		addressText.setBounds(210, 199, 165, 20);
//=======
		addressText.setBounds(baseX + 210, baseY + 199, 165, 20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(addressText);
		
		paymentText = new JTextField();
		paymentText.setColumns(10);
//<<<<<<< HEAD
//		paymentText.setBounds(210, 290, 165, 20);
//=======
		paymentText.setBounds(baseX + 210, baseY + 290, 165, 20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(paymentText);
		
		JLabel paymentLabel = new JLabel("Payment: ");
		paymentLabel.setFont(details);
//<<<<<<< HEAD
//		paymentLabel.setBounds(126, 289, 84, 19);
//=======
		paymentLabel.setBounds(baseX + 126, baseY + 289, 84, 19);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(paymentLabel);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(details);
//<<<<<<< HEAD
//		emailLabel.setBounds(125, 261, 63, 14);
//=======
		emailLabel.setBounds(baseX + 125, baseY + 261, 63, 14);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
//<<<<<<< HEAD
//		emailText.setBounds(210, 260, 165, 20);		
//=======
		emailText.setBounds(baseX + 210, baseY + 260, 165, 20);		
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(emailText);
//<<<<<<< HEAD
//=======
	
//>>>>>>> refs/heads/JesseBranch
	}
	
	private void addStaffNPORegisterPanel(){
		
		contentPane.removeAll();
//<<<<<<< HEAD
//		JLabel currentUser = new JLabel("You are registering as " + UserType);
//		currentUser.setFont(details);
//		currentUser.setBounds(360,13, 264,20);
//		contentPane.add(currentUser);
//
//		addRegisterOptionButton();
//=======
		if(UserType.equals("NPO")){
			JLabel currentUser = new JLabel("You are registering as NPO");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
		}else if(UserType.equals("Staff")){
			JLabel currentUser = new JLabel("You are registering as Staff");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
		}else{
			JLabel currentUser = new JLabel("You are registering as Bidder");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
		}
//		addRadioButton();
//>>>>>>> refs/heads/JesseBranch
		addRegisterButton();
		addCancelButton();
		JLabel label2 = new JLabel("Please enter the following information:");
		label2.setFont(details);
//<<<<<<< HEAD
//		label2.setBounds(125, 113, 300, 20);
//=======
		label2.setBounds(baseX + 125, baseY + 113, 300, 20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(label2);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(details);
//<<<<<<< HEAD
//		nameLabel.setBounds(125, 144, 63, 14);
//=======
		nameLabel.setBounds(baseX + 125, baseY + 144, 63, 14);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(nameLabel);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(details);
//<<<<<<< HEAD
//		usernameLabel.setBounds(125, 174, 84, 14);
//=======
		usernameLabel.setBounds(baseX + 125, baseY + 174, 84, 14);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(usernameLabel);
		
		nameText = new JTextField();
//<<<<<<< HEAD
//=======
		nameText.setBounds(baseX + 210, baseY + 143, 165, 20);
		contentPane.add(nameText);
//>>>>>>> refs/heads/JesseBranch
		nameText.setColumns(10);
		nameText.setBounds(210, 143, 165, 20);
		nameText.addFocusListener(new TextFieldActionListener());
		contentPane.add(nameText);
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
//<<<<<<< HEAD
//		usernameText.setBounds(210, 173, 165, 20);
//		usernameText.addFocusListener(new TextFieldActionListener());
//
//=======
		usernameText.setBounds(baseX + 210, baseY + 173, 165, 20);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(usernameText);

		
	}
	
	private void addRegisterButton(){
		registerButton = new JButton("Register");
//<<<<<<< HEAD
//		registerButton.setBounds(124, 215, 154,23);
//=======
		registerButton.setBounds(baseX + 124, baseY + 215, 154,23);
		contentPane.add(registerButton);
		registerButton.setToolTipText("Click here to register.");
//>>>>>>> refs/heads/JesseBranch
		registerButton.setEnabled(false);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// re-check unfilled text field for the user
				if(UserType.equals("NPO") || UserType.equals("Staff")){
					if (usernameText.getText().equals("") || nameText.getText().equals("")){
						JOptionPane.showMessageDialog(null, "ERROR: invalid field, please try again", "Error", 0);
					} else {
						checkAvailability(usernameText.getText());
					}
					
				} else {
					if (usernameText.getText().equals("") || nameText.getText().equals("")
							|| addressText.getText().equals("") || phoneText.getText().equals("")
							|| paymentText.getText().equals("") || emailText.getText().equals("")){
						JOptionPane.showMessageDialog(null, "ERROR: invalid field, please try again", "Error", 0);						
					} else {
						checkAvailability(usernameText.getText());
					}
				}
			}
			
		});
		contentPane.add(registerButton);
		
	}
	
	private void confirmationPanel(){
		int reply;
		if (UserType.equals("NPO") || UserType.equals("Staff")){
			
			reply = JOptionPane.showConfirmDialog(null, "Please confirm your informations: "
		    		+ "\n Your are registering as: " + UserType
		    		+ "\n Name: " + nameText.getText()
		    		+ "\n Username: " + usernameText.getText(), "Confirmation", JOptionPane.YES_NO_OPTION);
		} else {
			reply = JOptionPane.showConfirmDialog(null, "Please confirm your informations: "
		    		+ "\n Your are registering as: " + UserType
		    		+ "\n Name: " + nameText.getText()
		    		+ "\n Username: " + usernameText.getText()
		    		+ "\n Address: " + addressText.getText()
		    		+ "\n Phone #: " + phoneText.getText()
		    		+ "\n Email: " + emailText.getText()
		    		+ "\n Payment Info.: " + paymentText.getText(), "Confirmation", JOptionPane.YES_NO_OPTION);
		}
	    
        if (reply == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(myFrame, "You are done with your registration", "Congratulation", 1);
          addUser();
          myFrame.dispose();
        }
	}
	
	private void checkAvailability(String userName) {
		for (User u : userList) {
			if (u.getMyUserName().equals(userName)){
				JOptionPane.showMessageDialog(null, "ERROR: user name is not available, please choose another name", "Error", 0);
				return;
			}
		}
		confirmationPanel();
	}
	
	/**
	 * this method add staff or NPO to user list
	 */
	private void addUser(){
		if (UserType.equals("NPO")){
			userList.add(new NPO(usernameText.getText(), nameText.getText()));
		} else if (UserType.equals("Staff")){
			userList.add(new Staff(usernameText.getText(), nameText.getText()));
		} else {
			User bidder = new Bidder(usernameText.getText(), nameText.getText(), addressText.getText(),
					phoneText.getText(), emailText.getText(), paymentText.getText());
			userList.add(bidder);
		}
	}
	private void addCancelButton(){
		
		cancelButton = new JButton("Cancel");
//<<<<<<< HEAD
//		cancelButton.setBounds(305, 215, 154,23);
//		cancelButton.addActionListener(this);
//=======
		cancelButton.setBounds(baseX + 305, baseY + 215, 154,23);
        cancelButton.addActionListener(this);
//>>>>>>> refs/heads/JesseBranch
		contentPane.add(cancelButton);
		cancelButton.setToolTipText("Click here to return to main menu.");
//<<<<<<< HEAD
	}
	
	
	public class TextFieldActionListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent theEvent) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			if (usernameText.getText().length() != 0 || nameText.getText().length() != 0){
				registerButton.setEnabled(true);
				myFrame.repaint();
	
			}
		}
		
		
//=======
//		cancelButton.setToolTipText("Click here to return to main menu.");
//		cancelButton.addActionListener(this);
//>>>>>>> refs/heads/JesseBranch
	}

	@Override
	public void actionPerformed(ActionEvent theEvent) {
//<<<<<<< HEAD
		
		// because NPO and staff using 2 fields for register
		// i'm using same method for them.
//=======

//>>>>>>> refs/heads/JesseBranch
//		if(theEvent.getActionCommand().equals("NPO")
//				|| theEvent.getActionCommand().equals("Staff")){
//			
//<<<<<<< HEAD
//			UserType = theEvent.getActionCommand();
			
//=======
			if(theEvent.getActionCommand().equals("NPO")){
				UserType = "NPO";
			}else if(theEvent.getActionCommand().equals("Staff")){
				UserType = "Staff";
//			}
//			else{
//				UserType = 2;
//			}
			//myFrame.setBounds(0, 0, 700, 400);
//>>>>>>> refs/heads/JesseBranch
//	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
//	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
//			addStaffNPORegisterPanel();
//			
//			myFrame.repaint();
			
	}else if (theEvent.getActionCommand().equals("Bidder")){
//<<<<<<< HEAD
//			UserType = theEvent.getActionCommand();
//=======
			UserType = "Bidder";
//>>>>>>> refs/heads/JesseBranch
			addBidderRegisterPanel();
//<<<<<<< HEAD
//=======
			//myFrame.setBounds(0, 0, 700, 400);
//>>>>>>> refs/heads/JesseBranch
	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
			myFrame.repaint();
		}
//<<<<<<< HEAD
//		
//		if (theEvent.getActionCommand().equals("Cancel")){
//			myFrame.dispose();
//		}

//=======
		if (theEvent.getActionCommand().equals("Cancel")){
			myFrame.dispose();
			contentPane.removeAll();
			MainGUI Mainpanel = new MainGUI();
			Mainpanel.start();
		}
//>>>>>>> refs/heads/JesseBranch
	}
	
}
