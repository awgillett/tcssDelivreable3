package model;
//match
/**
 * staff user object
 * @author  Seiber, Tran, Gillet, Fitzgerald, Wiklanski
 * @version 11/14/2016 
 */
public class Staff extends User {
    /**
     * constructs a staff object with a name and a unique userName
     * @param theUserName
     * @param theName
     */
	public Staff(String theUserName, String theName) {
		super(theUserName, theName, "Staff");	 	
	}
	
	
}
