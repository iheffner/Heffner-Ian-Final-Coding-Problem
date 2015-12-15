package ch.makery.address.model;

import domain.RateDomainModel;
import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;

public class Rate extends RateDomainModel {
	
	private double PresentValue;
	private double InterestRate;
	
	public Rate(int CreditScore) {
		super();
		setInterestRate(RateDAL.getRate(CreditScore));
	}
	
	public double getPresentValue() {
		return PresentValue;
	}

	public void setPresentValue(double presentValue) {
		this.PresentValue = presentValue;
	}
	
	@Override
	public double getInterestRate() {
		return InterestRate;
	}
	@Override
	public void setInterestRate(double interestRate) {
		InterestRate = interestRate;
	}

	public double getPayment(int NumberOfPayments)
	{
		//To find the monthly payment for the mortgage:
		//pmt parameters: (rate, num of periods, present value, future value, type)
		double monthlyrate = InterestRate/1200;
		double payment = -1 * FinanceLib.pmt(monthlyrate, (double) NumberOfPayments, PresentValue, (double) 0, false);
    	
		return payment;
	}
}
