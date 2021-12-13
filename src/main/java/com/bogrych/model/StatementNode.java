package com.bogrych.model;

public class StatementNode {

	private long time;
	private String description;
	private int amount;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "time=" + time +
			", description='" + description + '\'' +
			", amount=***" + // amount +
			'}';
	}
}

