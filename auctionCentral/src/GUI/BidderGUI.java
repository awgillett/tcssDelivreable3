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

import model.Calendar;
import model.User;

public class BidderGUI extends JPanel implements ActionListener{
	
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
	protected static JFrame myFrame = new JFrame();
	protected static Calendar myCalendar = new Calendar();
	public BidderGUI() {
		
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
