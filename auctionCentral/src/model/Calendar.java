package model;
//match

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Calendar class holds list of auction.
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class Calendar implements Serializable {


	private static final long serialVersionUID = -2370558377553764986L;
	
	// assume 1 month in future is 30 days in future.
	private static final int TOTAL_NUMBER_OF_AUCTION_ALLOWED = 60;
	private Collection<Auction> myAuctionList;
	private int nextAuctionID; 
	private int maxNumberOfAuction;
	/**
	 * constructs a calendar with a auction list and auction ID
	 * @param theAuction
	 */
	public Calendar(Collection<Auction> theAuction) {
		myAuctionList = theAuction;
		nextAuctionID = 1;
		maxNumberOfAuction = 25;
		
	}
	
	public Calendar() {
		myAuctionList = new ArrayList<Auction>();
		maxNumberOfAuction = 25;
	}
	
	
	/**
	 * set new maximum number of Auction.
	 * @param theNumber new maximum number of auction
	 * @return return true if the number is set.
	 */
	public boolean isValidMaxNumberOfAuction(int theNumber){		
		if(theNumber <= 0){
			return false;
		}else if (theNumber > TOTAL_NUMBER_OF_AUCTION_ALLOWED){
			return false;
		}else if (theNumber <= maxNumberOfAuction){
			return false;
		}else{
			return true;
		}
	}
	
	public void setMaxNumberOfAuction(int theNumber){
		if(isValidMaxNumberOfAuction(theNumber)){
			maxNumberOfAuction = theNumber;
		}
	}

	
	public int getMaxNumberOfAuction(){
		return maxNumberOfAuction;
	}
	
	/**
	 * adds an auction to the calendar if the requested date is valid
	 * @param theNPO the NPO who request the auction
	 * @param theDate the Date of the auction
	 * @param numItems Number of Item
	 * @param theNotes the description.
	 * @return true is the auction is added.
	 */
	public boolean addAuction(NPO theNPO, LocalDateTime theDate, int numItems, String theNotes) {
		int auctions = 0;
		if(!theNPO.hasAuction() && theNPO.isValidAuctionDate(theDate) && myAuctionList.size() < 25){
			for (Auction a : myAuctionList)
			{
				if(a.getAuctionDate().toLocalDate().isEqual(theDate.toLocalDate()))
					auctions++;
			}
			if (auctions < maxNumberOfAuction)
			{
				Auction newAuction = new Auction(theNPO, theDate, numItems, theNotes, nextAuctionID);
				myAuctionList.add(newAuction);
				theNPO.setAuction(true);
				theNPO.setLastAuctionDate(theDate);
				nextAuctionID++;
				return true;
			}

		} 
		return false;
	}
	
	//Strictly for running testing. Allows any auction object to be added to the calendar without safeguards
	public void addAuction(Auction a) {
		myAuctionList.add(a);
	}
	
	/**
	 * places a bid for and item as long as the bid is valid
	 * @param theBidder the bidder 
	 * @param theItemID the Item (number) that bidder bid on.
	 * @param theBidAmount bid price
	 * @param theAuctionID the auction (number) that the bidder is in.
	 * @return true if bid is made.
	 */
	public boolean requestBid(Bidder theBidder, int theItemID, double theBidAmount, int theAuctionID)
	{
		for (Auction a : myAuctionList)
		{
			if (a.getMyID() == theAuctionID)
			{
				if(a.getAuctionDate().isBefore(LocalDateTime.now()) || a.getAuctionDate().isEqual((LocalDateTime.now()))){
					return false;
				}
				if (a.getItem(theItemID).isValidBid(theBidAmount))
				{
					theBidder.addBid(new Bid(theBidder.getMyName(), theItemID, theBidAmount, theAuctionID));
					return true;
				}
			}
		}
		
		return false;
	}
	
	

	
	/**
	 * get a list of available auctions
	 * @return all the auctions.
	 */
	public Collection<Auction> getAllAuctions() {
		return myAuctionList;		
	}
	
	/**
	 * get the auction associated with the NPO
	 * @param theNPO 
	 * @return the Auction associate with the NPO
	 */
	public Auction getAuction(NPO theNPO)
	{
		Auction auction = null;
		for (Auction a : myAuctionList)
		{
			if (a.getNPO().getMyUserName().equals(theNPO.getMyUserName()))
			{
				auction = a;
				break;
			}
		}
		return auction;
	}
	
	
	/**
	 * deletes an auction
	 * @param NPO
	 * @return True if the auction is deleted.
	 */
	public boolean deleteAuction(String NPO){
		for (Auction a : myAuctionList)
		{
			if (a.getNPO().getMyName() == NPO)
			{
				a.getNPO().setAuction(false);
				myAuctionList.remove(a);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get the date of an auction
	 * @param theDate
	 * @return the number of auction in theDate
	 */
	public int getAuctionDayCount(LocalDate theDate)
	{
		int count = 0;
		for (Auction a : myAuctionList)
		{
			if(a.getAuctionDate().toLocalDate().isEqual(theDate))
				count++;
		}
		return count;
	}
	
}
