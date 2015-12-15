package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.makery.address.model.Rate;

public class Rate_Test {

	static Rate rate1;
	static Rate rate2;
	static Rate rate3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rate1 = new Rate(630);
		rate1.setPresentValue(450000);
		rate2 = new Rate(849);
		rate2.setPresentValue(1000000);
		rate3 = new Rate(701);
		rate3.setPresentValue(200000);
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
	public void testRate1() {
		//Testing that it's within 1 cent of the expected rather than trying to
		//match the number of decimal places returned
		//15 Year
		assertTrue(Math.abs(rate1.getPayment(180) - 3558.57) < .01);
		//30 Year
		assertTrue(Math.abs(rate1.getPayment(360) - 2415.70) < .01);
	}

	@Test
	public void testRate2() {
		//15 Year
		assertTrue(Math.abs(rate2.getPayment(180) - 7148.83) < .01);
		//30 Year
		assertTrue(Math.abs(rate2.getPayment(360) - 4490.45) < .01);
	}
	
	@Test
	public void testRate3() {
		//15 Year
		assertTrue(Math.abs(rate3.getPayment(180) - 1479.38) < .01);
		//30 Year
		assertTrue(Math.abs(rate3.getPayment(360) - 954.83) < .01);
	}
	
}
