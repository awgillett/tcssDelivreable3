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
	 * @return the myAddress
	 */
	public String getMyAddress() {
		return myAddress;
	}

	/**
	 * @param myAddress the myAddress to set
	 */
	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}

	/**
	 * @return the myPhone
	 */
	public String getMyPhone() {
		return myPhone;
	}

	/**
	 * @param myPhone the myPhone to set
	 */
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}

	/**
	 * @return the myEmail
	 */
	public String getMyEmail() {
		return myEmail;
	}

	/**
	 * @param myEmail the myEmail to set
	 */
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}

	/**
	 * @return the myPaymentInfo
	 */
	public String getMyPaymentInfo() {
		return myPaymentInfo;
	}

	/**
	 * @param myPaymentInfo the myPaymentInfo to set
	 */
	public void setMyPaymentInfo(String myPaymentInfo) {
		this.myPaymentInfo = myPaymentInfo;
	}	
	
	/**
	 * @return the myBids
	 */
	public Collection<Bid> getMyBids() {
		return myBids;
	}

	/**
	 * checks if bid is valid then adds it if it is
	 * @param myBid the Bid to set
	 */
	public boolean addBid(Bid myBid) {
		if (myBid.getMyBidAmount() <= 0)
			return false;
		for (Bid b : myBids)
		{
			if (b.getMyItemID() == myBid.getMyItemID())
				return false;
		}
		myBids.add(myBid);
		return true;
	}

}
