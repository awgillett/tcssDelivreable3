package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import model.Item;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ItemEditGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtMinBid;
	JTextArea txtDescription;
	JTextArea txtNewInfo;
	private JComboBox cboCondition;
	private JComboBox cboSize;
	private Item myItem;
	private String curItemInfo;
	private String newItemInfo;
	//private JLabel lblNewInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemEditGUI frame = new ItemEditGUI(new Item("Iphone 7","Like New", "Small", 350));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ItemEditGUI(Item theItem) {
		myItem = theItem;
		setItemInfo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Auction Central");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label.setBounds(0, 0, 691, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Welcome to the NPO Auction Item Edit Menu");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_1.setBounds(0, 29, 691, 24);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Logged in as: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(0, 64, 347, 24);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel((String) null);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(357, 64, 315, 24);
		contentPane.add(label_3);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(58, 112, 45, 17);
		contentPane.add(lblName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(21, 140, 82, 17);
		contentPane.add(lblDescription);
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCondition.setBounds(31, 235, 72, 17);
		contentPane.add(lblCondition);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSize.setBounds(71, 263, 32, 17);
		contentPane.add(lblSize);
		
		JLabel lblMinimumBid = new JLabel("Minimum Bid:");
		lblMinimumBid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMinimumBid.setBounds(10, 291, 93, 17);
		contentPane.add(lblMinimumBid);
		
		cboCondition = new JComboBox();
		cboCondition.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateItemInfo();
			}
		});
		cboCondition.setModel(new DefaultComboBoxModel(new String[] {"Acceptable", "Good", "Very Good", "Like New", "New"}));
		cboCondition.setBounds(113, 235, 200, 20);
		contentPane.add(cboCondition);
		
		cboSize = new JComboBox();
		cboSize.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateItemInfo();
			}
		});														//(no dimension is greater than one foot) (at least one dimension is greater than one foot but no dimension is greater than three feet) (at least one dimension is greater than three feet)
		cboSize.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium ", "Large"}));
		cboSize.setBounds(113, 263, 200, 20);
		contentPane.add(cboSize);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
			}
		});		
		txtName.setBounds(113, 112, 200, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setText(myItem.getItemName());
		
		txtMinBid = new JTextField();
		txtMinBid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
			}
		});
		txtMinBid.setBounds(113, 291, 200, 20);
		contentPane.add(txtMinBid);
		txtMinBid.setColumns(10);
		txtMinBid.setText(Double.toString(myItem.getMyMinBid()));
		
		txtDescription = new JTextArea();
		txtDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateItemInfo();
			}
		});
		txtDescription.setBounds(113, 138, 200, 86);
		contentPane.add(txtDescription);
		txtDescription.setText(myItem.getMyDescription());
		
//		JLabel lblOrgInfo = new JLabel(curItemInfo);
//		lblOrgInfo.setBackground(Color.LIGHT_GRAY);
//		lblOrgInfo.setHorizontalAlignment(SwingConstants.CENTER);
//		lblOrgInfo.setVerticalAlignment(SwingConstants.TOP);
//		lblOrgInfo.setBounds(357, 140, 200, 168);
//		contentPane.add(lblOrgInfo);
		
//		lblNewInfo = new JLabel(newItemInfo);
//		lblNewInfo.setVerticalAlignment(SwingConstants.TOP);
//		lblNewInfo.setBounds(332, 266, 124, 168);
//		contentPane.add(lblNewInfo);
		
		JLabel lblNewLabel = new JLabel("Current Item Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(357, 115, 190, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewItemInfo = new JLabel("New Item Info");
		lblNewItemInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewItemInfo.setBounds(557, 115, 124, 24);
		contentPane.add(lblNewItemInfo);
		
		txtNewInfo = new JTextArea(newItemInfo);
		txtNewInfo.setLineWrap(true);
		txtNewInfo.setWrapStyleWord(true);
		txtNewInfo.setBounds(552, 138, 129, 175);
		contentPane.add(txtNewInfo);
		
		JTextArea textArea = new JTextArea(curItemInfo);
		textArea.setBounds(344, 138, 203, 175);
		contentPane.add(textArea);
	}
	
	private void setItemInfo() {
		curItemInfo = "Name:  " + myItem.getItemName()
			    + "\nDescription: " + myItem.getMyDescription() + "\nCondition: "
			    + myItem.getMyCondition() + "\nSize: " + myItem.getMySize()
			    + "\nMinimum Bid: " + myItem.getMyMinBid();
		
		newItemInfo = myItem.getItemName() + "\n" + myItem.getMyDescription() + "\n"
				+ myItem.getMyCondition() + "\n" + myItem.getMySize()
				+ "\n" + myItem.getMyMinBid();
	}
	
	private void updateItemInfo() {
		newItemInfo = txtName.getText() + "\n" + txtDescription.getText() + "\n"
		+ cboCondition.getSelectedItem().toString() + "\n" + cboSize.getSelectedItem().toString()
		+ "\n" + txtMinBid.getText();
		
		txtNewInfo.setText(newItemInfo);
		//lblNewInfo.setText(newItemInfo);
	}
}
