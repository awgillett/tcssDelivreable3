package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCalendar;
import model.Item;
import model.NPO;

public class CancelAnItemInAnAuction {
    
	NPO theNPO;
	AuctionCalendar theCalendar;
	Auction theAuction;
	LocalDate Today;
	int Month;
	Item lamp;
	Item painting; 
	
	@Before
	public void setUp() throws Exception {
		theCalendar = new AuctionCalendar();
		LocalDateTime date = LocalDateTime.now().plusDays(10);
		theNPO = new NPO("theNPO", "NPO");
		theCalendar.addAuction(theNPO, date, 0, "");
		theAuction = new Auction(theNPO, 
				LocalDateTime.of(2017, 02, 16, 12, 00), 10, "None", 123456);
		Today = LocalDateTime.now().toLocalDate();
		Month = Today.getMonthValue();
		painting = new Item("Painting", "Private Collector", "New", "Large", "", 
				"A reprint of Picasso's The Old Guitarist.", 100.00, 111);
		lamp  = new Item("Leg Lamp", "Private Collector", "New", "Large", "", 
				"A Christmas Story Lamp.", 50.00, 555);
	}
	
	/**
	 * Test method for {@link model.Auction#testRemoveItemBeforeTwoDayDeadline()}.
	 */
	@Test 
	public void testRemoveItemBeforeTwoDayDeadline() {
		int removedItem = 1;
		theAuction.addItem(painting);
		theAuction.addItem(lamp);
		assertEquals(removedItem, theAuction.removeItem(lamp));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemAfterTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemAfterTwoDayDeadline() {
		LocalDate Today = LocalDateTime.now().toLocalDate();
		int OneDayOut = Today.getDayOfMonth()+1;
		int tooLateDate = 3;
		Auction myAuction = new Auction(theNPO, 
				LocalDateTime.of(2016, Month, OneDayOut, 23, 00), 10, "None", 123456);
		theAuction.addItem(painting);
		assertEquals(tooLateDate, myAuction.removeItem(painting));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemOnTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemOnTwoDayDeadline() {
		LocalDate Today = LocalDateTime.now().toLocalDate();
		int TwoDaysOut = Today.getDayOfMonth()+2;
		int removedItem = 1;
		Auction myAuction = new Auction(theNPO, 
				LocalDateTime.of(2016, Month, TwoDaysOut, 23, 00), 10, "None", 123456);
		myAuction.addItem(painting);
		assertEquals(removedItem, myAuction.removeItem(painting));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemOnTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemThatDoesNotExist() {
		int itemNotFound = 2;
		theAuction.addItem(painting);
		assertEquals(itemNotFound, theAuction.removeItem(lamp));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemWithNoItem()}.
	 */
	@Test
	public void testRemoveItemWithNoItemInList() {
		int itemNotFound = 2;
		assertEquals(itemNotFound, theAuction.removeItem(lamp));
	}

	

}
