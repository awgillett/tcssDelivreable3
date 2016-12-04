package test;
//match
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuctionTest.class, BidderTest.class, BidTest.class, CalendarTest.class, ItemTest.class, NPOTest.class,
		StaffTest.class, UserTest.class, BusinessRulesForBidder.class, CancelAnAuctionRequestAcceptanceTest.class,
		CancelAnItemInAnAuction.class, SubmitAnAuctionRequestAcceptanceTest.class})
public class AllTests {

}
