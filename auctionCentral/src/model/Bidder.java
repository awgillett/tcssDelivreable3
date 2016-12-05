package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
/*
 * The Bidder class manages Bidder information and current bids
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class Bidder extends User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String myAddress;
	private String myPhone;
	private String myEmail;
	private String myPaymentInfo;
	private Collection<Bid> myBids;
	private static int bidCancelBuffer = 2;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/d/yyyy");
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
	 * @author aaron
	 * @return bidCancelBuffer the maximum number of 
	 * days before an auction that a bid may be reoved
	 */
	public int getBidCancelBuffer() {
		return bidCancelBuffer;
	}

	/**
	 * @author aaron
	 * set the bidCancelBuffer of the bidder
	 * @param newBuffer the maximum number of days before an auction that a bid may be reoved
	 */
	public void setBidCancelBuffer(int newBuffer) {
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
	 * @param itemToBidOn 
	 * @return true if the bid is a positive number & does not already exist
	 * @return false if the bid is negative or already exists
	 */
	public int addBid(Calendar cal, Item itemToBidOn, double bidOffer) {
		
//		Bid newBid = createBid(bidOffer, itemToBidOn, cal);
		
		int bidAccepted = 1;
		int bidBelowMinBid = 2;
		int bidForItemAlreadyExists = 3;
		int auctionHasStarted = 4;
		
//		if(!cal.requestBid(newBid.getMyItemID())){
//			return auctionHasStarted;
//		}else if(!itemToBidOn.isValidBid(newBid.getMyBidAmount())){
//			return bidBelowMinBid;
//		}else if(getBid(newBid.getMyItemID()) != null){
//			return bidForItemAlreadyExists;
//		}else{
//			placeBid(newBid);
//			return bidAccepted;
//		}
		
		if(!cal.requestBid(itemToBidOn.getMyItemID())){
			return auctionHasStarted;
		}else if(!itemToBidOn.isValidBid(bidOffer)){
			return bidBelowMinBid;
		}else if(getBid(itemToBidOn.getMyItemID()) != null){
			return bidForItemAlreadyExists;
		}else{
			placeBid(createBid(bidOffer, itemToBidOn, cal));
			return bidAccepted;
		}
	}
	
	/**
	 * @author aaron
	 * checks to make sure that the bid offer is a positive number
	 * checks to make sure that the the bidder does not already have a bit for this item
	 * @param myBid the Bid in question
	 * @param itemToBidOn 
	 * @return true if the bid is a positive number & does not already exist
	 * @return false if the bid is negative or already exists
	 */
	public int addBidGUI(Calendar cal, Item itemToBidOn, double bidOffer) {
		
		int bidAccepted = 1;
		int bidBelowMinBid = 2;
		int bidForItemAlreadyExists = 3;
		int auctionHasStarted = 4;
		
		if(!cal.requestBid(itemToBidOn.getMyItemID())){
			return auctionHasStarted;
		}else if(!itemToBidOn.isValidBid(bidOffer)){
			return bidBelowMinBid;
		}else if(getBid(itemToBidOn.getMyItemID()) != null){
			return bidForItemAlreadyExists;
		}else{
			return bidAccepted;
		}
	}
	
	/**
	 * @author aaron
	 * adds the supplied bid to the bidders list of bids
	 * @param newBid 
	 * @param myBid the Bid to add
	 * @return 
	 * @return void
	 */
	public boolean placeBid(Bid newBid) {
		boolean bidPlaced = true;
		if(newBid != null){
			myBids.add(newBid);
		}else{
			bidPlaced = false;
		}
		return bidPlaced;
		
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
		int bidFoundAndRemovable = 1;
		int bidFoundButNotRemovable = 2;
		int bidNotFound = 3;
		Bid myBid = getBid(itemID);
		if(myBid == null){
			return bidNotFound;
		}
		for(Auction a : myCalendar.getAllAuctions()){
			if(myBid.getMyAuctionID() == a.getMyID()){
				if(a.getAuctionDate().isBefore(LocalDateTime.now().plusDays(bidCancelBuffer))){
					return bidFoundButNotRemovable;
				}
				return bidFoundAndRemovable;
			}
		}
		return bidFoundAndRemovable;
	}
	
	/**
	 * @author aaron
	 * @removes the bid that is associated with the provided itemID
	 * @param itemID the item number corresponding to the bid removal
	 * @return true if the bid is successfully removed
	 * @return false if there is no bid associated with the itemID
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
	 * takes an itemID and returns the bid
	 * @param itemID the item number corresponding to the bid to return
	 * @return Bid if the bid is successfully found in the list of bids
	 * @return null if there is no bid associated with the itemID
	 */
	public Bid getBid(int itemID) {
		
		Bid myBid = null;
		for (Bid b : myBids){
			if (b.getMyItemID() == itemID)
				myBid = b;
		}
		return myBid;
	}
	
	/**
	 * @author aaron
	 * takes an itemID and returns a formated string showing the item and the bid
	 * @param itemID of the item corresponding to the bid to print
	 * @return str a formated string showing the itemID and the bid amount. If there is a bid for that item
	 * @return str If there is no bid for the item with the provided itemID. 
	 * A message indicating that currently there is no bid for the item associated with the provided itemID.
	 */
	public String printBid(int itemID){
		
		Bid b  = getBid(itemID);
		StringBuilder str = new StringBuilder();
		if(b == null){
			str.append("You currently have no bid for this item.");
		}else{
			str.append("\n\tItem Number: \tYour Bid:");
			str.append("\n\t"+ itemID + "\t\t" + b.getMyBidAmount());
		}
		return str.toString();
	}
	
	/**
	 * 
	 * @param myCalendar 
	 * @return str 
	 */
	public String printBids(Calendar myCalendar){
		StringBuilder str = new StringBuilder();
		myCalendar.getAllAuctions();
		str.append("\n\tItem Number: \tYour Bid:");
		for (Bid b : myBids){
			str.append("\n\t"+ b.getMyItemID() +"\t\t" + b.getMyBidAmount());
		}
		str.append("\n");
		return str.toString();
	}
	
	/**
	 * @author aaron
	 * takes an itemID and calendar and returns the item object or null if it does not exist
	 * @param itemID the ID of the item that the bidder wishes to bid on
	 * @param myCalendar the system calendar
	 * @return getItemToBidOn the item for the supplied ID or null if it does not exist
	 */
	public Item getItemToBidOn(int itemID, Calendar myCalendar) {
		
		return myCalendar.getItem(itemID);
	}
	
	/**
	 * @author aaron
	 * helper method that takes an item, an offer and a calendar and returns a bid object
	 * @param bidOffer the bid amount 
	 * @param itemToBidOn the item object that the bid is for
	 * @param myCalendar the calendar
	 * @return new Bid Object
	 */
	public Bid createBid(double bidOffer, Item itemToBidOn, Calendar myCalendar) {
		
		return new Bid(getMyUserName(), itemToBidOn.getMyItemID(), bidOffer, myCalendar.getAuction(itemToBidOn.getMyItemID()).getMyID());

		
	}
	/**
	 * @author aaron
	 * Prints a string showing all of the bidders bids. 
	 * This view is specifically designed for the bidder GUI main window.
	 * @param myCalendar
	 * @return String of all of the bidders current bids
	 */
	public String printBidsGUI(Calendar myCalendar) {
		StringBuilder str = new StringBuilder();
		str.append("Item ID\t");
		str.append("Item name\t");
		str.append("Minimum bid\t");
		str.append("Your bid\t");
		str.append("Auction date\t");
		str.append("\n");
		for (Bid b : myBids){
			str.append(b.getMyItemID());
			str.append("\t");
			str.append(myCalendar.getItem(b.getMyItemID()).getItemName());
			str.append("\t");
			str.append(myCalendar.getItem(b.getMyItemID()).getMyMinBid());
			str.append("\t");
			str.append(b.getMyBidAmount());
			str.append("\t");
			str.append(myCalendar.getAuction(b.getMyItemID()).getAuctionDate().format(dateFormat));
			str.append("\n");
		}
		return str.toString();
	}
}












