package model;
//match
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * user object 
 * @author
 *
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
	
	public User(String theUserName, String theName, String theUserType) {
		myUserName = theUserName;
		myName = theName;
		myUserType = theUserType;
	}
	
	public User(String theUserName, String theName) {
		myUserName = theUserName;
		myName = theName;
	}
	
	public boolean isEqual(User theUser){
		return myUserName.equals(theUser.getMyUserName());
	}
	
	public String getUserType(){return myUserType;}

	public String getMyUserName() {return myUserName;}
	
	public String getMyName() {return myName;}

//	public void setUserType(String theUserType){
//		this.myUserType = theUserType;
//	}
	
	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	
}
