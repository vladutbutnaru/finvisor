package ro.vladbutnaru.financeadvisor.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ro.vladbutnaru.financeadvisor.io.ConsolePrinter;
import ro.vladbutnaru.financeadvisor.storage.ObjectStorage;

public class FinVisorApp {
	  /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
    public void run(String[] args) {

        CommandLine line = parseArguments(args);

        if (line.hasOption("filename")) {
            String fileName = line.getOptionValue("filename");
            
            try {
				entryPoint(fileName);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
            

        } else {
            printAppHelp();
        }
    }

    /**
     * Parses application arguments
     *
     * @param args application arguments
     * @return <code>CommandLine</code> which represents a list of application
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getOptions();
        CommandLine line = null;

        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);

        } catch (ParseException ex) {

            System.err.println("Failed to parse command line arguments");
            System.err.println(ex.toString());
            printAppHelp();

            System.exit(1);
        }

        return line;
    }
    
    /**
     * Reads application data from a file
     *
     * @param fileName file of application data
     * @return array of double values
     */
    private double[] readData(String fileName) {

        double[] mydata = null;

        return mydata;
    }

    /**
     * Generates application command line options
     *
     * @return application <code>Options</code>
     */
    private Options getOptions() {

    	Options options = new Options();

        options.addOption("f", "filename", true, "personal financial data file");
        return options;
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {

        Options options = getOptions();

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JavaStatsEx", options, true);
    }


    private void entryPoint(String configurationFile) throws java.text.ParseException {
		ConsolePrinter.printWelcome();
		ConsolePrinter.readConfiguration(configurationFile);
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
				System.out.format("+-----------+-----------+%n");
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
