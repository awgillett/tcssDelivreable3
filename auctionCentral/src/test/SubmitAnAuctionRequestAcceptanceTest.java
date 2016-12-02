package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import model.Calendar;
import model.NPO;

public class SubmitAnAuctionRequestAcceptanceTest {

	Calendar theCalendar;
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new Calendar();
	}
	
	/**
	 * Test method for {@link model.NPO#deleteAuction(java.lang.String)}.
	 */
	@Test
	public void testCannotCancelAuctionWithinTwoDaysOnDateExactlyTwoDaysInFuture() {
		LocalDateTime date = LocalDateTime.now().plusDays(2);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuctionNoDateRestriction(theNPO, date, 0, "");
		assertEquals(1, theCalendar.deleteAuction(theNPO.getMyName()));
	}

}
