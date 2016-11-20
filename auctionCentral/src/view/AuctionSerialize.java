/**
 * match
 */
package view;

import java.io.*;
import java.util.*;

import model.Auction;
import model.Item;
import model.NPO;
import model.User;

/**
 * @author phucv
 *
 */
public class AuctionSerialize {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Collection<Auction> AuctionList = new ArrayList<Auction>();
		Collection<User> UserList = new ArrayList<User>();
		ArrayList<NPO> NPOList = new ArrayList<NPO>();
		
		try{
			
			FileInputStream fileIn = new FileInputStream("C:/Users/Carl/Documents/userlist.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        UserList = (Collection<User>) in.readObject();
	        in.close();
	        fileIn.close();
		}catch(IOException i){
			System.out.println("NPO class not found");
	        i.printStackTrace();
	        return;
		}catch(ClassNotFoundException c){
			System.out.println("NPO class not found");
	        c.printStackTrace();
	        return;
		}

		
		for (User a : UserList){
			if (a.getUserType().equals("NPO")){
				NPOList.add((model.NPO) a);
			}
		}
		
//		AuctionList.add(new Auction(NPOList.get(0), 2016, 11, 10, 9, 00));
//		AuctionList.add(new Auction(NPOList.get(1), 2016, 11, 11, 15, 30));
//		AuctionList.add(new Auction(NPOList.get(2), 2016, 11, 12, 9, 00));
//		AuctionList.add(new Auction(NPOList.get(3), 2016, 11, 14, 16, 30));
//		AuctionList.add(new Auction(NPOList.get(4), 2016, 11, 14, 12, 30));
//		AuctionList.add(new Auction(NPOList.get(5), 2016, 11, 16, 10, 30));
//		AuctionList.add(new Auction(NPOList.get(6), 2016, 11, 17, 13, 0));
//		AuctionList.add(new Auction(NPOList.get(7), 2016, 11, 18, 15, 30));
//		AuctionList.add(new Auction(NPOList.get(8), 2016, 11, 21, 11, 30));
//		AuctionList.add(new Auction(NPOList.get(9), 2016, 11, 23, 13, 30));
//		AuctionList.add(new Auction(NPOList.get(10), 2016, 11, 25, 9, 30));
//		AuctionList.add(new Auction(NPOList.get(11), 2016, 11, 25, 15, 00));
//		AuctionList.add(new Auction(NPOList.get(12), 2016, 11, 26, 13, 00));
//		AuctionList.add(new Auction(NPOList.get(13), 2016, 11, 28, 10, 00));
//		AuctionList.add(new Auction(NPOList.get(14), 2016, 11, 29, 12, 00));
//		AuctionList.add(new Auction(NPOList.get(15), 2016, 11, 30, 9, 00));
//		AuctionList.add(new Auction(NPOList.get(16), 2016, 11, 30, 14, 00));
//		AuctionList.add(new Auction(NPOList.get(17), 2016, 12, 2, 11, 00));
//		AuctionList.add(new Auction(NPOList.get(18), 2016, 12, 5, 11, 00));
//		AuctionList.add(new Auction(NPOList.get(19), 2016, 12, 5, 18, 00));
//		AuctionList.add(new Auction(NPOList.get(20), 2016, 12, 7, 11, 00));
//		AuctionList.add(new Auction(NPOList.get(21), 2016, 12, 8, 15, 00));
//		AuctionList.add(new Auction(NPOList.get(22), 2016, 12, 9, 11, 00));
//		AuctionList.add(new Auction(NPOList.get(23), 2016, 12, 10, 11, 00));

//		((ArrayList<Auction>) AuctionList).get(0).addItem(new Item("Chocolate", "New", "Small", 10.00));
//		((ArrayList<Auction>) AuctionList).get(0).addItem(new Item("Candy", "New", "Small", 7.50));
//		((ArrayList<Auction>) AuctionList).get(0).addItem(new Item("M&M", "New", "Small", 3.00));
//		((ArrayList<Auction>) AuctionList).get(1).addItem(new Item("Toyota Camry", "New", "Large", 27000.00));
//		((ArrayList<Auction>) AuctionList).get(1).addItem(new Item("Honda Civic", "Old", "Large", 23500.00));
//		((ArrayList<Auction>) AuctionList).get(2).addItem(new Item("Apple", "Fresh", "Small", 3.00));
//		((ArrayList<Auction>) AuctionList).get(2).addItem(new Item("Banana","Fresh", "Small", 1.50));
//		((ArrayList<Auction>) AuctionList).get(2).addItem(new Item("Orange","Fresh", "Small", 4.50));
//		((ArrayList<Auction>) AuctionList).get(2).addItem(new Item("Cherry","Fresh", "Small", 5.50));
//		((ArrayList<Auction>) AuctionList).get(3).addItem(new Item("Diamond Ring", "Like New", "Small", 2555.50));
//		((ArrayList<Auction>) AuctionList).get(4).addItem(new Item("Notebook", "New", "Small", 2.50));
//		((ArrayList<Auction>) AuctionList).get(4).addItem(new Item("Pen", "New", "Small", 3.50));
//		((ArrayList<Auction>) AuctionList).get(4).addItem(new Item("Pencil", "New", "Small", 1.50));
//		((ArrayList<Auction>) AuctionList).get(5).addItem(new Item("Chair","New", "Medium", 25.00));
//		((ArrayList<Auction>) AuctionList).get(5).addItem(new Item("Table","New", "Large", 95.50));
//		((ArrayList<Auction>) AuctionList).get(6).addItem(new Item("Iphone 5", "Used", "Small", 95.50));
//		((ArrayList<Auction>) AuctionList).get(6).addItem(new Item("Iphone 5s","Used", "Small", 195.50));
//		((ArrayList<Auction>) AuctionList).get(6).addItem(new Item("Iphone 6","Used", "Small", 275.50));
//		((ArrayList<Auction>) AuctionList).get(6).addItem(new Item("Iphone 6s Plus","New", "Small", 395.50));
//		((ArrayList<Auction>) AuctionList).get(6).addItem(new Item("Iphone 7","New", "Small", 695.50));
//		((ArrayList<Auction>) AuctionList).get(7).addItem(new Item("Samsung S7 Edge","New", "Small", 615.50));
//		((ArrayList<Auction>) AuctionList).get(7).addItem(new Item("LG G5","Used", "Small", 325.00));
//		((ArrayList<Auction>) AuctionList).get(7).addItem(new Item("LG V20","New", "Small", 795.50));
//		((ArrayList<Auction>) AuctionList).get(8).addItem(new Item("Wedding Dress", "Used", "Large", 7795.50));
//		((ArrayList<Auction>) AuctionList).get(9).addItem(new Item("Watch", "New", "Small", 1795.00));
//		((ArrayList<Auction>) AuctionList).get(9).addItem(new Item("Jewery","New", "Small", 3765.00));
//		((ArrayList<Auction>) AuctionList).get(10).addItem(new Item("Iphone 7","New", "Small", 680.50));
//		((ArrayList<Auction>) AuctionList).get(10).addItem(new Item("Iphone 7 Plus","New", "Small", 865.00));
//		((ArrayList<Auction>) AuctionList).get(11).addItem(new Item("Football","Used", "Small", 65.00));
//		((ArrayList<Auction>) AuctionList).get(11).addItem(new Item("Basketball","Used", "Small", 35.00));
//		((ArrayList<Auction>) AuctionList).get(11).addItem(new Item("Soccer ball","New", "Small", 48.00));
//		((ArrayList<Auction>) AuctionList).get(12).addItem(new Item("Wilson's jersey", "New", "Small", 128.00));
//		((ArrayList<Auction>) AuctionList).get(13).addItem(new Item("Nike Air 2015 shoe","New", "Small", 118.00));
//		((ArrayList<Auction>) AuctionList).get(13).addItem(new Item("Nike Air 2016 shoe","New", "Small", 137.00));
//		((ArrayList<Auction>) AuctionList).get(13).addItem(new Item("Nike Air 2017 shoe","New", "Small", 187.00));
//		((ArrayList<Auction>) AuctionList).get(14).addItem(new Item("Adidas shoe", "New", "Small", 88.00));
//		((ArrayList<Auction>) AuctionList).get(14).addItem(new Item("Adidas soccer jersey", "New", "Small", 98.00));
//		((ArrayList<Auction>) AuctionList).get(14).addItem(new Item("Adidas soccer ball", "New", "Small", 56.00));
//		((ArrayList<Auction>) AuctionList).get(15).addItem(new Item("Dell XPS 13 Laptop", "New", "Medium", 999.00));
//		((ArrayList<Auction>) AuctionList).get(15).addItem(new Item("Dell XPS 15 Laptop", "New", "Medium", 1499.00));
//		((ArrayList<Auction>) AuctionList).get(16).addItem(new Item("Frozen Beef (20LBS)", "New", "Medium", 180.00));
//		((ArrayList<Auction>) AuctionList).get(16).addItem(new Item("Frozen chicken (20LBS)", "New", "Medium", 110.00));
//		((ArrayList<Auction>) AuctionList).get(17).addItem(new Item("Desktop CPU", "Used", "Medium", 410.00));
//		((ArrayList<Auction>) AuctionList).get(18).addItem(new Item("HP Printer", "New", "Small", 44.00));
//		((ArrayList<Auction>) AuctionList).get(18).addItem(new Item("Office Printer", "Used", "Large", 2138.00));
//		((ArrayList<Auction>) AuctionList).get(19).addItem(new Item("Projector", "Used", "Medium", 335.00));
//		((ArrayList<Auction>) AuctionList).get(19).addItem(new Item("WhiteBoard", "Used", "Large", 99.00));
//		((ArrayList<Auction>) AuctionList).get(19).addItem(new Item("Projector screen", "Like New", "Large", 137.00));
//		((ArrayList<Auction>) AuctionList).get(20).addItem(new Item("BMW Motorbike", "Used", "Large", 1252.00));
//		((ArrayList<Auction>) AuctionList).get(21).addItem(new Item("HP Printer", "New", "Medium", 56.00));
//		((ArrayList<Auction>) AuctionList).get(21).addItem(new Item("HP monitor", "New", "Medium", 128.00));
//		((ArrayList<Auction>) AuctionList).get(22).addItem(new Item("Lego Puzzle", "New", "Medium", 376.00));
//		((ArrayList<Auction>) AuctionList).get(22).addItem(new Item("Lego T-shirt", "New", "Small", 35.00));
//		((ArrayList<Auction>) AuctionList).get(23).addItem(new Item("24K Gold Iphone 7", "New", "Small", 4500.00));
		
		try{
			
	        FileOutputStream fileOut =
            new FileOutputStream("/Users/phucv/Documents/GitHub/AuctionCentral/auctionlist.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(AuctionList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /AuctionCentral/auctionlist.ser");
		}catch(IOException i){
			i.printStackTrace();
		}
	}
		
}


