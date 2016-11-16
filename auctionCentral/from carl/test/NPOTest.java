/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.NPO;

/**
 * @author Carl
 *
 */
public class NPOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnPreviousAuctionGreaterThanOneYear() {
		NPO theNPO = new NPO("theNPO", "NPO");
		theNPO.setLastAuctionDate(LocalDateTime.now().minusDays(400));
		assertTrue(theNPO.isValidAuctionDate(LocalDateTime.now().plusMonths(1)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateLessThanOneYear() {
		NPO theNPO = new NPO("theNPO", "NPO");
		theNPO.setLastAuctionDate(LocalDateTime.now().minusDays(360));
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now()));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnExactlyOneYearMinusOneDay() {
		NPO theNPO = new NPO("theNPO", "NPO");
		theNPO.setLastAuctionDate(LocalDateTime.now().minusDays(364));
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now()));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnExactlyOneYearSinceLastAuction() {
		NPO theNPO = new NPO("theNPO", "NPO");
		
		//Days offset is so the test wont fail due to the BR stating auctions must be scheduled
		//for at least one week out.
		theNPO.setLastAuctionDate(LocalDateTime.now().minusYears(1).plusDays(10));
		assertTrue(theNPO.isValidAuctionDate(LocalDateTime.now().plusDays(10)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateLessThanOneMonthInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertTrue(theNPO.isValidAuctionDate(LocalDateTime.now().plusDays(15)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateExactlyOneMonthInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertTrue(theNPO.isValidAuctionDate(LocalDateTime.now().plusMonths(1)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateOneMonthAndOneDayInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now().plusMonths(1).plusDays(1)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateMoreThanOneMonthAndOneDayInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now().plusMonths(1).plusDays(5)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateLessThanSixDaysInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now().plusDays(3)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateExactlyOneWeekInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertTrue(theNPO.isValidAuctionDate(LocalDateTime.now().plusDays(7)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateToday() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now()));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateExactlytSixDaysInFuture() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now().plusDays(6)));
	}
	
	/**
	 * Test method for {@link model.NPO#isValidAuctionDate(java.time.LocalDateTime)}.
	 */
	@Test
	public void testisValidAuctionDateOnDateInThePast() {
		NPO theNPO = new NPO("theNPO", "NPO");
		assertFalse(theNPO.isValidAuctionDate(LocalDateTime.now().minusDays(15)));
	}

}
