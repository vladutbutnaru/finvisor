package ro.vladbutnaru.financeadvisor.storage;

import java.util.ArrayList;
import java.util.List;

import ro.vladbutnaru.financeadvisor.entities.Configuration;
import ro.vladbutnaru.financeadvisor.entities.Currency;
import ro.vladbutnaru.financeadvisor.entities.Spending;

public class ObjectStorage {
	public static Configuration configuration;
	public static List<Spending> spendings = new ArrayList<>();
	public static List<Currency> currencies = new ArrayList<>();
	
}
