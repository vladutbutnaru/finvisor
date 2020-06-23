package ro.vladbutnaru.financeadvisor.logic;

import java.util.Date;

import ro.vladbutnaru.financeadvisor.storage.ObjectStorage;

public class FutureProcessor {

	public static double computeBalanceAt(Date date, double monthlyIncome) {
	
		Date now = new Date();
		long monthsMilis = date.getTime() - now.getTime();
		long months = monthsMilis / (3600000 * 24 * 29);
		System.out.println(date.toString());
		System.out.println(months + "");
		return ObjectStorage.configuration.getBalanceRon() + monthlyIncome * months;
		
	} 
}
