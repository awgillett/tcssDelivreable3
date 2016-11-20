/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Item;

/**
 * @author Carl
 *
 */
public class ItemTest {
	private Item myItem;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myItem = new Item("Banana", "Good", "Small", 15);
	}
	
//	public void testItemOnMissingItemName(){
//		Item someItem = new Item( null,"Good", "Small", 15);
//		
//		assertEquals(myItem.getItemName(),someItem.getItemName());
//	}

//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for {@link model.Item#Item(java.lang.String)}.
//	 */
//	@Test
//	public void testItem() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for {@link model.Item#getItemName()}.
//	 */
//	@Test
//	public void testGetItemName() {
//		fail("Not yet implemented");
//	}
	
	/**
	 * Test method for {@link model.Item#isValidBid(java.lang.Integer)}.
	 */
	@Test
	public void testIsValidBidOnNegative() {
		
		assertFalse(myItem.isValidBid(-0.01));
		assertFalse(myItem.isValidBid(-10));

	}
	
	@Test
	public void testIsEqual(){
		Item myItem1 = new Item("Banana", "Good", "Small", 15);
		Item myItem2 = new Item("Banana", "Good", "Small", 15);
		assertTrue(myItem1.isEqual(myItem2));
	}
	
	/**
	 * Test method for {@link model.Item#isValidBid(java.lang.Integer)}.
	 */
	@Test
	public void testIsValidBidOnZero() {
		
		assertFalse(myItem.isValidBid(0));
		
	}
	
	@Test
	public void testIsValidBidOnMinBidPrice() {
		
		assertTrue(myItem.isValidBid(15));

	}
	
	@Test
	public void testIsValidBidOnPositiveLessThanMinBidPrice() {
		
		assertFalse(myItem.isValidBid(10.00));
		

	}
	
	@Test
	public void testIsValidBidOnAboveMinBidPrice() {
		
		assertTrue(myItem.isValidBid(15.01));
		assertTrue(myItem.isValidBid(25.00));

	}
	

}
