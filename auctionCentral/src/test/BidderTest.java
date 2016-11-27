package test;

import static org.junit.Assert.*;

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

public class BidderTest {
	
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
	
	//TODO create automated ATs

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

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidForDuplicateBid() {
			
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == 3);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidForNonduplicateBid() {
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidOfZeroDollars() {
		assertTrue(theBidder.addBid(myCalendar,itemA, 0) == 2);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidNegativeDollars() {
		assertTrue(theBidder.addBid(myCalendar,itemA, -5) == 2);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidAfterAuctionHasBegun() {
		theAuction.setAuctionDate(LocalDateTime.now());
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == 4);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidAfterAuctionHasCompleted() {
		theAuction.setAuctionDate(LocalDateTime.now().minusDays(1));
		assertTrue(theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid()) == 4);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.placeBid(newBid)}.
	 */
	@Test
	public void testPlaceBidForNonNullBid() {
		assertTrue(theBidder.placeBid(bidA));
	}
	
	
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestOneDayBeforeAuction() {
		
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(1));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 2);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestThreeDaysBeforeAuction() {
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(3));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestTwoDaysBeforeAuction() {
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(2));
		assertTrue(theBidder.bidRemovalRequest(itemA.getMyItemID(), myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestZeroDaysBeforeAuction() {
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now());
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 2);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestTenDaysBeforeAuction() {
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		theAuction.setAuctionDate(LocalDateTime.now().plusDays(10));
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.bidRemovalRequest(itemID, myCalendar)}.
	 */
	@Test
	public void testBidRemovalRequestBidDoesNotExist() {
		assertTrue(theBidder.bidRemovalRequest(bidA.getMyItemID(), myCalendar) == 3);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.removeTheBid(itemID)}.
	 */
	@Test
	public void testRemoveTheBidForExistingBid() {
		theBidder.addBid(myCalendar,itemA, itemA.getMyMinBid());
		assertTrue(theBidder.removeTheBid(bidA.getMyItemID()));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.removeTheBid(itemID)}.
	 */
	@Test
	public void testRemoveTheBidForNonExistingBid() {
		assertFalse(theBidder.removeTheBid(bidA.getMyItemID()));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.placeBid(newBid)}.
	 */
	@Test
	public void testPlaceBidForNullBid() {
		assertFalse(theBidder.placeBid(bidNull));
	}

}
