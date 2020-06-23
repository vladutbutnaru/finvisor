package ro.vladbutnaru.financeadvisor.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class StatementReader {
	public static void readStatement(String path) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		NumberFormat format = NumberFormat.getInstance(Locale.US);

		try {
			Number number = format.parse("1.234,00");
			// System.out.println(number.doubleValue());
			br = new BufferedReader(new FileReader(path));
			double credit = 0.0;
			double debit = 0.0;
			while ((line = br.readLine()) != null) {

				String[] transaction = line.split(cvsSplitBy);

				try {

					transaction[4] = transaction[4].replaceAll("\"", "").replaceAll("\\.", "");
					if (!transaction[3].contains("Transfer Home'Bank")) {
						debit += format.parse(transaction[4]).doubleValue();
					}

				} catch (Exception e) {
				}
				try {
					transaction[6] = transaction[6].replaceAll("\"", "").replaceAll("\\.", "");
					credit += format.parse(transaction[6]).doubleValue();
				} catch (Exception e) {
				}

			}

			System.out.println("Cheltuieli totale: " + debit);
			System.out.println("Incasari totale: " + credit);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
