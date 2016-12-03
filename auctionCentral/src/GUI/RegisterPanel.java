/**
 * 
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author RIm
 *
 */
public class RegisterPanel extends JPanel {
	
	
	protected void setupPanel() {

		this.setLayout(null);
		this.setSize(getPreferredSize());

		JLabel userLabel = new JLabel("Register for: ");
		userLabel.setBounds(10, 45, 80, 25);
		this.add(userLabel);
		
		addContinueButton();
		addCancelButton();
		

	}
	
	private void addContinueButton(){
		JButton registerButton = new JButton("Continue");
		registerButton.setBounds(30, 80, 80, 25);
		this.add(registerButton);	
		registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	
            }
        });
	}
	
	private void addCancelButton(){
		JButton registerButton = new JButton("Cancel");
		registerButton.setBounds(160, 80, 80, 25);
		this.add(registerButton);	
		registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	MainGUI.myFrame.getContentPane().removeAll();
            	//HomeGUI homePanel = new HomeGUI(MainGUI.userList);
            	//homePanel.setupPanel();
            	//MainGUI.myFrame.add(homePanel);
            	MainGUI.myFrame.setVisible(true);
            	
            }
        });
	}

}
