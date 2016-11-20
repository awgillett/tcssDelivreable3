package model;
//match
import java.io.Serializable;
/*
 * The Bid class holds the information of individual bids
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016
 */
public class Bid implements Serializable{
	
	//attributes
	
	private String myBidderName;
	private int myAuctionID;
	private int myItemID;
	private double myBidAmount;
	/**
	 * a Bid has bidder name, item ID, bid Amount, and auction ID
	 * @param theBidderName
	 * @param theItemID
	 * @param theBidAmount
	 * @param theAuctionID
	 */
	public Bid(String theBidderName, int theItemID, double theBidAmount, int theAuctionID) {
		myBidderName = theBidderName;
		myItemID = theItemID;
		myBidAmount = theBidAmount;
		myAuctionID = theAuctionID;
	}

	/**
	 * @return the myBidderName
	 */
	public String getMyBidderName() {
		return myBidderName;
	}

	/**
	 * @param myBidderName the myBidderName to set
	 */
	public void setMyBidderName(String myBidderName) {
		this.myBidderName = myBidderName;
	}

	/**
	 * @return the myAuctionID
	 */
	public int getMyAuctionID() {
		return myAuctionID;
	}

	/**
	 * @param myAuctionID the myAuctionID to set
	 */
	public void setMyAuctionID(int myAuctionID) {
		this.myAuctionID = myAuctionID;
	}

	/**
	 * @return the myItemID
	 */
	public int getMyItemID() {
		return myItemID;
	}

	/**
	 * @param myItemID the myItemID to set
	 */
	public void setMyItemID(int myItemID) {
		this.myItemID = myItemID;
	}

	/**
	 * @return the myBidAmount
	 */
	public double getMyBidAmount() {
		return myBidAmount;
	}

	/**
	 * @param myBidAmount the myBidAmount to set
	 */
	public void setMyBidAmount(int myBidAmount) {
		this.myBidAmount = myBidAmount;
	}

	
}
