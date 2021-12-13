package com.bogrych.model;


public class CurrencyRate extends CurrencyResponse {
	private String currencyA;
	private String currencyB;

	public CurrencyRate(CurrencyResponse resp) {
		super(resp.currencyCodeA, resp.currencyCodeB, resp.date, resp.rateSell, resp.rateBuy, resp.rateCross);
	}

	public String getCurrencyA() {
		return currencyA;
	}

	public void setCurrencyA(String currencyA) {
		this.currencyA = currencyA;
	}

	public String getCurrencyB() {
		return currencyB;
	}

	public void setCurrencyB(String currencyB) {
		this.currencyB = currencyB;
	}

	@Override
	public String toString() {
		return "CurrencyRate" +
			"currencyA='" + currencyA + '\'' + '\n' +
			"currencyB='" + currencyB + '\'' + '\n' +
			"currencyCodeA=" + currencyCodeA + '\n' +
			"currencyCodeB=" + currencyCodeB + '\n' +
			"date=" + date + '\n' +
			"rateSell=" + rateSell + '\n' +
			"rateBuy=" + rateBuy + '\n' +
			"rateCross=" + rateCross + '\n';
	}
}
