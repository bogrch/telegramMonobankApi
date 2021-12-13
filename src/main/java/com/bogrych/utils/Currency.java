package com.bogrych.utils;

public class Currency {
	private String currency;
	private int numericCode;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(int numericCode) {
		this.numericCode = numericCode;
	}

	@Override
	public String toString() {
		return "Currency{" +
			"name='" + currency + '\'' +
			", code=" + numericCode +
			'}';
	}
}
