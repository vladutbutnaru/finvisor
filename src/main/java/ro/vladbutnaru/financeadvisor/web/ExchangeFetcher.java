package ro.vladbutnaru.financeadvisor.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import ro.vladbutnaru.financeadvisor.io.ConsolePrinter;

public class ExchangeFetcher {

	private static final String exchangeURI = "https://api.exchangeratesapi.io/latest?base=EUR&symbols=RON,USD";

	public static void fetchExchange() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(exchangeURI)).build();

		

		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ConsolePrinter.showLine("Today's rates:" + response.body());

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
