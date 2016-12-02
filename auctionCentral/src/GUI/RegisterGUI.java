package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterGUI implements ActionListener {
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static Font details = new Font("Tahoma", Font.PLAIN, 15);
	private static Font welcome = new Font("Tahoma", Font.BOLD, 22);
	private static Font subWelcome = new Font("Tahoma", Font.BOLD, 20);
	
	int WINDOWWIDTH = 700;
	int WINDOWHEIGHT = 400;
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
	private int UserType = 0;
	
	protected JFrame myFrame;
	private ArrayList<User> userList = new ArrayList();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegisterGUI frame = new RegisterGUI();
//					//frame.runTest();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI(JFrame theFrame, ArrayList<User> theUserList) {
		myFrame = theFrame;
		userList = theUserList;
		contentPane = new JPanel();
		contentPane.setLayout(null);;
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 350);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		
//		JLabel label = new JLabel("You are register as: ");
//		label.setFont( new Font("Tahoma", Font.PLAIN, 15));
//		label.setBounds(4,13, 165,20);
//		contentPane.add(label);
//		
//		JRadioButton staff = new JRadioButton("Staff");
//		staff.setBounds(144,0,70,50);
//		contentPane.add(staff);
//		
//		JRadioButton NPO = new JRadioButton("NPO");
//		NPO.setBounds(224, 0, 50, 50);
//		contentPane.add(NPO);
//
//		
//		JRadioButton bidder = new JRadioButton("Bidder");
//		bidder.setBounds(298, 0 , 100, 50);
//		contentPane.add(bidder);
//		
//		JLabel label2 = new JLabel("Please enter the following information:");
//		label2.setFont( new Font("Tahoma", Font.PLAIN, 15));
//		label2.setBounds(14,50, 264,20);
//		contentPane.add(label2);
//		
//		JLabel nameLabel = new JLabel("Name: ");
//		nameLabel.setFont( new Font("Tahoma", Font.PLAIN, 15));
//		nameLabel.setBounds(26, 94, 63, 14);
//		contentPane.add(nameLabel);
//		
//		JLabel usernameLabel = new JLabel("Username: ");
//		usernameLabel.setFont( new Font("Tahoma", Font.PLAIN, 15));
//		usernameLabel.setBounds(26, 124, 84, 14);
//		contentPane.add(usernameLabel);
//		
//		nameText = new JTextField();
//		nameText.setBounds(110, 93, 165, 20);
//		contentPane.add(nameText);
//		nameText.setColumns(10);
//		
//		usernameText = new JTextField();
//		usernameText.setColumns(10);
//		usernameText.setBounds(110, 123, 165, 20);
//		contentPane.add(usernameText);

//		JButton registerButton = new JButton("Register");
//		registerButton.setBounds(224, 277, 89, 23);
//		contentPane.add(registerButton);
//		registerButton.setEnabled(false);
//		
//		JButton cancelButton = new JButton("Cancel");
//		cancelButton.setBounds(335, 277, 89, 23);
//		contentPane.add(cancelButton);
		
