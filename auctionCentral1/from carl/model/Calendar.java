package model;
//match

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Calendar class holds list of auction.
 * @author 
 */
public class Calendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Auction> myAuctionList;
	private int nextAuctionID; 
	
	public Calendar(Collection<Auction> theAuction) {
		myAuctionList = theAuction;
		nextAuctionID = 1;
	}
	
	public Calendar() {
		myAuctionList = new ArrayList<Auction>();
	}
	
	// didn't catch the BR : no more than 2 auctions per day.
	/**
	 * 
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
			if (auctions < 2)
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
	
	/**
	 * 
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
				if (a.getItem(theItemID).isValidBid(theBidAmount))
				{
					theBidder.addBid(new Bid(theBidder.getMyName(), theItemID, theBidAmount, theAuctionID));
					return true;
				}
			}
		}
		
		return false;
	}
	
	
//	public Collection<Auction> getAuctionsAfterThisDate(AuctionDate theDate) {
//		//returnList contain all the auctions after the pass in date
//		Collection<Auction> returnList = new ArrayList<Auction>();
//		for (Auction curAuction : myAuctionList){
////			if (curAuction.getAuctionDate().isAfterThisDate(theDate)){
////				returnList.add(curAuction);
////			}
//		}
//		return returnList;
//	}
	
	/**
	 * 
	 * @return all the auctions.
	 */
	public Collection<Auction> getAllAuctions() {
		return myAuctionList;		
	}
	
	/**
	 * 
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
	 * 
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
	 * 
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
