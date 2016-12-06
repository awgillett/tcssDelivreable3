package model;
//match 

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.time.Period;

/**
 * Calendar class holds list of auction.
 * 
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class AuctionCalendar implements Serializable {

	private static final long serialVersionUID = -2370558377553764986L;

	// assume 1 month in future is 30 days in future.
	public static final int TOTAL_NUMBER_OF_AUCTION_ALLOWED = 60;
	private Collection<Auction> myAuctionList;
	private int nextAuctionID;
	private int maxNumberOfAuction;
	private int maxAuctionsPerDay;

	/**
	 * constructs a calendar with a auction list and auction ID
	 * 
	 * @param theAuction
	 */
	public AuctionCalendar(Collection<Auction> theAuction) {
		myAuctionList = theAuction;
		nextAuctionID = 1;
		maxNumberOfAuction = 25;
		maxAuctionsPerDay = 2;
	}

	public AuctionCalendar() {
		myAuctionList = new ArrayList<Auction>();
		nextAuctionID = 1;
		maxNumberOfAuction = 25;
		maxAuctionsPerDay = 2;
		
	}

	/**
	 * set new maximum number of Auction.
	 * 
	 * @param theNumber
	 *            new maximum number of auction
	 * @return return true if the number is set.
	 */
	public boolean isValidMaxNumberOfAuction(int theNumber) {
		if (theNumber <= 0) {
			return false;
		} else if (theNumber > TOTAL_NUMBER_OF_AUCTION_ALLOWED) {
			return false;
		} else if (theNumber <= getAllAuctions().size()) {
			return false;
		} else {
			return true;
		}
	}

	public void setMaxNumberOfAuction(int theNumber) {
		if (isValidMaxNumberOfAuction(theNumber)) {
			maxNumberOfAuction = theNumber;
		}
	}

	public int getMaxNumberOfAuction() {
		return maxNumberOfAuction;
	}

	/**
	 * adds an auction to the calendar if the requested date is valid
	 * 
	 * @param theNPO
	 *            the NPO who request the auction
	 * @param theDate
	 *            the Date of the auction
	 * @param numItems
	 *            Number of Item
	 * @param theNotes
	 *            the description.
	 * @return 0 already has auction.
	 * @return 1 is outside the timefram allowed.
	 * @return 2 auction central is full.
	 * @return 3 this day is full.
	 * @return 4 auction added.
	 */
	public int addAuction(NPO theNPO, LocalDateTime theDate, int numItems, String theNotes) {
		int auctions = 0;

		if (theNPO.hasAuction())
			return 0; // already has an auction
		else if (!theNPO.isValidAuctionDate(theDate))
			return 1; // is outside the required timeframe for auctions
		else if (myAuctionList.size() >= maxNumberOfAuction)
			return 2; // auction central is currently full
		else {
			for (Auction a : myAuctionList) {
				if (a.getAuctionDate().toLocalDate().isEqual(theDate.toLocalDate()))
					auctions++;
			}
			if (auctions < maxAuctionsPerDay) {
				Auction newAuction = new Auction(theNPO, theDate, numItems, theNotes, nextAuctionID);
				myAuctionList.add(newAuction);
				theNPO.setAuction(true);
				theNPO.setLastAuctionDate(theDate);
				nextAuctionID++;
				return 4; // Auction scheduled
			} else
				return 3; // this day is full
		}
	}

	/**
	 * adds an auction to the calendar if the requested date is valid
	 * 
	 * @param theNPO
	 *            the NPO who request the auction
	 * @param theDate
	 *            the Date of the auction
	 * @param numItems
	 *            Number of Item
	 * @param theNotes
	 *            the description.
	 * @return true is the auction is added.
	 */
	public boolean addAuctionNoDateRestriction(NPO theNPO, LocalDateTime theDate, int numItems, String theNotes) {
		int auctions = 0;
		if (!theNPO.hasAuction() && myAuctionList.size() < 25) {
			for (Auction a : myAuctionList) {
				if (a.getAuctionDate().toLocalDate().isEqual(theDate.toLocalDate()))
					auctions++;
			}
			if (auctions < 2) {
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

	// Strictly for running testing. Allows any auction object to be added to
	// the calendar without safeguards
	public void addAuction(Auction a) {
		myAuctionList.add(a);
	}

	/**
	 * places a bid for and item as long as the bid is valid
	 * 
	 * @param theBidder
	 *            the bidder
	 * @param theItemID
	 *            the Item (number) that bidder bid on.
	 * @param theBidAmount
	 *            bid price
	 * @param theAuctionID
	 *            the auction (number) that the bidder is in.
	 * @return true if bid is made.
	 */
	public boolean requestBid(Bidder theBidder, int theItemID, double theBidAmount, int theAuctionID) {
		for (Auction a : myAuctionList) {
			if (a.getMyID() == theAuctionID) {
				if (a.getAuctionDate().isBefore(LocalDateTime.now())
						|| a.getAuctionDate().isEqual((LocalDateTime.now()))) {
					return false;
				}
				if (a.getItem(theItemID).isValidBid(theBidAmount)) {
					theBidder.placeBid(new Bid(theBidder.getMyName(), theItemID, theBidAmount, theAuctionID));
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @author aaron takes an itemID and determines if the auction has started
	 *         or is past
	 * @param itemID
	 * @return canBid true if the the auction has not already begun
	 */
	public boolean requestBid(int itemID) {
		boolean canBid = true;
		Auction auc = getAuction(itemID);
		if (auc.getAuctionDate().isBefore(LocalDateTime.now()) || auc.getAuctionDate().isEqual((LocalDateTime.now()))) {
			canBid = false;
		}
		return canBid;
	}

	/**
	 * get a list of available auctions
	 * 
	 * @return all the auctions.
	 */
	public Collection<Auction> getAllAuctions() {
		return myAuctionList;
	}

	/**
	 * get the auction associated with the NPO
	 * 
	 * @param theNPO
	 * @return the Auction associate with the NPO
	 */
	public Auction getAuction(NPO theNPO) {
		Auction auction = null;
		for (Auction a : myAuctionList) {
			if (a.getNPO().getMyUserName().equals(theNPO.getMyUserName())) {
				auction = a;
				break;
			}
		}
		return auction;
	}

	/**
	 * deletes an auction
	 * 
	 * @param NPO
	 * @return True if the auction is deleted.
	 * @author Carl Seiber
	 */
	public int deleteAuction(String NPO) {
		for (Auction a : myAuctionList) {
			if (a.getNPO().getMyName() == NPO) {
				Period days = Period.between(LocalDateTime.now().toLocalDate(), a.getAuctionDate().toLocalDate());
				int numOfDays = days.getDays();
				if (numOfDays >= 2) {
					a.getNPO().setAuction(false);
					a.getNPO().setLastAuctionDate(LocalDateTime.of(1970, 1, 1, 12, 00));
					myAuctionList.remove(a);
					return 1; // auction found and deleted successfully
				}
				return 2; // auction found but is past the deadline for auction
							// deletion
			}
		}
		return 0; // no auction was found
	}

	/**
	 * get the date of an auction
	 * 
	 * @param theDate
	 * @return the number of auction in theDate
	 */
	public int getAuctionDayCount(LocalDate theDate) {
		int count = 0;
		for (Auction a : myAuctionList) {
			if (a.getAuctionDate().toLocalDate().isEqual(theDate))
				count++;
		}
		return count;
	}

	/**
	 * @author aaron Takes an itemID and returns the Item object or null. Allows
	 *         access to Items for the Bidder class through calendar.
	 * @param itemID
	 * @return Item associated with the supplied itemID
	 * @return null if there is no item associated with the supplied itemID
	 */
	public Item getItem(int itemID) {
		for (Auction a : getAllAuctions()) {
			if (a.getItem(itemID) != null) {
				return a.getItem(itemID);
			}
		}
		return null;
	}

	public Auction getAuction(int itemID) {
		for (Auction a : getAllAuctions()) {
			if (a.getItem(itemID) != null) {
				return a;
			}
		}
		return null;
	}
//<<<<<<< HEAD

	public Auction getAuctionWithAucID(String npoName){
		Auction auction = null;
		for (Auction a : myAuctionList)	{
			if (a.getNPO().getMyName().equals(npoName)){
				auction = a;
				break;
			}
		}
		return auction;
	}

//=======
//
//>>>>>>> refs/heads/PatrickBranch
}
