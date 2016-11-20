package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Bid;
import model.Bidder;
import model.NPO;

public class BidderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Bidder#theBidder.addBid(theBid)}.
	 */
	@Test
	public void testAddBidWhenAlreadyHasBid() {
		Bid theBid = new Bid("theBidderName", 1234,  100.00, 4321);
		//Bid theSecondBid = new Bid("theBidderName", 456,  200.00, 7894);
		Bidder theBidder = new Bidder("TheBidder", "Bidder", "theAddress", "thePhone", "theEmail", "thePaymentInfo");
		theBidder.addBid(theBid);
		assertFalse(theBidder.addBid(theBid));
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

}
