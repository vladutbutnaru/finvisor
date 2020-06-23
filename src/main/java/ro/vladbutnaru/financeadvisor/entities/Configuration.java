package ro.vladbutnaru.financeadvisor.entities;

import com.google.gson.annotations.SerializedName;

public class Configuration {
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("balance_ron")
	private double balanceRon;
	
	@SerializedName("balance_eur")
	private double balanceEur;
	
	@SerializedName("balance_usd")
	private double balanceUsd;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalanceRon() {
		return balanceRon;
	}

	public void setBalanceRon(double balanceRon) {
		this.balanceRon = balanceRon;
	}

	public double getBalanceEur() {
		return balanceEur;
	}

	public void setBalanceEur(double balanceEur) {
		this.balanceEur = balanceEur;
	}

	public double getBalanceUsd() {
		return balanceUsd;
	}

	public void setBalanceUsd(double balanceUsd) {
		this.balanceUsd = balanceUsd;
	}
	
	
	
}
