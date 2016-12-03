package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Auction;
import model.Bidder;
import model.Calendar;
import model.NPO;
import model.Staff;
import model.User;
import java.awt.GridLayout;




public class MainGUI implements MouseListener{
	int WINDOWWIDTH = 801; //I made this 701 because 700 caused an issue not sure why yet
	int WINDOWHEIGHT = 500;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight = ((int) screenSize.getHeight()/2) - WINDOWHEIGHT/2;
	int screenWidth = ((int) screenSize.getWidth()/2) - WINDOWWIDTH/2;

	
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
	
	protected static JFrame myFrame = new JFrame("Auction Central");

	protected static Calendar myCalendar = new Calendar();

	protected static ArrayList<User> userList = new ArrayList();


	public static void main(String[] args) {
	
	MainGUI GUI = new MainGUI();
	
	GUI.preLoad();
	GUI.start();

	}
	
	
	public void start(){
		setupFrame();
		addLoginPanel();
		myFrame.setVisible(true);

	}
	
	private void addLoginPanel(){
		HomeGUI Loginpanel = new HomeGUI(userList, myFrame);
		Loginpanel.startGUI();
	}
	
    /**
     * this method will keep the window in a fit size
     * and center screen position.
     */
    private void setupFrame() { 
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), myFrame.getHeight()));
        myFrame.setBounds(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                    SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        
        //for debug
        myFrame.addMouseListener(this);
    }
    

	
	private void saveAndExit() {
		try {
			FileOutputStream fileOut = new FileOutputStream("./Calendar.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myCalendar);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ./Calendar.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}

		try {
			FileOutputStream fileOut2 = new FileOutputStream("./Users.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
			out2.writeObject(userList);
			out2.close();
			fileOut2.close();
			System.out.printf("Serialized data is saved in ./Users.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	private void load() {
//		try {
//			FileInputStream fileIn = new FileInputStream("./Calendar.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			myCalendar = (Calendar) in.readObject();
//			in.close();
//			fileIn.close();
//		} catch (IOException i) {
//			i.printStackTrace();
//			return;
//		} catch (ClassNotFoundException c) {
//			System.out.println("Calendar class not found");
//			c.printStackTrace();
//			return;
//		}

		try {
			FileInputStream fileIn = new FileInputStream("./Users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userList = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
	}
	
	public void preLoad()
	{
		userList.add(new Staff("cseiber", "Carl"));
		userList.add(new NPO("seiber", "carl"));
		userList.add(new Staff("lseiber", "Lindsey"));
		
		for (int i = 0; i < 26; i++)
		{
			char ch;

			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
			userList.add(new Bidder("Bidder" + ch, Character.toString(ch), "111-1234", "123 Avenue", ch + "@hotmail.com", "CC 12345678"));
		}
		
		int count = 20;
		int month = 11;
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				myCalendar.addAuction((NPO) u, LocalDateTime.of(2016, month, count, 12, 00), 0, "");
				if (count >= 30)
				{
					count = 0;
					month = 12;
				}
				count++;
			}
		}
		
		for (Auction a : myCalendar.getAllAuctions())
		{
			for (int i = 0; i < 10; i++)
			{
				char ch;

				ch = (char) ((i) + 'a');
				a.addItem("Item" + ch, "", "good", "small", "", "", 25);
			}
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X: " + e.getX());
		System.out.println("Y: " + e.getY());

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
