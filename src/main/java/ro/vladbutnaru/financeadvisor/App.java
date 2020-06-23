package ro.vladbutnaru.financeadvisor;

import java.io.IOException;
import java.text.ParseException;

import ro.vladbutnaru.financeadvisor.console.FinVisorApp;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, ParseException {
		FinVisorApp app = new FinVisorApp();
		app.run(args);

	}

}
