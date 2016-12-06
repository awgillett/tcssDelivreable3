package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.*;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
/* The RegisterGUI allows the user to register as an NPO, Staff, or Bidder
 * @author Tran, Wiklanski
 * @version 12/5/2016
 */
public class RegisterGUI implements ActionListener {
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static Font details = new Font("Tahoma", Font.PLAIN, 15);
	private static Font welcome = new Font("Tahoma", Font.BOLD, 22);
	private static Font subWelcome = new Font("Tahoma", Font.BOLD, 20);
	private int WINDOWWIDTH = 800;
	private int WINDOWHEIGHT = 500;
	private int baseX = 60;
	private int baseY = 50;
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
	private String UserType;
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
	}
	
	public void run(){
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setBounds(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		myFrame.setContentPane(contentPane);
		myFrame.setLocation(100,100);
//        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
//                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
		contentPane.setLayout(null);
		
		addRegisterOptionButton();
		addRegisterButton();
		addCancelButton();
		myFrame.setVisible(true);
	}
	
	private void addRegisterOptionButton(){
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(welcome);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(baseX + 160, baseY - 40, 400, 20);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel label = new JLabel("What would you like to register as: ");		
		label.setFont(details);
		label.setBounds(baseX + 125,baseY + 13, 265,20);
		contentPane.add(label);
		
		JButton Staff = new JButton("Staff");
		Staff.setFont(details);
		Staff.setBounds(baseX + 80,baseY + 50,154,23);
		Staff.setToolTipText("Click here to register as a Staff member.");
		contentPane.add(Staff);
		Staff.addActionListener(this);
		
		JButton NPO = new JButton("NPO");
		NPO.setFont(details);
		NPO.setBounds(baseX + 260, baseY + 50, 154, 23);
		NPO.setToolTipText("Click here to register as a Nonprofit Organization (NPO) contact person.");
		contentPane.add(NPO);
		NPO.addActionListener(this);
		
		JButton bidder = new JButton("Bidder");
		bidder.setFont(details);
		bidder.setBounds(baseX + 440, baseY + 50 , 154, 23);
		bidder.setToolTipText("Click here to register as a Bidder.");
		contentPane.add(bidder);
		bidder.addActionListener(this);
	}
	
	private void addBidderRegisterPanel(){
		contentPane.removeAll();
		addStaffNPORegisterPanel();
		cancelButton.setBounds(baseX + 305, baseY + 327, 154,23);
		registerButton.setBounds(baseX + 124, baseY + 327, 154,23);
		
		phoneText = new JTextField();
		phoneText.setColumns(10);
		phoneText.setBounds(baseX + 210, baseY + 229, 165, 20);
		contentPane.add(phoneText);

		JLabel phoneLabel = new JLabel("Phone #: ");
		phoneLabel.setFont(details);
		phoneLabel.setBounds(baseX + 125, baseY + 230, 84, 14);
		contentPane.add(phoneLabel);
		
		JLabel addressLabel = new JLabel("Address: ");
		addressLabel.setFont(details);
		addressLabel.setBounds(baseX + 125, baseY + 200, 63, 14);
		contentPane.add(addressLabel);
		
		addressText = new JTextField();
		addressText.setColumns(10);
		addressText.setBounds(baseX + 210, baseY + 199, 165, 20);
		contentPane.add(addressText);
		
		paymentText = new JTextField();
		paymentText.setColumns(10);
		paymentText.setBounds(baseX + 210, baseY + 290, 165, 20);
		contentPane.add(paymentText);
		
		JLabel paymentLabel = new JLabel("Payment: ");
		paymentLabel.setFont(details);
		paymentLabel.setBounds(baseX + 126, baseY + 289, 84, 19);
		contentPane.add(paymentLabel);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(details);
		emailLabel.setBounds(baseX + 125, baseY + 261, 63, 14);
		contentPane.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(baseX + 210, baseY + 260, 165, 20);		
		contentPane.add(emailText);
	}
	
	private void addStaffNPORegisterPanel(){
		
		contentPane.removeAll();
		if(UserType.equals("NPO")){
			JLabel currentUser = new JLabel("You are registering as NPO");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
			addRegisterOptionButton();
		}else if(UserType.equals("Staff")){
			JLabel currentUser = new JLabel("You are registering as Staff");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
			addRegisterOptionButton();
		}else{
			JLabel currentUser = new JLabel("You are registering as Bidder");
			currentUser.setFont(details);
			currentUser.setBounds(baseX + 360,baseY + 13, 264,20);
			contentPane.add(currentUser);
			addRegisterOptionButton();
		}
		addRegisterButton();
		addCancelButton();
		
		JLabel label2 = new JLabel("Please enter the following information:");
		label2.setFont(details);
		label2.setBounds(baseX + 125, baseY + 113, 300, 20);
		contentPane.add(label2);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(details);
		nameLabel.setBounds(baseX + 125, baseY + 144, 63, 14);
		contentPane.add(nameLabel);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(details);
		usernameLabel.setBounds(baseX + 125, baseY + 174, 84, 14);
		contentPane.add(usernameLabel);
		
		nameText = new JTextField();
		nameText.setBounds(baseX + 210, baseY + 143, 165, 20);
		contentPane.add(nameText);
		nameText.setColumns(10);
		nameText.addFocusListener(new TextFieldActionListener());
		contentPane.add(nameText);
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBounds(baseX + 210, baseY + 173, 165, 20);
		contentPane.add(usernameText);
	}
	
	private void addRegisterButton(){
		registerButton = new JButton("Register");
		registerButton.setBounds(baseX + 124, baseY + 215, 154,23);
		registerButton.setToolTipText("Click here to register.");
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
          JOptionPane.showMessageDialog(myFrame, "You are now registered as " + UserType , "Congratulation", 1);
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
	 * This method adds staff or NPO to user list
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
		cancelButton.setBounds(baseX + 305, baseY + 215, 154,23);
        cancelButton.addActionListener(this);
		contentPane.add(cancelButton);
		cancelButton.setToolTipText("Click here to return to main menu.");
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
	}

	@Override
	public void actionPerformed(ActionEvent theEvent) {

		if(theEvent.getActionCommand().equals("NPO")
				|| theEvent.getActionCommand().equals("Staff")){
			UserType = theEvent.getActionCommand();
			myFrame.setLocation(100,100);
//	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
//	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
			addStaffNPORegisterPanel();
			myFrame.repaint();
			
	}else if (theEvent.getActionCommand().equals("Bidder")){

			UserType = theEvent.getActionCommand();

			addBidderRegisterPanel();
			myFrame.setLocation(100,100);
//	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
//	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
			myFrame.repaint();
		}
		if (theEvent.getActionCommand().equals("Cancel")){
			myFrame.dispose();
			contentPane.removeAll();
			MainGUI Mainpanel = new MainGUI();
			Mainpanel.start();
		}
	}
}
