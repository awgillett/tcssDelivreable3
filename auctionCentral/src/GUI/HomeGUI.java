package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import model.*;


public class HomeGUI implements ActionListener{
	
	private static String welcomeBanner = "Welcome to Auction Central";
	private static Font details = new Font("Tahoma", Font.PLAIN, 15);
	private static Font welcome = new Font("Tahoma", Font.BOLD, 22);
	private static Font subWelcome = new Font("Tahoma", Font.BOLD, 20);
	
	private JTextField userText;
	//protected static Calendar myCalendar = new Calendar();
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
		userLabel.setBounds(20, 85, 80, 25);
		userLabel.setFont(details);
		myFrame.getContentPane().add(userLabel);
	}
	
	private void addTextBox(){
		userText = new JTextField(20);
		userText.setBounds(110, 85, 160, 25);
		
		myFrame.getContentPane().add(userText);
		userText.addActionListener(this);
	}
	
	private void addTitle(){
		JLabel lblWelcomeBanner = new JLabel(welcomeBanner);
		lblWelcomeBanner.setFont(welcome);
		lblWelcomeBanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBanner.setBounds(10, 10, 400, 20);
		myFrame.getContentPane().add(lblWelcomeBanner);
		
		JLabel lblYouAreSignedAs = new JLabel("Please sign in:");
		lblYouAreSignedAs.setFont(subWelcome);
		lblYouAreSignedAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreSignedAs.setBounds(20, 55, 300, 30);
		myFrame.getContentPane().add(lblYouAreSignedAs);
	}
	
	private void addLoginButton(){
		JButton loginButton = new JButton("login");
		loginButton.setBounds(40, 120, 80, 25);
		loginButton.addActionListener(this);
		myFrame.getContentPane().add(loginButton);
		
	}
	
	private void addRegisterButton(){
		JButton registerButton = new JButton("register");
		registerButton.setBounds(170, 120, 80, 25);
		myFrame.getContentPane().add(registerButton);	
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
		} else{
			System.out.println("username doesn't exist, please try again");
			message.setText("Please try again");
			userText.setText("");
		}
		
	}

}