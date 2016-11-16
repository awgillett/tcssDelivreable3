package model;
//match
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


/* The Auction class holds the auction's information and the Items in the auction
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class Auction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6428727641409006380L;
	private NPO NPOname;
	private LocalDateTime auctionDate;
	private int myID;
	//	private String contactPerson;
//	private String contactPhone;
	private int expectedItems;
	private String myNotes;
	private int nextItemID;
	private Collection<Item> myItemList;
	/**
	 * an Auction has NPO name, auction date, item count, side notes, and auction ID
	 * @param NPOname
	 * @param theDate
	 * @param itemCount
	 * @param theNotes
	 * @param theID
	 */
	public Auction(NPO NPOname, LocalDateTime theDate, int itemCount, String theNotes, int theID){
		this.NPOname = NPOname;
//		this.contactPerson = contactPerson;
//		this. contactPhone = contactPhone;
		myNotes = theNotes;
		auctionDate = theDate;
		expectedItems = itemCount;
		myItemList = new ArrayList<Item>();
		myID = theID;
	}
	
	/**
	 * @return the nPOname
	 */
	public NPO getNPOname() {
		return NPOname;
	}
	/**
	 * @param nPOname the nPOname to set
	 */
	public void setNPOname(NPO nPOname) {
		NPOname = nPOname;
	}
	/**
	 * @return the myID
	 */
	public int getMyID() {
		return myID;
	}
	/**
	 * @param myID the myID to set
	 */
	public void setMyID(int myID) {
		this.myID = myID;
	}
	
	/**
	 * @return the auctionDate
	 */
	public LocalDateTime getAuctionDate() {
		return auctionDate;
	}

	/**
	 * @param auctionDate the auctionDate to set
	 */
	public void setAuctionDate(LocalDateTime auctionDate) {
		this.auctionDate = auctionDate;
	}

	/**
	 * @return the expectedItems
	 */
	public int getExpectedItems() {
		return expectedItems;
	}

	/**
	 * @param expectedItems the expectedItems to set
	 */
	public void setExpectedItems(int expectedItems) {
		this.expectedItems = expectedItems;
	}

	/**
	 * @return the myNotes
	 */
	public String getMyNotes() {
		return myNotes;
	}

	/**
	 * @param myNotes the myNotes to set
	 */
	public void setMyNotes(String myNotes) {
		this.myNotes = myNotes;
	}

	/**
	 * @return the myItemList
	 */
	public Collection<Item> getMyItemList() {
		return myItemList;
	}

	/**
	 * @param myItemList the myItemList to set
	 */
	public void setMyItemList(Collection<Item> myItemList) {
		this.myItemList = myItemList;
	}
	
//	public Auction(NPO NPOname, LocalDateTime theDate){
//		this.NPOname = NPOname;
////		this.contactPerson = contactPerson;
////		this. contactPhone = contactPhone;
//		auctionDate = theDate;
//		expectedItems = 0;
//		myNotes = "";
//		myItemList = new ArrayList<Item>();
//	}
	
	/**
	 * adds an item to myItemList as long as the item has not already been added
	 * @param theItemName
	 * @param theAuctionName
	 * @param theDonor
	 * @param theCondition
	 * @param theSize 
	 * @param theNote
	 * @param theDescription
	 * @param theMinBid the minimum bid allowed
	 * @return return true if item is added and false if the item has already been added to this auction
	 */
	// call isEqual() in item method to implement this method.
	public boolean addItem(String theItemName, String theDonor,
			String theCondition, String theSize, String theNote,
			String theDescription, double theMinBid){
		String id = Integer.toString(myID) + Integer.toString(nextItemID);
		int theId = Integer.valueOf(id);
		Item theItem = new Item(theItemName, theDonor, theCondition, theSize, theNote, theDescription, theMinBid, theId);
		for (Item i : myItemList)
		{
			if (theItem.isEqual(i))
				return false;
		}
		myItemList.add(theItem);
		nextItemID++;
		return true;
	}
	/**
	 * searches through item myItemList to find item that matches the given ID
	 * @param theItemID
	 * @return the item
	 */
	public Item getItem(int theItemID)
	{
		Item item = null;
		for (Item i : myItemList)
		{
			if (i.getMyItemID() == theItemID)
				item = i;
		}
		return item;
	}
    /**
     * 
     * @return the NPOname
     */
	public NPO getNPO(){
		return NPOname;
	}

	/**
	 * 
	 * @return myItemList
	 */
	public Collection<Item> getItemList(){
		return myItemList;
	}
	
	
	@Override
	public String toString(){
//		int i = 1;
		StringBuilder str = new StringBuilder();
		str.append("NPO: " + NPOname.getMyName());
		str.append("\nAuction date: " + getAuctionDate().toString());
		str.append("\nTotal number of items: " + myItemList.size());
		
		str.append("\n\tItem Number: \tItem Name");
		
		for (Item itm : myItemList)
		{
			str.append("\n\t"+ itm.getMyItemID() + "\t\t" + itm.getItemName());
//			i++;
		}
		str.append("\n");
		return str.toString();
	}
}
