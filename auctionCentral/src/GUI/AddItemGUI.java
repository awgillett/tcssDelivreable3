package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Auction;
import model.NPO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddItemGUI extends JDialog {

	private JPanel contentPane;
	private Auction myAuction;
	private JTextField txtName;
	private JTextField txtMinBid;
	private JTextArea txtDescription;
	private JTextArea txtSnapshot;
	private JComboBox cboCondition;
	private JComboBox cboSize;
	private String itemInfo;
	private JButton btnAdd;
	private JTextField txtDonor;
	private JTextArea txtNotes;
	private Font mainFont = new Font("Tahoma", Font.BOLD, 22);
	private Font subMenuFont = new Font("Tahoma", Font.BOLD, 20);
	private Font detailsFont = new Font("Tahoma", Font.PLAIN, 15);

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddItemGUI frame = new AddItemGUI(new Auction(new NPO("NPOa", "a"), LocalDateTime.now(), 13, "", 3));
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
	public AddItemGUI(Auction theAuction) {
		myAuction = theAuction;
		setupSnapshot();
		setupGUI();
	}
	
	private void setupGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(mainFont);
		label.setBounds(0, 0, 784, 24);
		contentPane.add(label);
		
		JLabel lblWelcome = new JLabel("Welcome to the NPO Add Item Menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(subMenuFont);
		lblWelcome.setBounds(0, 29, 784, 24);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel_1 = new JLabel("Logged in as: " + myAuction.getNPO().getMyName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(detailsFont);
		lblNewLabel_1.setBounds(10, 52, 764, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(detailsFont);
		lblName.setBounds(58, 112, 45, 17);
		contentPane.add(lblName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(detailsFont);
		lblDescription.setBounds(21, 251, 82, 17);
		contentPane.add(lblDescription);
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setFont(detailsFont);
		lblCondition.setBounds(31, 140, 72, 17);
		contentPane.add(lblCondition);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(detailsFont);
		lblSize.setBounds(71, 168, 32, 17);
		contentPane.add(lblSize);
		
		JLabel lblMinimumBid = new JLabel("Minimum Bid:");
		lblMinimumBid.setFont(detailsFont);
		lblMinimumBid.setBounds(10, 196, 93, 17);
		contentPane.add(lblMinimumBid);
		
		cboCondition = new JComboBox();
		cboCondition.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateItemInfo();
			}
		});
		cboCondition.setModel(new DefaultComboBoxModel(new String[] {"Acceptable", "Good", "Very Good", "Like New", "New"}));
		cboCondition.setBounds(113, 140, 200, 20);
		contentPane.add(cboCondition);
		
		cboSize = new JComboBox();
		cboSize.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateItemInfo();
			}
		});		//(no dimension is greater than one foot)
				//(at least one dimension is greater than one foot but no dimension is greater than three feet)
				//(at least one dimension is greater than three feet)
		cboSize.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium ", "Large"}));
		cboSize.setBounds(113, 168, 200, 20);
		contentPane.add(cboSize);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
				isValidItem();
			}
		});		
		txtName.setBounds(113, 112, 200, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtMinBid = new JTextField();
		txtMinBid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
				isValidItem();
			}
		});
		txtMinBid.setBounds(113, 196, 200, 20);
		contentPane.add(txtMinBid);
		txtMinBid.setColumns(10);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
			}
		});
		txtDescription.setBounds(113, 249, 200, 64);
		contentPane.add(txtDescription);
		//txtDescription.setText(myItem.getMyDescription());
		
		JLabel lblNewLabel = new JLabel("Item Information Snapshot");
		lblNewLabel.setFont(detailsFont);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(344, 109, 190, 24);
		contentPane.add(lblNewLabel);
		
		txtSnapshot = new JTextArea(itemInfo);
		txtSnapshot.setWrapStyleWord(true);
		txtSnapshot.setLineWrap(true);
		txtSnapshot.setBounds(344, 138, 328, 248);
		contentPane.add(txtSnapshot);
		
		btnAdd = new JButton("Add Item");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(myAuction.addItem(txtName.getText(), txtDonor.getText(), cboCondition.getSelectedItem().toString(), 
								  cboSize.getSelectedItem().toString(), txtNotes.getText(), txtDescription.getText(),
								  Double.parseDouble(txtMinBid.getText()))) 
					addItemSuccess();
				else
					addItemFail();
			}
		});
		btnAdd.setToolTipText("Click here to submit an Auction Request");
		btnAdd.setBounds(98, 431, 154, 23);
		btnAdd.setEnabled(false);
		contentPane.add(btnAdd);
		
		JButton btnCancelItemAddition = new JButton("Cancel Item Addition");
		btnCancelItemAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnCancelItemAddition.setToolTipText("Click here to edit items, add items, remove items, or cancel the auction.");
		btnCancelItemAddition.setBounds(273, 431, 154, 23);
		contentPane.add(btnCancelItemAddition);
		
		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setFont(detailsFont);
		lblDonor.setBounds(55, 221, 48, 17);
		contentPane.add(lblDonor);
		
		txtDonor = new JTextField();
		txtDonor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
			}
		});
		txtDonor.setColumns(10);
		txtDonor.setBounds(113, 221, 200, 20);
		contentPane.add(txtDonor);
		
		txtNotes = new JTextArea();
		txtNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateItemInfo();
			}
		});
		txtNotes.setWrapStyleWord(true);
		txtNotes.setLineWrap(true);
		txtNotes.setBounds(113, 322, 200, 64);
		contentPane.add(txtNotes);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(detailsFont);
		lblNotes.setBounds(55, 324, 48, 17);
		contentPane.add(lblNotes);
		
		JButton btnDone = new JButton("Finished");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnDone.setToolTipText("Click Here to return to the Auction Edit Menu");
		btnDone.setBounds(448, 431, 154, 23);
		contentPane.add(btnDone);
	}
	
	private void setupSnapshot() {
		itemInfo = "Name:\nDonor:\nCondition:\nSize:\nMinimum Bid:\nDescription:\nNotes:";
	}
	
	private void updateItemInfo() {
		itemInfo = "Name:  " + txtName.getText() + "\nDonor: " + txtDonor.getText()
		 + "\nCondition: " + cboCondition.getSelectedItem().toString() + "\nSize: " + cboSize.getSelectedItem().toString()
		 + "\nMinimum Bid: " + txtMinBid.getText()+ "\nDescription: " + txtDescription.getText() + "\nNotes: " + txtNotes.getText();
		
		txtSnapshot.setText(itemInfo);
	}
	
	private void isValidItem() {
		if (txtName.getText().length() != 0 && txtMinBid.getText().length() != 0) {
			if (tryParseDouble(txtMinBid.getText())) {
				btnAdd.setEnabled(true);
				txtMinBid.setBackground(Color.WHITE);
			}
				
			else {
				txtMinBid.setBackground(Color.RED);
				btnAdd.setEnabled(false);
			}				
		}			
	}
	
	private void addItemSuccess() {
		JOptionPane.showMessageDialog(this, "Item has been successfully added to Inventory!");
		clearAll();
		btnAdd.setEnabled(false);
	}
	
	private void addItemFail() {
		JOptionPane.showMessageDialog(this, null, "Oops failed to add item, it appears you already have this item in inventory.", 0);
	}
	
	boolean tryParseDouble(String value) {  
	     try {  
	         Double.parseDouble(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	private void close() {
		//this.setVisible(false);
		this.dispose();
	}
	
	private void clearAll() {
		txtDescription.setText("");
		txtName.setText("");
		txtMinBid.setText("");
		txtSnapshot.setText(itemInfo);
		txtDonor.setText("");
		txtNotes.setText("");
	}
}
