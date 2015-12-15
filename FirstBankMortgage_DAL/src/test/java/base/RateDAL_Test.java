package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class RateDAL_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllRates() {
		ArrayList<RateDomainModel> rates1 = RateDAL.getAllRates();
		
		//# of rows is not zero?
		assertTrue(rates1.size() > 0);
		
		//unique rate ids in order?
		for (int i = 0; i < rates1.size(); i++) {
			assertTrue(rates1.get(i).getRateID() == i + 1);
		}
	}

	@Test
	public void testGetRate() {
		//Based on the table:
		//Credit score | Interest Rate
		//   600-649   | 5.0
		//   650-699   | 4.5		
		//   700-749   | 4.0
		//   750-799   | 3.75		
		//     800+    | 3.5
		
		//Test that the returned rates are correct
		assertTrue(RateDAL.getRate(615) == 5);
		assertTrue(RateDAL.getRate(675) == 4.5);
		assertTrue(RateDAL.getRate(731) == 4);
		assertTrue(RateDAL.getRate(760) == 3.75);
		assertTrue(RateDAL.getRate(815) == 3.5);
	}
	
}
