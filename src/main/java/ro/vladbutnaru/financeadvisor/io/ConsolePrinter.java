package ro.vladbutnaru.financeadvisor.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import ro.vladbutnaru.financeadvisor.entities.Configuration;
import ro.vladbutnaru.financeadvisor.storage.ObjectStorage;

public class ConsolePrinter {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));



	public static void printWelcome() {
		showLine("FinanceAdvisor 1.0.0 BETA		Author: Vlad Butaru");
	}

	public static void readConfiguration(String file) {
		Gson gson = new Gson();
		JsonReader jsonReader;
		showLine("Reading configuration from [" + file + "]");

		try {
			jsonReader = new JsonReader(new FileReader(file));
			Configuration config = gson.fromJson(jsonReader, Configuration.class);
			showLine("Configuration found for name [" + config.getName() + "]");
			ObjectStorage.configuration = config;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void showLine(String line) {
		System.out.println(line);
	}
	
	public static void printMenu() {
		showLine("Pick an option:");
		showLine("0. Exit");
		showLine("1. Plot balance over months");
		showLine("2. Daily spending estimation for fixed economy");
	}
	
	public static String readDate(String message) {
		showLine(message);
		
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	public static double readDouble(String message) {
		showLine(message);
		try {
			return Double.parseDouble(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public static int readInt(String message) {
		showLine(message);
		try {
			return Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
