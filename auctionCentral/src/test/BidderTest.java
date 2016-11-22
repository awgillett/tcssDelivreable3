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
	
	Calendar myCalendar;
	NPO a;
	NPO b;
	NPO c;

	@Before
	public void setUp() throws Exception {
		
		myCalendar = new Calendar();
		a = new NPO("NPOa", "a");
		b = new NPO("NPOb", "b");
		c = new NPO("NPOc", "c");
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidWhenAlreadyHasBid() {
		Bid theBid = new Bid("theBidderName", 1234,  100.00, 7894);
		Bid theSecondBid = new Bid("theBidderName", 456,  200.00, 7894);
		Bidder theBidder = new Bidder("theBidderName", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		theBidder.addBid(theBid);
		assertFalse(theBidder.addBid(theSecondBid));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidWhenNoBidYet() {
		Bid theBid = new Bid("theBidderName", 1234,  100.00, 4321);
		Bidder theBidder = new Bidder("TheBidder", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		assertTrue(theBidder.addBid(theBid));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidZeroDollars() {
		Bid theBid = new Bid("theBidderName", 1234,  0.00, 4321);
		Bidder theBidder = new Bidder("TheBidder", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		assertFalse(theBidder.addBid(theBid));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidNegativeDollars() {
		Bid theBid = new Bid("theBidderName", 1234,  -1.00, 4321);
		Bidder theBidder = new Bidder("TheBidder", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		assertFalse(theBidder.addBid(theBid));
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testBidRemovalRequestOneDayBeforeAuction() {
		
		Bidder theBidder = new Bidder("bidder", "Bidder", "address", "phone", "email", "thePaymentInfo");
		Auction theAuction = new Auction(a, LocalDateTime.now().plusDays(1), 1, "None", 123456);
		theAuction.addItem("thing", "donor", "good", "small", "you don't want this", "missing one bite", 1000);
		myCalendar.addAuction(theAuction);
		int itemID = 0;
		for(Item item : theAuction.getItemList()){
			itemID = item.getMyItemID();
		}
		Bid theBid = new Bid("theBidderName", itemID,  1000, 123456);
		theBidder.placeBid(theBid);
		assertFalse(theBidder.bidRemovalRequest(itemID, myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testBidRemovalRequestThreeDaysBeforeAuction() {
		
		Bidder theBidder = new Bidder("bidder", "Bidder", "address", "phone", "email", "thePaymentInfo");
		Auction theAuction = new Auction(a, LocalDateTime.now().plusDays(3), 1, "None", 123456);
		theAuction.addItem("thing", "donor", "good", "small", "you don't want this", "missing one bite", 1000);
		myCalendar.addAuction(theAuction);
		int itemID = 0;
		for(Item item : theAuction.getItemList()){
			itemID = item.getMyItemID();
		}
		Bid theBid = new Bid("theBidderName", itemID,  1000, 123456);
		theBidder.placeBid(theBid);
		assertTrue(theBidder.bidRemovalRequest(itemID, myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testBidRemovalRequestTwoDaysBeforeAuction() {
		
		Bidder theBidder = new Bidder("bidder", "Bidder", "address", "phone", "email", "thePaymentInfo");
		Auction theAuction = new Auction(a, LocalDateTime.now().plusDays(2), 1, "None", 123456);
		theAuction.addItem("thing", "donor", "good", "small", "you don't want this", "missing one bite", 1000);
		myCalendar.addAuction(theAuction);
		int itemID = 0;
		for(Item item : theAuction.getItemList()){
			itemID = item.getMyItemID();
		}
		Bid theBid = new Bid("theBidderName", itemID,  1000, 123456);
		theBidder.placeBid(theBid);
		assertTrue(theBidder.bidRemovalRequest(itemID, myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testBidRemovalRequestZeroDaysBeforeAuction() {
		
		Bidder theBidder = new Bidder("bidder", "Bidder", "address", "phone", "email", "thePaymentInfo");
		Auction theAuction = new Auction(a, LocalDateTime.now(), 1, "None", 123456);
		theAuction.addItem("thing", "donor", "good", "small", "you don't want this", "missing one bite", 1000);
		myCalendar.addAuction(theAuction);
		int itemID = 0;
		for(Item item : theAuction.getItemList()){
			itemID = item.getMyItemID();
		}
		Bid theBid = new Bid("theBidderName", itemID,  1000, 123456);
		theBidder.placeBid(theBid);
		assertFalse(theBidder.bidRemovalRequest(itemID, myCalendar) == 1);
	}
	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testBidRemovalRequestTenDaysBeforeAuction() {
		
		Bidder theBidder = new Bidder("bidder", "Bidder", "address", "phone", "email", "thePaymentInfo");
		Auction theAuction = new Auction(a, LocalDateTime.now().plusDays(10), 1, "None", 123456);
		theAuction.addItem("thing", "donor", "good", "small", "you don't want this", "missing one bite", 1000);
		myCalendar.addAuction(theAuction);
		int itemID = 0;
		for(Item item : theAuction.getItemList()){
			itemID = item.getMyItemID();
		}
		Bid theBid = new Bid("theBidderName", itemID,  1000, 123456);
		theBidder.placeBid(theBid);
		assertTrue(theBidder.bidRemovalRequest(itemID, myCalendar) == 1);
	}
}
