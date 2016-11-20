package model;
//match
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
/*
 * The Bidder class manages Bidder information and current bids
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class Bidder extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String myAddress;
	private String myPhone;
	private String myEmail;
	private String myPaymentInfo;
	private Collection<Bid> myBids;
	/**
     * a Bidder has user name, name, address, phone, email, and payment info 
     * @param theUserName
     * @param theName
     * @param theAddress
     * @param thePhone
     * @param theEmail
     * @param thePaymentInfo
     */
	public Bidder(String theUserName, String theName, String theAddress, String thePhone, String theEmail, String thePaymentInfo)
	{
		super(theUserName, theName, "Bidder");
		myAddress = theAddress;
		myPhone = thePhone;
		myEmail = theEmail;
		myPaymentInfo = thePaymentInfo;	
		myBids = new ArrayList<Bid>();
	}
	
	/**
	 * get the address of the bidder
	 * @return the myAddress
	 */
	public String getMyAddress() {
		return myAddress;
	}

	/**
	 * set the address of the bidder
	 * @param myAddress the myAddress to set
	 */
	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}

	/**
	 * get the phone number of the bidder
	 * @return the myPhone
	 */
	public String getMyPhone() {
		return myPhone;
	}

	/**
	 * set the phone number of the bidder
	 * @param myPhone the myPhone to set
	 */
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}

	/**
	 * get the email of the bidder
	 * @return the myEmail
	 */
	public String getMyEmail() {
		return myEmail;
	}

	/**
	 * set the email of the bidder
	 * @param myEmail the myEmail to set
	 */
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}

	/**
	 * get the payment info for the bidder
	 * @return the myPaymentInfo
	 */
	public String getMyPaymentInfo() {
		return myPaymentInfo;
	}

	/**
	 * set the payment info for the bidder
	 * @param myPaymentInfo the myPaymentInfo to set
	 */
	public void setMyPaymentInfo(String myPaymentInfo) {
		this.myPaymentInfo = myPaymentInfo;
	}	
	
	/**
	 * get all the bidders bids
	 * @return the myBids
	 */
	public Collection<Bid> getMyBids() {
		return myBids;
	}

	/**
	 * checks if bid is valid then adds it if it is valid
	 * @param myBid the Bid to set
	 * @return true if the bid is valid, else false
	 */
	public boolean addBid(Bid myBid) {
		if (myBid.getMyBidAmount() <= 0)
			return false;
		for (Bid b : myBids)
		{
			if (b.getMyItemID() == myBid.getMyItemID())
				return false;
		}
		return true;
	}
	
	public void placeBid(Bid myBid) {
		
		myBids.add(myBid);
	}
	

	public String printBids(){
		
		
	
		StringBuilder str = new StringBuilder();

		
		str.append("\n\tItem Number: \tYour Bid");
		
		for (Bid b : myBids)
		{
			//System.out.println("printBids");
			str.append("\n\t"+ b.getMyItemID() + "\t\t" + b.getMyBidAmount());

		}
		str.append("\n");
		return str.toString();
	}

}
