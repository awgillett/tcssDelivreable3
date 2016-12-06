package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.AuctionCalendar;
import model.NPO;
import model.User;

public class ChangeMaxNumberOfAuctions {
	
	AuctionCalendar myCalendar;

	@Before
	public void setUp() throws Exception {
		myCalendar = new AuctionCalendar();
	}

	 //*********************************************************************************************************
		//Business rule: As a staff member I want to adjust the maximum number of bids
	 //*********************************************************************************************************
	
	
	@Test
	public void testChangeMaxNumberOfAuctionOnMaxValueChanged(){
		ArrayList<User> userList = new ArrayList();
		NPO theNPO = new NPO("er", "e");
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
				myCalendar.addAuction((NPO) u, LocalDateTime.of(year, month, count, 12, 00), 0, "");
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
		
		assertEquals(2, myCalendar.addAuction(theNPO, LocalDateTime.now().plusDays(25), 0, ""));
		
		myCalendar.setMaxNumberOfAuction(26);
		
		assertEquals(4, myCalendar.addAuction(theNPO, LocalDateTime.now().plusDays(25), 0, ""));
	}

}
