/**
 * match
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Item;
import model.NPO;

/**
 * @author Carl
 *
 */
public class AuctionTest {
	
	NPO a;
	Auction theAuction;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		a = new NPO("NPOa", "a");
//		theAuction = new Auction(a, 2016, 11, 8, 15, 30,);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Auction#Auction(model.NPO, int)}.
	 */
	@Test
	public void testAuction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#addItem(model.Item)}.
	 */
	@Test
	public void testAddItem() {

		// test addItem
		assertEquals(0, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
		theAuction.addItem(new Item("chocolate"));
		assertEquals(1, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
		theAuction.addItem(new Item("candy"));
		assertEquals(2, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
		theAuction.addItem(new Item("donut"));
		assertEquals(3, ((ArrayList<Item>) theAuction.getItemList()).size(), 0.0);
	}

	/**
	 * Test method for {@link model.Auction#getNPO()}.
	 */
	@Test
	public void testGetNPO() {
		assertEquals(theAuction.getNPO(), a);
	}

	/**
	 * Test method for {@link model.Auction#getItemList()}.
	 */
	@Test
	public void testGetItemList() {
		theAuction.addItem(new Item("chocolate"));
		theAuction.addItem(new Item("banana"));
		theAuction.addItem(new Item("cherry"));

		Object[] items = theAuction.getItemList().toArray();
	
		assertEquals("chocolate", ((Item)items[0]).getItemName());
		assertEquals("banana", ((Item)items[1]).getItemName());
		assertEquals("cherry", ((Item)items[2]).getItemName());
	}

	/**
	 * Test method for {@link model.Auction#tostring()}.
	 */
	@Test
	public void testtoString() {
		theAuction.addItem(new Item("chocolate"));
		theAuction.addItem(new Item("banana"));
		theAuction.addItem(new Item("cherry"));
		assertEquals("NPO Username: a\nTotal number of items: 3\n\tItem 1: chocolate\n\tItem 2: banana\n\tItem 3: cherry\n\n\n", theAuction.toString());
	}

}
