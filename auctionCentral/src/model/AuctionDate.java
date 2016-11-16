package model;
//match
import java.io.Serializable;

public class AuctionDate implements Serializable{
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;

	public AuctionDate(int year, int month, int date, int hour, int minute){
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
	}
	
	public AuctionDate(int year, int month, int date){
		this.year = year;
		this.month = month;
		this.date = date;
		hour = 0;
		minute = 0;
	}
	
	public void setDate(int year, int month, int date, int hour, int minute){
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getYear(){ return year; }
	public int getMonth(){ return month;}
	public int getDate(){ return date;}
//	public int getHour(){ return hour;}
//	public int getMinute(){ return minute;}
	
	public boolean isAfterThisDate(AuctionDate date){
		if (year > date.year){
			return true;
		} else if (year == date.year && month > date.month){
			return true;
		} else if (year == date.year && month == date.month && this.date > date.date){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isSameDate (AuctionDate date){
		return (year == date.year && month == date.month && this.date == date.date);
	}
	
//	public boolean isBeforeThisDate(AuctionDate date){
//		if (year < date.year){
//			return true;
//		} else if (year == date.year && month < date.month){
//			return true;
//		} else if (year == date.year && month == date.month && this.date < date.date){
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(month + "/" + date + "/" + year);
		return str.toString();
		
	}
	
	public String toStringWithTime(){
		StringBuilder str = new StringBuilder();
		str.append(month + "/" + date + "/" + year);
		str.append("\nTime: " + hour + ":" + minute);
		return str.toString();
		
	}
	

}
