package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Calendar;
import model.User;

public class HomeGUI extends JPanel implements ActionListener{
	
	private JTextField userText;
	//protected static Calendar myCalendar = new Calendar();
	private ArrayList<User> userList = new ArrayList();
	User curUser;
	JLabel message = new JLabel("Welcome");

	public HomeGUI(ArrayList<User> theUserList){
		userList = theUserList;
		curUser = null;
		setupPanel();
	}

	protected void setupPanel() {

		this.setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 45, 80, 25);
		this.add(userLabel);
		
		message.setBounds(100, 15, 100, 25);
		this.add(message);
		
		userText = new JTextField(20);
		userText.setBounds(100, 45, 160, 25);
		this.add(userText);
		userText.addActionListener(this);
		
		addLoginButton();
		addRegisterButton();
	}
	
	private void addLoginButton(){
		JButton loginButton = new JButton("login");
		loginButton.setBounds(30, 80, 80, 25);
		loginButton.addActionListener(this);
		this.add(loginButton);
		
	}
	
	private void addRegisterButton(){
		JButton registerButton = new JButton("register");
		registerButton.setBounds(160, 80, 80, 25);
		this.add(registerButton);	
		registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	MainGUI.myFrame.getContentPane().removeAll();
            	RegisterPanel RegisterPanel = new RegisterPanel();
            	RegisterPanel.setupPanel();
            	MainGUI.myFrame.add(RegisterPanel);
            	MainGUI.myFrame.setVisible(true);
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