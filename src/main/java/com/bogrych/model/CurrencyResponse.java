package com.bogrych.model;

public class CurrencyResponse {

	protected int currencyCodeA;
	protected int currencyCodeB;
	protected long date;
	protected float rateSell;
	protected float rateBuy;
	protected float rateCross;

	public int getCurrencyCodeA() {
		return currencyCodeA;
	}

	public void setCurrencyCodeA(int currencyCodeA) {
		this.currencyCodeA = currencyCodeA;
	}

	public int getCurrencyCodeB() {
		return currencyCodeB;
	}

	public void setCurrencyCodeB(int currencyCodeB) {
		this.currencyCodeB = currencyCodeB;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public float getRateSell() {
		return rateSell;
	}

	public void setRateSell(float rateSell) {
		this.rateSell = rateSell;
	}

	public float getRateBuy() {
		return rateBuy;
	}

	public void setRateBuy(float rateBuy) {
		this.rateBuy = rateBuy;
	}

	public float getRateCross() {
		return rateCross;
	}

	public void setRateCross(float rateCross) {
		this.rateCross = rateCross;
	}

	public CurrencyResponse(int currencyCodeA, int currencyCodeB, long date, float rateSell, float rateBuy, float rateCross) {
		this.currencyCodeA = currencyCodeA;
		this.currencyCodeB = currencyCodeB;
		this.date = date;
		this.rateSell = rateSell;
		this.rateBuy = rateBuy;
		this.rateCross = rateCross;
	}

	@Override
	public String toString() {
		return "Currency{" +
			"currencyCodeA=" + currencyCodeA +
			", currencyCodeB=" + currencyCodeB +
			", date=" + date +
			", rateSell=" + rateSell +
			", rateBuy=" + rateBuy +
			", rateCross=" + rateCross +
			'}';
	}
}