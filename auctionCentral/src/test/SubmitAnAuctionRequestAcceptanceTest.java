package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import GUI.NPOGUI;
import model.Bidder;
import model.AuctionCalendar;
import model.NPO;
import model.User;

public class SubmitAnAuctionRequestAcceptanceTest {
	
	//Since 2016 was a leap year I have to minus one extra day from all tests regarding timeframes around the one year mark
	//to confirm accurate results (ie one year would be minus(366) vs (365)
	
	//The addAuction method returns the following values:
	 //  0 - already has auction.
	 //  1 - is outside the timeframe allowed.
	 //  2 - auction central is full.
	 //  3 - this day is full.
	 //  4 - auction added.

	AuctionCalendar theCalendar;
	NPO theNPO;
	LocalDateTime date;
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new AuctionCalendar();
		date = LocalDateTime.now().plusDays(10);
		theNPO = new NPO("theNPO", "NPO");
	}
	
 //*********************************************************************************************************
	//Business rule: only the contact person for this non-profit organization can submit an auction request.
		//This is handled within the UI by passing the reference to the current NPO to the auction Request
 //*********************************************************************************************************	
	

 //*********************************************************************************************************
	//Business rule: Maximum of one future auction for this non-profit
 //*********************************************************************************************************
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnNoAuctionsInFuture() {
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnOneAuctionInFuture() {
		theCalendar.addAuction(theNPO, date.plusDays(5), 0, "");
		assertEquals(0, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void maxOneFutureAuctionOnMoreThanOneAuctionInFuture() {
		theCalendar.addAuction(theNPO, date.plusDays(5), 0, "");
		theCalendar.addAuction(theNPO, date.plusDays(6), 0, "");
		assertEquals(0, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
 //*********************************************************************************************************
	//Business rule: No auctions within the past year for this non-profit
 //*********************************************************************************************************
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnOnePastAuctionLessThanOneYearAndOneDayAgo() {
		//the new auction will be scheduled for 10 days in the future so I add the 10 days before I begin to minus days for clarity
		LocalDateTime theDate = LocalDateTime.now().plusDays(10).minusDays(360);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theNPO.setAuction(false);//set this false so that we know that test has past because of the date and 
								//NOT because it shows the NPO has an auction due to us adding it for testing
		assertEquals(1, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnOnePastAuctionExactlyOneYearAndOneDayAgo() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(10).minusDays(365);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theNPO.setAuction(false);//set this false so that we know that test has past because of the date and 
								//NOT because it shows the NPO has an auction due to us adding it for testing
		assertEquals(1, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnMultiplePastAuctionLessThanOneYearAgo() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(10).minusDays(360);
		LocalDateTime theNextDate = LocalDateTime.now().plusDays(10).minusDays(350);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theCalendar.addAuctionNoDateRestriction(theNPO, theNextDate, 0, "");
		theNPO.setAuction(false);//set this false so that we know that test has past because of the date and 
								//NOT because it shows the NPO has an auction due to us adding it for testing
		assertEquals(1, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnOnePastAuctionMoreThanOneYearAgo() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(10).minusDays(400);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theNPO.setAuction(false);//set this false so that we know that test has past because of the date and 
								//NOT because it shows the NPO has an auction due to us adding it for testing
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnOnePastAuctionExactlyOneYearAgo() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(10).minusDays(366);
		theCalendar.addAuctionNoDateRestriction(theNPO, theDate, 0, "");
		theNPO.setAuction(false);//set this false so that we know that test has past because of the date and 
								//NOT because it shows the NPO has an auction due to us adding it for testing
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void NoNewAuctionsWithinOneYearofLastAuctionOnNoPastAuctions() {
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	

  //*********************************************************************************************************
	//Business rule: Maximum of two auctions per day
  //*********************************************************************************************************
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwoAuctionInOneDayOnNoAuctionsScheduledForThatDay() {

		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwoAuctionInOneDayOnOneAuctionScheduledForThatDay() {
		NPO theNPO2 = new NPO("NPO2", "npo");
		theCalendar.addAuction(theNPO2, date, 0, "");
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwoAuctionInOneDayOnTwoAuctionScheduledForThatDay() {
		NPO theNPO2 = new NPO("NPO2", "npo");
		NPO theNPO3 = new NPO("NPO3", "npo");
		theCalendar.addAuction(theNPO2, date, 0, "");
		theCalendar.addAuction(theNPO3, date, 0, "");
		assertEquals(3, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwoAuctionInOneDayOnMoreThanTwoAuctionScheduledForThatDay() {
		NPO theNPO2 = new NPO("NPO2", "npo");
		NPO theNPO3 = new NPO("NPO3", "npo");
		NPO theNPO4 = new NPO("NPO4", "npo");
		theCalendar.addAuction(theNPO2, date, 0, "");
		theCalendar.addAuction(theNPO3, date, 0, "");
		theCalendar.addAuction(theNPO4, date, 0, "");
		assertEquals(3, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
  //*********************************************************************************************************
	//Business rule: Maximum of twenty-five upcoming auctions in the system
  //*********************************************************************************************************
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwentyFiveAuctionsOnLessThanTwentyFourAuctionsScheduled() {
		ArrayList<User> userList = new ArrayList();
		
		for (int i = 0; i < 20; i++)
		{
			char ch;
			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
		}
		
		int count = LocalDateTime.now().plusDays(7).getDayOfMonth();
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				theCalendar.addAuction((NPO) u, LocalDateTime.of(year, month, count, 12, 00), 0, "");
				if (count >= 31)
				{
					count = 0;
					month = 1;
					year = LocalDateTime.now().plusYears(1).getYear();
				}
				count++;
			}
		}
		assertEquals(4, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwentyFiveAuctionsOnExactlyTwentyFourAuctionsScheduled() {
		ArrayList<User> userList = new ArrayList();
		
		for (int i = 0; i < 24; i++)
		{
			char ch;
			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
		}
		
		int count = LocalDateTime.now().plusDays(7).getDayOfMonth();
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				theCalendar.addAuction((NPO) u, LocalDateTime.of(year, month, count, 12, 00), 0, "");
				if (count >= 31)
				{
					count = 0;
					month = 1;
					year = LocalDateTime.now().plusYears(1).getYear();
				} else if (count <= LocalDateTime.now().minusDays(1).getDayOfMonth())
				{
					count = LocalDateTime.now().plusDays(7).getDayOfMonth();
					month = LocalDateTime.now().getMonthValue();;
					year = LocalDateTime.now().getYear();
				}
				count++;
			}
		}
		
		assertEquals(4, theCalendar.addAuction(theNPO, date.plusDays(10), 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwentyFiveAuctionsOnExactlyTwentyFiveAuctionsScheduled() {
		ArrayList<User> userList = new ArrayList();
		
		for (int i = 0; i < 25; i++)
		{
			char ch;
			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
		}
		
		int count = LocalDateTime.now().plusDays(7).getDayOfMonth();
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				theCalendar.addAuction((NPO) u, LocalDateTime.of(year, month, count, 12, 00), 0, "");
				if (count >= 31)
				{
					count = 0;
					month = 1;
					year = LocalDateTime.now().plusYears(1).getYear();
				} else if (count <= LocalDateTime.now().minusDays(1).getDayOfMonth())
				{
					count = LocalDateTime.now().plusDays(7).getDayOfMonth();
					month = LocalDateTime.now().getMonthValue();;
					year = LocalDateTime.now().getYear();
				}
				count++;
			}
		}
		
		assertEquals(2, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void MaxTwentyFiveAuctionsOnMoreThanTwentyFiveAuctionsScheduled() {
		ArrayList<User> userList = new ArrayList();
		
		for (int i = 0; i < 28; i++)
		{
			char ch;
			ch = (char) ((i) + 'a');
			userList.add(new NPO("NPO" + ch, Character.toString(ch)));
		}
		
		int count = LocalDateTime.now().plusDays(7).getDayOfMonth();
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		for ( User u : userList)
		{
			if (u.getUserType().equals("NPO"))
			{
				theCalendar.addAuction((NPO) u, LocalDateTime.of(year, month, count, 12, 00), 0, "");
				if (count >= 31)
				{
					count = 0;
					month = 1;
					year = LocalDateTime.now().plusYears(1).getYear();
				} else if (count <= LocalDateTime.now().minusDays(1).getDayOfMonth())
				{
					count = LocalDateTime.now().plusDays(7).getDayOfMonth();
					month = LocalDateTime.now().getMonthValue();;
					year = LocalDateTime.now().getYear();
				}
				count++;
			}
		}
		
		assertEquals(2, theCalendar.addAuction(theNPO, date, 0, ""));
	}
	
	
  //*********************************************************************************************************
	//Business rule: Auction cannot be scheduled for more than one month into the future
  //*********************************************************************************************************
	
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionNoMoreThanOneMonthOutOnExactlyOneMonthInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusMonths(1);
		assertEquals(4, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionNoMoreThanOneMonthOutOnLessThanOneMonthInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(22);
		assertEquals(4, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionNoMoreThanOneMonthOutOnExactlyOneMonthAndOneDayInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusMonths(1).plusDays(1);
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionNoMoreThanOneMonthOutOnMoreThanOneMonthAndOneDayInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusMonths(1).plusDays(5);
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	
  //*********************************************************************************************************
	//Business rule: all required fields must be specified at the time an auction is submitted. 
	//				 These fields are: auction date (formatted date: DD/MM/YYYY), auction time 
	//				 (formatted time: HH [AM/PM]). Optional (non-required) fields are: approximate 
	//				 number of inventory items (positive integer), comment (string).
	
	//This is handled in the UI by only enabling the submit auction button when a valid auction
	//request is detected
  //*********************************************************************************************************
	
	
  //*********************************************************************************************************
	//Business rule: auction date is at least one week from the day in which it is being added.
  //*********************************************************************************************************
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnExactlyOneWeekInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(7);
		assertEquals(4, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnGreaterThanOneWeekInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(10);
		assertEquals(4, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnExactlySixDaysInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(6);
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnLessThanSixDaysInFuture() {
		LocalDateTime theDate = LocalDateTime.now().plusDays(2);
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnSameDayasToday() {
		LocalDateTime theDate = LocalDateTime.now();
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(java.lang.String)}.
	 */
	@Test
	public void AuctionAtLeastOneWeekOutOnDateInPast() {
		LocalDateTime theDate = LocalDateTime.now().minusDays(10);
		assertEquals(1, theCalendar.addAuction(theNPO, theDate, 0, ""));
	}
}
