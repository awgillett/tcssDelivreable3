package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;

public class NPO extends User implements Serializable{
	private boolean hasAuction;
	LocalDateTime lastAuctionDate;
	
	/**
	 * NPO user object.
	 * @param theUserName username of NPO 
	 * @param theName name of NPO
	 */
	public NPO(String theUserName, String theName){
		super(theUserName, theName, "NPO");
		hasAuction = false;
		lastAuctionDate = LocalDateTime.of(1970, 1, 1, 12, 00); //Default initializer
	}
	
	/**
	 * set the NPO to have an auction or not.
	 * @param isNPOHasAuction true if NPO has auction, otherwise false.
	 */
	public void setAuction(boolean isNPOHasAuction){
		hasAuction = isNPOHasAuction;
	}
	
	/**
	 * get the date of last auction
	 * @return the date of last auction
	 */
	public LocalDateTime getLastAuctionDate() {
		return lastAuctionDate;
	}
	
	/**
	 * set date for last auction
	 * @param lastAuctionDate the date for last auction
	 */
	public void setLastAuctionDate(LocalDateTime lastAuctionDate) {
		this.lastAuctionDate = lastAuctionDate;
	}
	
	/**
	 * check to see the NPO has auction or not
	 * @return true if the NPO has Auction, otherwise false.
	 */
	public boolean hasAuction(){
		return hasAuction;
	}
	
//	public boolean auctionRequest(LocalDateTime theTime, int itemNumber){
//		
//		if (hasAuction || !isValidAuctionDate(theTime)){
//			return false;
//		}
//		//send request here (could return false still)
//		return true;
//	}
	
	/**
	 * check to see if new Auction can be add on the passed in date
	 * @param theAuctionDate the date
	 * @return true if auction can be added, otherwise false.
	 */
	public boolean isValidAuctionDate(LocalDateTime theAuctionDate){
		LocalDateTime now = LocalDateTime.now(); //today
		Period prevAuctionTime = Period.between(lastAuctionDate.toLocalDate(), theAuctionDate.toLocalDate()); // period from last auction date to this auction date
		Period futureTimes = Period.between(now.toLocalDate(), theAuctionDate.toLocalDate()); // period from now to this auction date to be added
		
		if (prevAuctionTime.getYears() < 1)
		{
			return false;
		}

		if (futureTimes.getMonths() > 1 || futureTimes.getYears() > 0)

		{			
			return false;
		}
		else if (futureTimes.getMonths() == 1)
		{
			if (now.getDayOfMonth() < theAuctionDate.getDayOfMonth())
				return false;
		}
		else if (futureTimes.getDays() < 7)
				return false;
		
			return true;
		
	}
		
}
