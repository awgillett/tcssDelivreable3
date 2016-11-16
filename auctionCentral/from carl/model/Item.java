package model;
//match
import java.io.Serializable;
/**
 * Item Class hold information of an item. 
 * @author 
 *
 */
public class Item implements Serializable {
	

	private static final long serialVersionUID = -2307887234345780299L;
	private String myItemName;
	private String myDonor;
	private String myCondition;
	private String mySize;
	private String myNotes;
	private String myDescription;
	private int myItemID;
	private double myMinBid;
	
	/**
	 * an Item has name, condition, size, and minimum bid price.
	 * @param theItemName Item name
	 * @param theCondition Condition of Item
	 * @param theSize Size of Item
	 * @param theMinBid Minimum bid price for the Item.
	 */
	public Item(String theItemName,String theCondition, String theSize, double theMinBid /*int theItemNumber*/){
		myItemName = theItemName;
		myCondition = theCondition;
		mySize = theSize;
		myMinBid = theMinBid;
	}
	
	public Item(String theItemName, String theDonor,
			String theCondition, String theSize, String theNote,
			String theDescription, double theMinBid, int theItemNumber){
		myItemName = theItemName;
		myDonor = theDonor;
		myCondition = theCondition;
		mySize = theSize;
		myNotes = theNote;
		myDescription = theDescription;
		myMinBid = theMinBid;
		myItemID = theItemNumber;
		//This is another comment for Aaron
	}
	
	public boolean isValidMinBidPrice(double thePrice)
	{
		return thePrice > 0;
	}

	
	public boolean isValidBid(double theAmount)
	{
		return theAmount >= myMinBid;
	}
	
	/**
	 * @return the myItemName
	 */
	public String getMyItemName() {
		return myItemName;
	}

	/**
	 * @param myItemName the myItemName to set
	 */
	public void setMyItemName(String myItemName) {
		this.myItemName = myItemName;
	}


	/**
	 * @return the myDonor
	 */
	public String getMyDonor() {
		return myDonor;
	}

	/**
	 * @param myDonor the myDonor to set
	 */
	public void setMyDonor(String myDonor) {
		this.myDonor = myDonor;
	}

	/**
	 * @return the myCondition
	 */
	public String getMyCondition() {
		return myCondition;
	}

	/**
	 * @param myCondition the myCondition to set
	 */
	public void setMyCondition(String myCondition) {
		this.myCondition = myCondition;
	}

	/**
	 * @return the mySize
	 */
	public String getMySize() {
		return mySize;
	}

	/**
	 * @param mySize the mySize to set
	 */
	public void setMySize(String mySize) {
		this.mySize = mySize;
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
	 * @return the myDescription
	 */
	public String getMyDescription() {
		return myDescription;
	}

	/**
	 * @param myDescription the myDescription to set
	 */
	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}


	/**
	 * @return the myItemNumber
	 */
	public int getMyItemID() {
		return myItemID;
	}

	/**
	 * @return the myMinBid
	 */
	public double getMyMinBid() {
		return myMinBid;
	}

	/**
	 * @param myMinBid the myMinBid to set
	 */
	public void setMyMinBid(int myMinBid) {
		this.myMinBid = myMinBid;
	}
	
	public String getItemName(){
		return myItemName;
	}
	
	/**
	 * compare 2 Items
	 * @param theOtherItem comparable Item
	 * @return True if 2 Items are equal.
	 */
	public boolean isEqual(Item theOtherItem){
		return(this.getItemName().equals( theOtherItem.getItemName())
				&& this.getMyCondition().equals(theOtherItem.getMyCondition())
				&& this.getMyMinBid() == theOtherItem.getMyMinBid()
				&& this.getMySize().equals(theOtherItem.getMySize()));
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("Item:\t\t" + myItemName);
		str.append("\nDescription:\t" + myDescription);
		str.append("\nCondition:\t" + myCondition);
		str.append("\nMinimum Bid:\t$" + myMinBid);
		str.append("\n");
		return str.toString();
	}
}

