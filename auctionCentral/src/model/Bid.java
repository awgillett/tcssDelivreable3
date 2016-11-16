package model;

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
	 * get the name of the bidder
	 * @return the myBidderName
	 */
	public String getMyBidderName() {
		return myBidderName;
	}

	/**
	 * set the name of the bidder
	 * @param myBidderName the myBidderName to set
	 */
	public void setMyBidderName(String myBidderName) {
		this.myBidderName = myBidderName;
	}

	/**
	 * get the ID of the auction the bid is for
	 * @return the myAuctionID
	 */
	public int getMyAuctionID() {
		return myAuctionID;
	}

	/**
	 * set the auction ID to this bid
	 * @param myAuctionID the myAuctionID to set
	 */
	public void setMyAuctionID(int myAuctionID) {
		this.myAuctionID = myAuctionID;
	}

	/**
	 * get the item ID of the item being bid on
	 * @return the myItemID
	 */
	public int getMyItemID() {
		return myItemID;
	}

	/**
	 * set the item ID to this bid 
	 * @param myItemID the myItemID to set
	 */
	public void setMyItemID(int myItemID) {
		this.myItemID = myItemID;
	}

	/**
	 * get the amount this bit is for
	 * @return the myBidAmount
	 */
	public double getMyBidAmount() {
		return myBidAmount;
	}

	/**
	 * set the amount the of the bid 
	 * @param myBidAmount the myBidAmount to set
	 */
	public void setMyBidAmount(int myBidAmount) {
		this.myBidAmount = myBidAmount;
	}
	

	

	
}