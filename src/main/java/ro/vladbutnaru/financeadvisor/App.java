package ro.vladbutnaru.financeadvisor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.sound.sampled.TargetDataLine;

import ro.vladbutnaru.financeadvisor.io.ConsolePrinter;
import ro.vladbutnaru.financeadvisor.storage.ObjectStorage;
/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, ParseException {
		ConsolePrinter.printWelcome();
		ConsolePrinter.readConfiguration("/home/vlad/eclipse-workspace/financeadvisor/configuration/vlad.json");
		boolean running = true;
		while (running) {
			ConsolePrinter.printMenu();
			int option = ConsolePrinter.readInt("Pick option: ");

			if (option == 0)
				running = false;

			if (option == 1) {
				String dateString = ConsolePrinter.readDate("Target date: ");
				double spendingsPerDay = ConsolePrinter.readDouble("Average spendings per day:");
				double fixedPayments = ConsolePrinter.readDouble("Average fixed payments per month:");
				double earningsPerMonth = ConsolePrinter.readDouble("Average earnings per month: ");

				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date = formatter.parse(dateString);

				LocalDateTime now = LocalDateTime.now();
				LocalDateTime wantedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

				
				String leftAlignFormat = "| %-10s | %-4s |%n";
				System.out.format("+-----------+-----------+%n");
				System.out.format("| Month     | Balance   |%n");
				System.out.format("+-----------------+------+%n");
				double startingBalance = ObjectStorage.configuration.getBalanceRon();
				for (LocalDateTime d = now; d.isBefore(wantedDate); d = d.plusMonths(1)) {
					startingBalance += (float) (earningsPerMonth - spendingsPerDay * 30 - fixedPayments);
					System.out.format(leftAlignFormat, d.getMonth().toString(), startingBalance + "");

				}
				System.out.format("+------------+---------+%n");

				ConsolePrinter.showLine("At given date, you will have: " + startingBalance + " in your accounts");

			}
			
			if(option == 2) {
				String dateString = ConsolePrinter.readDate("Target date: ");
				double targetSum = ConsolePrinter.readDouble("Target economies: ");
				double fixedPayments = ConsolePrinter.readDouble("Average fixed payments per month:");
				double earningsPerMonth = ConsolePrinter.readDouble("Average earnings per month: ");

				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date = formatter.parse(dateString);

				LocalDateTime now = LocalDateTime.now();
				LocalDateTime wantedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				double startingBalance = ObjectStorage.configuration.getBalanceRon();
				
			
				long daysBetween = ChronoUnit.DAYS.between(wantedDate, now);
				for (LocalDateTime d = now; d.isBefore(wantedDate); d = d.plusMonths(1)) {
					startingBalance += (float) (earningsPerMonth  - fixedPayments);

				}
				
				double dailySum = (targetSum - startingBalance) / daysBetween;

				ConsolePrinter.showLine("You can spend up to: " + dailySum + " per day");
			}
		}

	}
}
