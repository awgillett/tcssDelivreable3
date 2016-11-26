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
import model.Calendar;
import model.Item;
import model.NPO;

public class BusinessRulesForBidder {
	
	Bidder theBidder;
	Auction theAuction;
	Calendar myCalendar;
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
		b = new NPO("NPOb", "b");
		c = new NPO("NPOc", "c");
		
		theBidder = new Bidder("theBidderName", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		theAuction = new Auction(a, LocalDateTime.now().plusDays(5), 1, "None", 1);
		
		myCalendar = new Calendar();	
		itemA = new Item("itemA", "NPOA", "good", "small", "NOTE", "DESC", 10., 123);
		itemB = new Item("itemB", "good", "small", 10.);
		
		myCalendar.addAuction(theAuction);
		theAuction.addItem(itemA);
		
		bidA = theBidder.createBid(itemA.getMyMinBid(), itemA, myCalendar);
		bidNull = null;
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
		//tests for bid removal request
		//test bid removal request when there is no bid
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 3);
		//test bid removal request day of auction
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now());
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 2);
		//test bid removal request 1 day before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(1));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 2);
		//test bid removal request 2 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(2));
		assertTrue(theBidder.bidRemovalRequest(itemA.getMyItemID(), myCalendar) == 1);
		//test bid removal request 3 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(3));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 1);
		//test bid removal request 10 days before auction
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(10));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 1);

		//tests for actually removing the bid
		//test that bid is removed
		assertTrue(theBidder.getMyBids().size() == 1);
		assertTrue(theBidder.removeTheBid(bidA.getMyItemID()));
		assertTrue(theBidder.getMyBids().size() == 0);
		assertFalse(theBidder.removeTheBid(bidA.getMyItemID()));
	}
}
