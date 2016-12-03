package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import model.Calendar;
import model.NPO;

public class CancelAnAuctionRequestAcceptanceTest {

	Calendar theCalendar;
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new Calendar();
	}

	
	/**
	 * Test method for {@link model.Calendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnDateExactlyTwoDaysInFuture() {
		LocalDateTime date = LocalDateTime.now().plusDays(2);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuctionNoDateRestriction(theNPO, date, 0, "");
		assertEquals(1, theCalendar.deleteAuction(theNPO.getMyName()));
	}
	
	/**
	 * Test method for {@link model.Calendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnDateLessThanTwoDaysInFuture() {
		LocalDateTime date = LocalDateTime.now().plusDays(1);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuctionNoDateRestriction(theNPO, date, 0, "");
		assertEquals(2, theCalendar.deleteAuction(theNPO.getMyName()));
	}
	
	/**
	 * Test method for {@link model.Calendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnDateMoreThanTwoDaysInFuture() {
		LocalDateTime date = LocalDateTime.now().plusDays(5);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuctionNoDateRestriction(theNPO, date, 0, "");
		assertEquals(1, theCalendar.deleteAuction(theNPO.getMyName()));
	}
	
	/**
	 * Test method for {@link model.Calendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnNoAuctions() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertEquals(0, theCalendar.deleteAuction(theNPO.getMyName()));
	}
	
	/**
	 * Test method for {@link model.Calendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnMultipleAuctions() {
		LocalDateTime date = LocalDateTime.now().plusDays(5);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuctionNoDateRestriction(theNPO, date, 0, "");
		
		//since another business rule prevents adding multiple auctions per NPO this is all that is needed
		assertEquals(false, theCalendar.addAuctionNoDateRestriction(theNPO, date.plusDays(2), 0, ""));
	}


}
