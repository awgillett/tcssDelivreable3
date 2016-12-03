package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import GUI.NPOGUI;
import model.Calendar;
import model.NPO;

public class SubmitAnAuctionRequestAcceptanceTest {
	
	//The addAuction method returns the following values:
	 //  0 - already has auction.
	 //  1 - is outside the timeframe allowed.
	 //  2 - auction central is full.
	 //  3 - this day is full.
	 //  4 - auction added.

	Calendar theCalendar;
	NPO theNPO;
	LocalDateTime date;
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new Calendar();
		date = LocalDateTime.now().plusDays(10);
		theNPO = new NPO("theNPO", "NPO");
	}
	
	//Business rule: only the contact person for this non-profit organization can submit an auction request.
		//This is handled within the UI by passing the reference to the current NPO to the auction Request
	
	
	
	//Business rule: Maximum of one future auction for this non-profit
	
	/**
	 * Test method for {@link model.Calendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnNoAuctionsInFuture() {
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
	/**
	 * Test method for {@link model.Calendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnOneAuctionInFuture() {
		theCalendar.addAuction(theNPO, date.plusDays(5), 0, "");
		assertEquals(0, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.Calendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnMoreThanOneAuctionInFuture() {
		theCalendar.addAuction(theNPO, date.plusDays(5), 0, "");
		theCalendar.addAuction(theNPO, date.plusDays(6), 0, "");
		assertEquals(0, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
	//Business rule: No auctions within the past year for this non-profit
	
	/**
	 * Test method for {@link model.Calendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnOnePastAuctionLessThanOneYearAndOneDayAgo() {
		LocalDateTime theDate = LocalDateTime.now().minusDays(360);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theNPO.setAuction(false);//
		assertEquals(0, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	public void test() {
		
		
	}
	
	
	
	
	

}
