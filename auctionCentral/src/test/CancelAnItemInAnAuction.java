package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Calendar;
import model.NPO;

public class CancelAnItemInAnAuction {

	Calendar theCalendar;
	Auction theAuction;
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new Calendar();
		LocalDateTime date = LocalDateTime.now().plusDays(10);
		NPO theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuction(theNPO, date, 0, "");
	}

	

}
