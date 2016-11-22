/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Item;
import model.NPO;
//import model.User;

/**
 * @author Carl/Patrick
 *
 */
public class AuctionTest {
	
	NPO a;
	Auction theAuction = new Auction(a, 
			LocalDateTime.of(2017, 02, 16, 12, 00), 10, "None", 123456);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		a = new NPO("NPOa", "a");	
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
//	 * Test method for {@link model.Auction#Auction(model.NPO, int)}.
//	 */
//	@Test
//	public void testAuction() {
//		//Auction theAuction = new Auction(a, LocalDateTime.of(2017, 02, 16, 12, 00), 10, "None", 123456);
//		
//		assertEquals("Auction param NPOname not initiated correctly!", a, theAuction.getNPO());
//		assertEquals("Auction param theDate not initiated correctly!", LocalDateTime.of(2017, 02, 16, 12, 00), theAuction.getAuctionDate());
//		assertEquals("Auction param itemCount not initiated correctly!", 10, theAuction.getExpectedItems());
//		assertEquals("Auction param theNotes not initiated correctly!", "None", theAuction.getMyNotes());
//		assertEquals("Auction param theID not initiated correctly!", 123456, theAuction.getMyID());
//	}
	
	// Patrick Fitzgerald author of addItem tests and method.
		/**
		 * Test method for {@link model.Auction#addItem(model.Item)}.
		 */
		@Test
		public void testAddItem() {
			// test itemList is empty.
			assertEquals(0, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
			// add first item.
			theAuction.addItem("Painting", "Private Collector", "New", "Large", "None", 
					"A reprint of Picasso's The Old Guitarist.", 100.00);
			// test itemList holds 1 item.
			assertEquals(1, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
			
			theAuction.addItem("Vending Machine", "Private Collector", "Like New",
					"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00);
			assertEquals(2, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
			
			theAuction.addItem("Comic Book", "Private Collector", "Very Good", "Small", "None", 
					"Amazing Spider-Man 300 first appearance of Venom.", 500.00);
			assertEquals(3, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
		}

		/**
		 * Test method for {@link model.Auction#addItem(model.Item)}.
		 */
		@Test
		public void testAddItemDuplicate() {		
			theAuction.addItem("Vending Machine", "Private Collector", "Like New",
					"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00);
			
			assertFalse(theAuction.addItem("Vending Machine", "Private Collector", "Like New",
					"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00));		
			}
		
		/**
		 * Test method for {@link model.Auction#addItem(model.Item)}.
		 */
		@Test
		public void testAddItemNotDuplicate() {	
			theAuction.addItem("Vending Machine", "Private Collector", "Like New",
					"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00);
			
			assertTrue(theAuction.addItem("Vending", "Private", "Like New",
					"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00));		
		}

	/**
	 * Test method for {@link model.Auction#getItemList()}.
	 */
	@Test
	public void testGetItemList() {
		
		theAuction.addItem("Painting", "Private Collector", "New", "Large", "", 
				"A reprint of Picasso's The Old Guitarist.", 100.00);
		theAuction.addItem("Vending Machine", "Private Collector", "Like New",
				"Large", "", "A Cocal-Cola machine from the 1920s.", 1000.00);
		theAuction.addItem("Comic Book", "Private Collector", "Very Good", "Small", "", 
				"First appearance of Venom.", 500.00);

		Object[] items = theAuction.getItemList().toArray();
	
		assertEquals("Painting", ((Item)items[0]).getItemName());
		assertEquals("Vending Machine", ((Item)items[1]).getItemName());
		assertEquals("Comic Book", ((Item)items[2]).getItemName());
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemBeforeTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemBeforeTwoDayDeadline() {
		int removedItem = 1;

		theAuction.addItem("Painting", "Private Collector", "New", "Large", "", 
				"A reprint of Picasso's The Old Guitarist.", 100.00);

		assertEquals(removedItem, theAuction.removeItem("Painting"));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemWithNoItem()}.
	 */
	@Test
	public void testRemoveItemWithNoItem() {
		int noItemToRemove = 2;
		assertEquals(noItemToRemove, theAuction.removeItem("Painting"));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemAfterTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemAfterTwoDayDeadline() {
		Auction myAuction = new Auction(a, 
				LocalDateTime.of(2016, 11, 23, 12, 00), 10, "None", 123456);
		int tooLateDate = 3;

		myAuction.addItem("Painting", "Private Collector", "New", "Large", "", 
				"A reprint of Picasso's The Old Guitarist.", 100.00);

		assertEquals(tooLateDate, myAuction.removeItem("Painting"));
	}
	/**
	 * Test method for {@link model.Auction#testRemoveItemOnTwoDayDeadline()}.
	 */
	@Test
	public void testRemoveItemOnTwoDayDeadline() {
		int removedItem = 1;
		Auction myAuction = new Auction(a, 
				LocalDateTime.of(2016, 11, 24, 13, 00), 10, "None", 123456);

		myAuction.addItem("Painting", "Private Collector", "New", "Large", "", 
				"A reprint of Picasso's The Old Guitarist.", 100.00);

		assertEquals(removedItem, myAuction.removeItem("Painting"));
	}

//	/**
//	 * Test method for {@link model.Auction#tostring()}.
//	 */
//	@Test
//	public void testToString() {
//		//theAuction.getItemList().clear();
//		theAuction.addItem("Painting", "Private Collector", "New", "Large", "None", 
//				"A reprint of Picasso's The Old Guitarist.", 100.00);
//		theAuction.addItem("Vending Machine", "Private Collector", "Like New",
//				"Large", "None", "A Cocal-Cola machine from the 1920s.", 1000.00);
//		theAuction.addItem("Comic Book", "Private Collector", "Very Good", "Small", "None", 
//				"First appearance of Venom.", 500.00);
//		
//		assertEquals("NPO Username: a\nAuction Date: 2017-12-16 12:00\nTotal number of items: 3\n\tItem 1: Painting\n\tItem 2: Vending Machine\n\tItem 3: Comic Book\n\n\n", theAuction.toString());
//	}

}
