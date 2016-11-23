package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
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

public class MainGUI{
	
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
	
	protected static JFrame myFrame = new JFrame();

	protected static Calendar myCalendar = new Calendar();

	protected static ArrayList<User> userList = new ArrayList();


	public static void main(String[] args) {
	
	MainGUI GUI = new MainGUI();
	
	GUI.preLoad();
	GUI.start();

	}
	
	
	public void start(){
		myFrame.setName("Auction Central");
		myFrame.setSize(300, 150);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addLoginPanel();
		position();
		myFrame.setVisible(true);
	}
	
	private void addLoginPanel(){
		HomeGUI Loginpanel = new HomeGUI(userList);
		myFrame.add(Loginpanel);

	}
	
    /**
     * this method will keep the window in a fit size
     * and center screen position.
     */
    private void position() {   
        myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), myFrame.getHeight()));
        
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                    SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        myFrame.pack();

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

}
