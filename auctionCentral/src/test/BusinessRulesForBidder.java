package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.Auction;
import model.Bid;
import model.Bidder;
import model.AuctionCalendar;
import model.Item;
import model.NPO;

public class BusinessRulesForBidder {
	
	Bidder theBidder;
	Auction theAuction;
	AuctionCalendar myCalendar;
	Item itemA;
	Item itemB;
	Bid bidA;
	Bid bidNull;
	NPO a;
	NPO b;
	NPO c;
	
	@Before
	public void setUp() throws Exception {
		
		a = new NPO("NPOa", "a");
		
		theBidder = new Bidder("theBidderName", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		theAuction = new Auction(a, LocalDateTime.now().plusDays(5), 1, "None", 1);
		
		myCalendar = new AuctionCalendar();	
		itemA = new Item("itemA", "NPOA", "good", "small", "NOTE", "DESC", 10., 123);
		
		myCalendar.addAuction(theAuction);
		theAuction.addItem(itemA);
		bidA = theBidder.createBid(itemA.getMyMinBid(), itemA, myCalendar);
	}
	/**
	 * @author aaron
	 * bidRemovalRequest() checks to make sure the bid can be removed and returns:
	 * 		3 if the bidder has no bid for the item
	 * 		2 if the bidder has a bid but it may not be removed do to the date of the auction being to close
	 * 		1 if the bidder has a bid and it may be removed
	 * removeTheBid() actually removes the bid from the bidders list of bids and returns:
	 *  	true if the bid was removed
	 * 		false if the bid was not found... 
	 */
	@Test
	public void testAsABidderIWantToCancelABid() {
		
		int bidFoundAndRemovable = 1;
		int bidFoundButNotRemovable = 2;
		int bidNotFound = 3;
		
		//tests for bidRemovalRequest()
		//test bid removal request when there is no bid
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == bidNotFound);
		//test bid removal request day of auction
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now());
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == bidFoundButNotRemovable);
		//test bid removal request 1 day before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(1));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == bidFoundButNotRemovable);
		//test bid removal request 2 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(2));
		assertTrue(theBidder.bidRemovalRequest(itemA.getMyItemID(), myCalendar) == bidFoundAndRemovable);
		//test bid removal request 3 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(3));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == bidFoundAndRemovable);
		//test bid removal request 10 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(10));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == bidFoundAndRemovable);

		//tests for removeTheBid()
		//test that bid is removed
		assertTrue(theBidder.getMyBids().size() == 1);
		assertTrue(theBidder.removeTheBid(bidA.getMyItemID()));
		assertTrue(theBidder.getMyBids().size() == 0);
		assertFalse(theBidder.removeTheBid(bidA.getMyItemID()));
	}
	/**
	 * @author aaron
	 * getMyBids().size() returns the number current bids for a bidder
	 * addBid() adds a bid to the bidders list of bids and returns:
	 * 		4 if the bid may not be added because the auction is the same day or has passed
	 * 		3 if the bidder already has a bid for the item
	 * 		2 if the bid amount is below the minimum bid
	 * 		1 if the bid is accepted and added to the bidders list of bids
	 */
	@Test
	public void testAsABidderIWantToAddABid() {
		
		int bidAccepted = 1;
		int bidBelowMinBid = 2;
		int bidForItemAlreadyExists = 3;
		int auctionHasStarted = 4;
		
		//tests adding a bid for the items minimum bid amount
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == bidAccepted);
		assertTrue(theBidder.getMyBids().size() == 1);
		//tests adding a second bid of the minimum bid amount for an item already bid on
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == bidForItemAlreadyExists);
		assertTrue(theBidder.getMyBids().size() == 1);
		//tests adding a second bid of more than the minimum bid amount for an item already bid on
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid() + 1) == bidForItemAlreadyExists);
		assertTrue(theBidder.getMyBids().size() == 1);
		//tests adding a bid for $.01 more than the minimum bid
		theBidder.removeTheBid(bidA.getMyItemID());
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()+.01) == bidAccepted);
		assertTrue(theBidder.getMyBids().size() == 1);
		//tests adding a bid for $1000.00 more than the minimum bid
		theBidder.removeTheBid(bidA.getMyItemID());
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()+1000) == bidAccepted);
		assertTrue(theBidder.getMyBids().size() == 1);
		theBidder.removeTheBid(bidA.getMyItemID());
		//tests adding a bid for $.01 less than the minimum bid
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()-.01) == bidBelowMinBid);
		assertTrue(theBidder.getMyBids().size() == 0);		
		//tests adding a bid for $.01
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, .01) == bidBelowMinBid);
		assertTrue(theBidder.getMyBids().size() == 0);	
		//tests adding a bid for of $0.00
		assertTrue(theBidder.getMyBids().size() == 0);
		assertTrue(theBidder.addBid(myCalendar,itemA, 0) == bidBelowMinBid);
		assertTrue(theBidder.getMyBids().size() == 0);
		//tests adding a bid for of -$.01
		assertTrue(theBidder.addBid(myCalendar,itemA, -.01) == bidBelowMinBid);
		assertTrue(theBidder.getMyBids().size() == 0);
		//tests adding a bid for of -$1000.00
		assertTrue(theBidder.addBid(myCalendar,itemA, -1000) == bidBelowMinBid);
		assertTrue(theBidder.getMyBids().size() == 0);
		//tests for adding a bid the same day as the auction
		theAuction.setAuctionDate(LocalDateTime.now());
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == auctionHasStarted);
		assertTrue(theBidder.getMyBids().size() == 0);
		//tests for adding a bid 14 days after the auction
		theAuction.setAuctionDate(LocalDateTime.now().minusDays(14));
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == auctionHasStarted);
		assertTrue(theBidder.getMyBids().size() == 0);
		//tests for adding a bid 1 day before the auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(1));
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == bidAccepted);
		assertTrue(theBidder.getMyBids().size() == 1);
	}
}
