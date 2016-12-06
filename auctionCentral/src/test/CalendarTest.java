/**
 *   
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCalendar;
import model.NPO;

/**
 * @author Carl,
 * @author Phuc Tran worked on isValidMaxNumberOfAuction Test
 *
 */
public class CalendarTest {

	AuctionCalendar myCalendar;
	NPO a;
	NPO b;
	NPO c;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myCalendar = new AuctionCalendar();
		a = new NPO("NPOa", "a");
		b = new NPO("NPOb", "b");
		c = new NPO("NPOc", "c");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(model.Auction)}.
	 */
	@Test
	public void testAddAuctionOnAlreadyTwoScheduledThatDay() {	
		myCalendar.addAuction(a, LocalDateTime.now().plusDays(7), 10, "");
		myCalendar.addAuction(b, LocalDateTime.now().plusDays(7), 10, "");
		//assertFalse(myCalendar.addAuction(c, LocalDateTime.now().plusDays(7), 10, ""));
		assertEquals(myCalendar.addAuction(c, LocalDateTime.now().plusDays(7), 10, ""), 3);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(model.Auction)}.
	 */
	@Test
	public void testAddAuctionOnOneScheduledThatDay() {	
		myCalendar.addAuction(a, LocalDateTime.now().plusDays(7), 10, "");
		assertEquals(myCalendar.addAuction(c, LocalDateTime.now().plusDays(7), 10, ""), 4);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(model.Auction)}.
	 */
	@Test
	public void testAddAuctionOnAlreadyTwentyFiveScheduled() {		
			NPO npo = new NPO("NPO", "g");
			NPO npo2 = new NPO("NPO2", "g2");
			NPO npo3 = new NPO("NPO3", "g3");
			NPO npo4 = new NPO("NPO4", "g4");
			NPO npo5 = new NPO("NPO5", "g5");
			NPO npo6 = new NPO("NPO6", "g6");
			NPO npo7 = new NPO("NPO7", "g7");
			NPO npo8 = new NPO("NPO8", "g8");
			NPO npo9 = new NPO("NPO9", "g9");
			NPO npo10 = new NPO("NPO10", "g10");
			NPO npo11 = new NPO("NPO11", "g11");
			NPO npo12 = new NPO("NPO12", "g12");
			NPO npo13 = new NPO("NPO13", "g13");
			NPO npo14 = new NPO("NPO14", "g14");
			NPO npo15 = new NPO("NPO15", "g15");
			NPO npo16 = new NPO("NPO16", "g16");
			NPO npo17 = new NPO("NPO17", "g17");
			NPO npo18 = new NPO("NPO18", "g18");
			NPO npo19 = new NPO("NPO19", "g19");
			NPO npo20 = new NPO("NPO20", "g20");
			NPO npo21 = new NPO("NPO21", "g21");
			NPO npo22 = new NPO("NPO22", "g22");
			NPO npo23 = new NPO("NPO23", "g23");
			NPO npo24 = new NPO("NPO24", "g24");
			NPO npo25 = new NPO("NPO25", "g25");
			myCalendar.addAuction(npo, LocalDateTime.now().plusDays(7), 10, "");
			myCalendar.addAuction(npo2, LocalDateTime.now().plusDays(7), 10, "");
			myCalendar.addAuction(npo3, LocalDateTime.now().plusDays(8), 10, "");
			myCalendar.addAuction(npo4, LocalDateTime.now().plusDays(8), 10, "");
			myCalendar.addAuction(npo5, LocalDateTime.now().plusDays(9), 10, "");
			myCalendar.addAuction(npo6, LocalDateTime.now().plusDays(9), 10, "");
			myCalendar.addAuction(npo7, LocalDateTime.now().plusDays(10), 10, "");
			myCalendar.addAuction(npo8, LocalDateTime.now().plusDays(10), 10, "");
			myCalendar.addAuction(npo9, LocalDateTime.now().plusDays(11), 10, "");
			myCalendar.addAuction(npo10, LocalDateTime.now().plusDays(11), 10, "");
			myCalendar.addAuction(npo11, LocalDateTime.now().plusDays(12), 10, "");
			myCalendar.addAuction(npo12, LocalDateTime.now().plusDays(12), 10, "");
			myCalendar.addAuction(npo13, LocalDateTime.now().plusDays(13), 10, "");
			myCalendar.addAuction(npo14, LocalDateTime.now().plusDays(13), 10, "");
			myCalendar.addAuction(npo15, LocalDateTime.now().plusDays(14), 10, "");
			myCalendar.addAuction(npo16, LocalDateTime.now().plusDays(14), 10, "");
			myCalendar.addAuction(npo17, LocalDateTime.now().plusDays(15), 10, "");
			myCalendar.addAuction(npo18, LocalDateTime.now().plusDays(16), 10, "");
			myCalendar.addAuction(npo19, LocalDateTime.now().plusDays(16), 10, "");
			myCalendar.addAuction(npo20, LocalDateTime.now().plusDays(17), 10, "");
			myCalendar.addAuction(npo21, LocalDateTime.now().plusDays(17), 10, "");
			myCalendar.addAuction(npo22, LocalDateTime.now().plusDays(18), 10, "");
			myCalendar.addAuction(npo23, LocalDateTime.now().plusDays(18), 10, "");
			myCalendar.addAuction(npo24, LocalDateTime.now().plusDays(19), 10, "");
			myCalendar.addAuction(npo25, LocalDateTime.now().plusDays(19), 10, "");
		assertEquals(myCalendar.addAuction(c, LocalDateTime.now().plusDays(20), 10, ""), 2);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#addAuction(model.Auction)}.
	 */
	@Test
	public void testAddAuctionOnAddingWhenTwentyFourScheduled() {
		NPO npo = new NPO("NPO", "g");
		NPO npo2 = new NPO("NPO2", "g2");
		NPO npo3 = new NPO("NPO3", "g3");
		NPO npo4 = new NPO("NPO4", "g4");
		NPO npo5 = new NPO("NPO5", "g5");
		NPO npo6 = new NPO("NPO6", "g6");
		NPO npo7 = new NPO("NPO7", "g7");
		NPO npo8 = new NPO("NPO8", "g8");
		NPO npo9 = new NPO("NPO9", "g9");
		NPO npo10 = new NPO("NPO10", "g10");
		NPO npo11 = new NPO("NPO11", "g11");
		NPO npo12 = new NPO("NPO12", "g12");
		NPO npo13 = new NPO("NPO13", "g13");
		NPO npo14 = new NPO("NPO14", "g14");
		NPO npo15 = new NPO("NPO15", "g15");
		NPO npo16 = new NPO("NPO16", "g16");
		NPO npo17 = new NPO("NPO17", "g17");
		NPO npo18 = new NPO("NPO18", "g18");
		NPO npo19 = new NPO("NPO19", "g19");
		NPO npo20 = new NPO("NPO20", "g20");
		NPO npo21 = new NPO("NPO21", "g21");
		NPO npo22 = new NPO("NPO22", "g22");
		NPO npo23 = new NPO("NPO23", "g23");
		NPO npo24 = new NPO("NPO24", "g24");
		NPO npo25 = new NPO("NPO25", "g25");
		myCalendar.addAuction(npo, LocalDateTime.now().plusDays(7), 10, "");
		myCalendar.addAuction(npo2, LocalDateTime.now().plusDays(7), 10, "");
		myCalendar.addAuction(npo3, LocalDateTime.now().plusDays(8), 10, "");
		myCalendar.addAuction(npo4, LocalDateTime.now().plusDays(8), 10, "");
		myCalendar.addAuction(npo5, LocalDateTime.now().plusDays(9), 10, "");
		myCalendar.addAuction(npo6, LocalDateTime.now().plusDays(9), 10, "");
		myCalendar.addAuction(npo7, LocalDateTime.now().plusDays(10), 10, "");
		myCalendar.addAuction(npo8, LocalDateTime.now().plusDays(10), 10, "");
		myCalendar.addAuction(npo9, LocalDateTime.now().plusDays(11), 10, "");
		myCalendar.addAuction(npo10, LocalDateTime.now().plusDays(11), 10, "");
		myCalendar.addAuction(npo11, LocalDateTime.now().plusDays(12), 10, "");
		myCalendar.addAuction(npo12, LocalDateTime.now().plusDays(12), 10, "");
		myCalendar.addAuction(npo13, LocalDateTime.now().plusDays(13), 10, "");
		myCalendar.addAuction(npo14, LocalDateTime.now().plusDays(13), 10, "");
		myCalendar.addAuction(npo15, LocalDateTime.now().plusDays(14), 10, "");
		myCalendar.addAuction(npo16, LocalDateTime.now().plusDays(14), 10, "");
		myCalendar.addAuction(npo17, LocalDateTime.now().plusDays(15), 10, "");
		myCalendar.addAuction(npo18, LocalDateTime.now().plusDays(16), 10, "");
		myCalendar.addAuction(npo19, LocalDateTime.now().plusDays(16), 10, "");
		myCalendar.addAuction(npo20, LocalDateTime.now().plusDays(17), 10, "");
		myCalendar.addAuction(npo21, LocalDateTime.now().plusDays(17), 10, "");
		myCalendar.addAuction(npo22, LocalDateTime.now().plusDays(18), 10, "");
		myCalendar.addAuction(npo23, LocalDateTime.now().plusDays(18), 10, "");
		myCalendar.addAuction(npo24, LocalDateTime.now().plusDays(19), 10, "");
		assertEquals(myCalendar.addAuction(npo25, LocalDateTime.now().plusDays(19), 10, ""), 4);
	}
	

	
	@Test
	public void testIsValidMaxNumberOfAuctionOnNegativeValue(){
		assertFalse(myCalendar.isValidMaxNumberOfAuction(-1));
	}
	
	@Test
	public void testIsValidMaxNumberOfAuctionOnZero(){
		assertFalse(myCalendar.isValidMaxNumberOfAuction(0));
	}
	
	@Test
	public void testIsValidMaxNumberOfAuctionOnLessThanCurrentMaxNumberOfAuction(){
		assertFalse(myCalendar.isValidMaxNumberOfAuction(20));
	}
	
	@Test
	public void testIsValidMaxNumberOfAuctionOnEqualToCurrentMaxNumberOfAuction(){
		assertFalse(myCalendar.isValidMaxNumberOfAuction(myCalendar.getMaxNumberOfAuction()));
	}
	
	@Test
	public void testIsValidMaxNumberOfAuctionOnMoreThanTotalNumberOfAuctionInOneMonth(){
		assertFalse(myCalendar.isValidMaxNumberOfAuction(70));
	}
	
	@Test
	public void testIsValidMaxNumberOfAuctionOnLessThanTotalNumberOfAuctionInOneMonth(){
		assertTrue(myCalendar.isValidMaxNumberOfAuction(50));
	}
	

	//Carl Seiber Author of delete Auction Tests and method.
	/**
	 * Test method for {@link model.AuctionCalendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testdeleteAuctionOnNoAuctionFoundWithNPO() {
		//Note: the addAuctionNoDateRestriction method was strictly created to allow scheduling of auctions earlier
		//than the one week for purposes of testing the deleteAuction. This was an exact copy of the addAuction
		//with only the valid date restriction removed and will not be used in implementation.
		myCalendar.addAuctionNoDateRestriction(a, LocalDateTime.now().plusDays(5), 10, "");
		myCalendar.addAuctionNoDateRestriction(b, LocalDateTime.now().plusDays(5), 10, "");
		assertEquals(myCalendar.deleteAuction(c.getMyName()), 0);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testdeleteAuctionOnPastAuction() {
		//Note: the addAuctionNoDateRestriction method was strictly created to allow scheduling of auctions earlier
		//than the one week for purposes of testing the deleteAuction. This was an exact copy of the addAuction
		//with only the valid date restriction removed and will not be used in implementation.
		myCalendar.addAuctionNoDateRestriction(a, LocalDateTime.now().minusDays(5), 10, "");
		myCalendar.addAuctionNoDateRestriction(b, LocalDateTime.now().minusDays(5), 10, "");
		assertEquals(myCalendar.deleteAuction(c.getMyName()), 0);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testdeleteAuctionOnAuctionScheduledInExactlyTwoDays() {
		myCalendar.addAuctionNoDateRestriction(a, LocalDateTime.now().plusDays(2), 10, "");
		myCalendar.addAuctionNoDateRestriction(b, LocalDateTime.now().plusDays(2), 10, "");		
		assertEquals(myCalendar.deleteAuction(a.getMyName()), 1);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testdeleteAuctionOnAuctionScheduledInLessThanTwoDays() {
		myCalendar.addAuctionNoDateRestriction(a, LocalDateTime.now().plusDays(1), 10, "");
		myCalendar.addAuctionNoDateRestriction(b, LocalDateTime.now().plusDays(1), 10, "");
		assertEquals(myCalendar.deleteAuction(a.getMyName()), 2);
	}
	
	/**
	 * Test method for {@link model.AuctionCalendar#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testdeleteAuctionOnAuctionScheduledInMoreThanTwoDays() {
		myCalendar.addAuctionNoDateRestriction(a, LocalDateTime.now().plusDays(3), 10, "");
		myCalendar.addAuctionNoDateRestriction(b, LocalDateTime.now().plusDays(1), 10, "");
		assertEquals(myCalendar.deleteAuction(a.getMyName()), 1);
	}


}
