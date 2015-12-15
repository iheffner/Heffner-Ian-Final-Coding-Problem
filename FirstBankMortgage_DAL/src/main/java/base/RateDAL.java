package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class RateDAL {

	
	public static ArrayList<RateDomainModel> getAllRates() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		//RateDomainModel ratGet = null;	Possibly unnecessary
		ArrayList<RateDomainModel> rats = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	 
			
			List rates = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = rates.iterator(); iterator.hasNext();) {
				RateDomainModel rat = (RateDomainModel) iterator.next();
				rats.add(rat);

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rats;
	}
	
	public static double getRate(int GivenCreditScore) {
		//Return the lowest possible interest rate for the credit score
		
		//Get the rates from the table
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		
		double bestRate = 0;
		for (RateDomainModel rr : rates) {
			if (GivenCreditScore > rr.getMinCreditScore()) { 
				bestRate = rr.getInterestRate();
			}
			else { continue; }
		}
		
		//if bestRate still = 0 at this point, a loan was not qualified for
		return bestRate;
	}

}