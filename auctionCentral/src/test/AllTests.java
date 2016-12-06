package test;
//match
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuctionTest.class, BidderTest.class, CalendarTest.class, ItemTest.class, NPOTest.class,
		BusinessRulesForBidder.class, CancelAnAuctionRequestAcceptanceTest.class,
		CancelAnItemInAnAuction.class, SubmitAnAuctionRequestAcceptanceTest.class, ChangeMaxNumberOfAuctions.class})
public class AllTests {

}
