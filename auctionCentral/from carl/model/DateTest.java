package model;
//match
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Period;

public class DateTest {

	public static void main(String[] args) {
		int curDay = LocalDateTime.now().getDayOfMonth();
		System.out.println("   Su\t   M\t   T\t   W\t   Th\t   F\t   Sa");
		System.out.println("|" + "[|\t|\t|\t|\t|\t|\t|");
		System.out.println("|\t|\t|\t|\t|\t|\t|\t|");
		System.out.println("|\t|\t|\t|\t|\t|\t|\t|");
		System.out.println("|\t|\t|\t|\t|\t|\t|\t|");
//		// TODO Auto-generated method stub
//		LocalDateTime time = LocalDateTime.now();
//		LocalDateTime otherTime = LocalDateTime.of(2016, 11, 11, 01, 0);
//		System.out.println(time.toLocalDate().equals(otherTime.toLocalDate()));
//		Period timeDiff = Period.between(time.toLocalDate(), otherTime.toLocalDate());
//		//System.out.println(timeDiff.getMonths());
//		
//		//System.out.println(time.toString());

	}

}
