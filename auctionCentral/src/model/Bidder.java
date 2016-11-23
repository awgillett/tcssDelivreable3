package model;
//match
import java.io.Serializable;
import java.time.LocalDateTime;
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
	private static int bidCancelBuffer = 2;
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
	public int bidCancelBuffer() {
		return bidCancelBuffer;
	}

	/**
	 * set the address of the bidder
	 * @param myAddress the myAddress to set
	 */
	public void bidCancelBuffer(int newBuffer) {
		Bidder.bidCancelBuffer = newBuffer;
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
	 * @author aaron
	 * checks to make sure that the bid offer is a positive number
	 * checks to make sure that the the bidder does not already have a bit for this item
	 * @param myBid the Bid in question
	 * @return true if the bid is a positive number & does not already exist
	 * @return false if the bid is negative or already exists
	 */
	public boolean addBid(Bid myBid) {
		
		if (myBid.getMyBidAmount() <= 0)
			return false;
		for (Bid b : myBids){
			
			if (b.getMyItemID() == myBid.getMyItemID())
				return false;
		}
		return true;
	}
	
	/**
	 * @author aaron
	 * adds the supplied bid to the bidders list of bids
	 * @param myBid the Bid to add
	 * @return void
	 */
	public void placeBid(Bid myBid) {
		
		myBids.add(myBid);
	}
	
	/**
	 * @author aaron
	 * checks if a bid can be removed from the bidders list of bids
	 * @param itemID the item number corresponding to the bid removal request
	 * @param myCalendar The calendar which holds all of the auctions
	 * @return 1 if the bid found and able to be canceled
	 * @return 2 if the bid is found but may not be canceled do to the date of the auction
	 * @return 3 if the bid does not exist
	 */
	public int bidRemovalRequest(int itemID, Calendar myCalendar){
		int itemFoundAndRemovable = 1;
		int itemFoundButNotRemovable = 2;
		int itemNotFound = 3;
		
		Bid myBid = getBid(itemID);
		
		if(myBid == null){
			// there is no bid for the item
			return itemNotFound;
		}
		for(Auction a : myCalendar.getAllAuctions()){
			
			if(myBid.getMyAuctionID() == a.getMyID()){
				
				if(a.getAuctionDate().isBefore(LocalDateTime.now().plusDays(bidCancelBuffer))){
					return itemFoundButNotRemovable;
				}
				return itemFoundAndRemovable;
			}
		}

		return itemFoundAndRemovable;

	}
	
	/**
	 * @author aaron
	 * @removes the bid that is associated with the provided itemID
	 * @param itemID the item number corresponding to the bid removal
	 * @return true if the bid is successfully removed
	 * @return false if there is no bid for associated with the itemID
	 */
	public boolean removeTheBid(int itemID) {
		
		Bid myBid = getBid(itemID);
		if(myBid == null){
			return false;
		}
		myBids.remove(myBid);
		return true;
	}	
	
	/**
	 * @author aaron
	 * @takes an itemID and returns the bid
	 * @param itemID the item number corresponding to the bid to return
	 * @return Bid if the bid is successfully found in the list of bids
	 * @return null if there is no bid for associated with the itemID
	 */
	private Bid getBid(int itemID) {
		
		Bid myBid = null;
		
		for (Bid b : myBids){
			if (b.getMyItemID() == itemID)
				myBid = b;
		}
		return myBid;
		
	}

	public String printBid(int itemID){
		StringBuilder str = new StringBuilder();
		
		Bid b  = getBid(itemID);
		
		str.append("\n\tItem Number: \tYour Bid");
		str.append("\n\t"+ b.getMyItemID() + "\t\t" + b.getMyBidAmount());
		
		return str.toString();
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
	
	public void addBidRequest(int itemID, Calendar myCalendar) {
		
		
		
		for(Auction a : myCalendar.getAllAuctions()){
			for(Item i : a.getItemList()){
				if(i.getMyItemID() == itemID){
					
					
//					System.out.println("Bidding on:");
//					System.out.println("\nItem ID: \tName: \t\tMin Bid: \tCondition: \tdescription: ");
//					System.out.println(" "+a.getItem(itemID).getMyItemID()+ "\t\t" + a.getItem(itemID).getItemName()+
//							"\t\t" + a.getItem(itemID).getMyMinBid() +"\t\t"+ a.getItem(itemID).getMyCondition() 
//							+"\t"+a.getItem(itemID).getMyDescription());
//					thisAuct = a;
//					found = true;
				}
			}
		}
		
	}


}
