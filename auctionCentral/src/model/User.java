package model;
//match
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * user object that hold basic information of any user
 * @author Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016 
 */
public class User implements Serializable {
	
	//attributes 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2557293527731096827L;

	String myUserName;
	
	String myName;
	
	String myUserType;
	
	public User() {};
	/**
	 * this constructs a user with a user name, unique userName, and type of user
	 * @param theUserName
	 * @param theName
	 * @param theUserType
	 */
	public User(String theUserName, String theName, String theUserType) {
		myUserName = theUserName;
		myName = theName;
		myUserType = theUserType;
	}
	/**
	 * this constructs a user with a name and unique userName
	 * @param theUserName
	 * @param theName
	 */
	public User(String theUserName, String theName) {
		myUserName = theUserName;
		myName = theName;
	}
	/**
	 * isEqual test to see if the user name already exists 
	 * @param theUser
	 * @return true if already in database, else false
	 */
	public boolean isEqual(User theUser){
		return myUserName.equals(theUser.getMyUserName());
	}
	/**
	 * get the type that the user is; NPO, AuctionCentral staff, or Bidder
	 * @return the type of user the user is, NOP, Staff, or Bidder
	 */
	public String getUserType(){return myUserType;}
    /**
     * get the unique userName of the user
     * @return myUserName 
     */
	public String getMyUserName() {return myUserName;}
	/**
	 * get the users name of the user
	 * @return
	 */
	public String getMyName() {return myName;}

//	public void setUserType(String theUserType){
//		this.myUserType = theUserType;
//	}
	/**
	 * set the unique userName of the user
	 * @param myUserName
	 */
	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}
    /**
     * set the name of the user
     * @param myName
     */
	public void setMyName(String myName) {
		this.myName = myName;
	}

	
}