//		phoneText = new JTextField();
//		phoneText.setColumns(10);
//		phoneText.setBounds(110, 179, 165, 20);
//		contentPane.add(phoneText);
//		
//		JLabel phoneLabel = new JLabel("Phone #: ");
//		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		phoneLabel.setBounds(25, 180, 84, 14);
//		contentPane.add(phoneLabel);
//		
//		JLabel addressLabel = new JLabel("Address: ");
//		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		addressLabel.setBounds(25, 150, 63, 14);
//		contentPane.add(addressLabel);
//		
//		addressText = new JTextField();
//		addressText.setColumns(10);
//		addressText.setBounds(110, 149, 165, 20);
//		contentPane.add(addressText);
//		
//		paymentText = new JTextField();
//		paymentText.setColumns(10);
//		paymentText.setBounds(110, 240, 165, 20);
//		contentPane.add(paymentText);
//		
//		JLabel paymentLabel = new JLabel("Payment: ");
//		paymentLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		paymentLabel.setBounds(26, 239, 84, 19);
//		contentPane.add(paymentLabel);
//		
//		JLabel emailLabel = new JLabel("Email: ");
//		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		emailLabel.setBounds(25, 211, 63, 14);
//		contentPane.add(emailLabel);
//		
//		emailText = new JTextField();
//		emailText.setColumns(10);
//		emailText.setBounds(110, 210, 165, 20);
//		contentPane.add(emailText);
	}
	
	public void runTest(){
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setBounds(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		myFrame.setContentPane(contentPane);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
		contentPane.setLayout(null);
		
			
		addRadioButton();
		addRegisterButton();
		addCancelButton();
		
	}
	
	private void addRadioButton(){
		
		JLabel label = new JLabel("What would you like to register as: ");		
		label.setFont(details);
		label.setBounds(125,13, 265,20);
		contentPane.add(label);
		
		JButton Staff = new JButton("Staff");
		Staff.setFont(details);
		Staff.setBounds(80,50,154,23);
		contentPane.add(Staff);
		
//		ButtonGroup group = new ButtonGroup();
//		JRadioButton staff = new JRadioButton("Staff");
//		staff.setBounds(244,0,70,50);
//		contentPane.add(staff);
		Staff.addActionListener(this);
		
		JButton NPO = new JButton("NPO");
		NPO.setFont(details);
		NPO.setBounds(260, 50,154, 23);
		contentPane.add(NPO);
		//btnOK.setActionCommand("enable");
		//btnOK.setEnabled(false);
			
//		JRadioButton NPO = new JRadioButton("NPO");
//		NPO.setBounds(324, 0, 50, 50);
//		contentPane.add(NPO);
		NPO.addActionListener(this);

		JButton bidder = new JButton("Bidder");
		bidder.setFont(details);
		bidder.setBounds(440, 50 , 154, 23);
		contentPane.add(bidder);
		
//		JRadioButton bidder = new JRadioButton("Bidder");
//		bidder.setBounds(398, 0 , 100, 50);
//		contentPane.add(bidder);
		
//		group.add(staff);
//		group.add(NPO);
//		group.add(bidder);
		bidder.addActionListener(this);
		
	}
	
	private void addBidderRegisterPanel(){
		contentPane.removeAll();
		addStaffNPORegisterPanel();
		cancelButton.setBounds(305, 327, 154,23);
		registerButton.setBounds(124, 327, 154,23);
		
		phoneText = new JTextField();
		phoneText.setColumns(10);
		phoneText.setBounds(210, 229, 165, 20);
		contentPane.add(phoneText);
		
//		JLabel currentUser = new JLabel("You are registering to be a Bidder");
//		currentUser.setFont(details);
//		currentUser.setBounds(240,13, 264,20);
//		contentPane.add(currentUser);
		
		JLabel phoneLabel = new JLabel("Phone #: ");
		phoneLabel.setFont(details);
		phoneLabel.setBounds(125, 230, 84, 14);
		contentPane.add(phoneLabel);
		
		JLabel addressLabel = new JLabel("Address: ");
		addressLabel.setFont(details);
		addressLabel.setBounds(125, 200, 63, 14);
		contentPane.add(addressLabel);
		
		addressText = new JTextField();
		addressText.setColumns(10);
		addressText.setBounds(210, 199, 165, 20);
		contentPane.add(addressText);
		
		paymentText = new JTextField();
		paymentText.setColumns(10);
		paymentText.setBounds(210, 290, 165, 20);
		contentPane.add(paymentText);
		
		JLabel paymentLabel = new JLabel("Payment: ");
		paymentLabel.setFont(details);
		paymentLabel.setBounds(126, 289, 84, 19);
		contentPane.add(paymentLabel);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(details);
		emailLabel.setBounds(125, 261, 63, 14);
		contentPane.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(210, 260, 165, 20);		
		contentPane.add(emailText);
	
//		JButton registerButton = new JButton("Register");
//		registerButton.setBounds(224, 277, 89, 23);
//		contentPane.add(registerButton);
//		registerButton.setEnabled(false);
//		
//		JButton cancelButton = new JButton("Cancel");
//		cancelButton.setBounds(335, 277, 89, 23);
//		contentPane.add(cancelButton);
	}
	
	private void addStaffNPORegisterPanel(){
		
		contentPane.removeAll();
		if(UserType == 0){
			JLabel currentUser = new JLabel("You are registering as NPO");
			currentUser.setFont(details);
			currentUser.setBounds(360,13, 264,20);
			contentPane.add(currentUser);
		}else if(UserType == 1){
			JLabel currentUser = new JLabel("You are registering as Staff");
			currentUser.setFont(details);
			currentUser.setBounds(360,13, 264,20);
			contentPane.add(currentUser);
		}else{
			JLabel currentUser = new JLabel("You are registering as Bidder");
			currentUser.setFont(details);
			currentUser.setBounds(360,13, 264,20);
			contentPane.add(currentUser);
		}
		addRadioButton();
		addRegisterButton();
		addCancelButton();
		JLabel label2 = new JLabel("Please enter the following information:");
		label2.setFont(details);
		label2.setBounds(125, 113, 300, 20);
		contentPane.add(label2);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(details);
		nameLabel.setBounds(125, 144, 63, 14);
		contentPane.add(nameLabel);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(details);
		usernameLabel.setBounds(125, 174, 84, 14);
		contentPane.add(usernameLabel);
		
		nameText = new JTextField();
		nameText.setBounds(210, 143, 165, 20);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBounds(210, 173, 165, 20);
		contentPane.add(usernameText);

		
	}
	
	private void addRegisterButton(){
		registerButton = new JButton("Register");
		registerButton.setBounds(124, 215, 154,23);
		contentPane.add(registerButton);
		registerButton.setEnabled(false);
		
	}
	
	private void addCancelButton(){
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(305, 215, 154,23);
		contentPane.add(cancelButton);
	}

	@Override
	public void actionPerformed(ActionEvent theEvent) {

		if(theEvent.getActionCommand().equals("NPO")
				|| theEvent.getActionCommand().equals("Staff")){
			
			if(theEvent.getActionCommand().equals("NPO")){
				UserType = 0;
			}else if(theEvent.getActionCommand().equals("Staff")){
				UserType = 1;
			}
//			else{
//				UserType = 2;
//			}
			//myFrame.setBounds(0, 0, 700, 400);
	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
			addStaffNPORegisterPanel();
			
			myFrame.repaint();
		} else if (theEvent.getActionCommand().equals("Bidder")){
			UserType = 2;
			addBidderRegisterPanel();
			//myFrame.setBounds(0, 0, 700, 400);
	        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
	                SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
			myFrame.repaint();
		}

		
	}
}
