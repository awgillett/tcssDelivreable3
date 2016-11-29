package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Calendar;
import model.NPO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AuctionRequestGUI extends JDialog {

	//private final JPanel contentPanel = new JPanel();
	private Calendar myCalendar;
	private NPO myNPO;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AuctionRequestGUI dialog = new AuctionRequestGUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AuctionRequestGUI(NPO theNPO, Calendar theCalendar) {
		myCalendar = theCalendar;
		myNPO = theNPO;
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label.setBounds(0, 0, 788, 24);
		getContentPane().add(label);
		
		JLabel lblWelcome = new JLabel("Welcome to the Auction Request Menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblWelcome.setBounds(0, 29, 788, 24);
		getContentPane().add(lblWelcome);
		
		JLabel label_2 = new JLabel("Logged in as: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(10, 52, 366, 24);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel(theNPO.getMyName());
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(386, 52, 392, 24);
		getContentPane().add(label_3);
		
//		setBounds(100, 100, 450, 300);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setLayout(new FlowLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
}
