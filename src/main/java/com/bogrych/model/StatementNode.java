package com.bogrych.model;

public class StatementNode {
//	 "id": "l4I5YWaTbsBCb_re",
//		 "time": 1639310881,
//		 "description": "Від: Владислав Боявець",
//		 "mcc": 4829,
//		 "originalMcc": 4829,
//		 "amount": 1000,
//		 "operationAmount": 1000,
//		 "currencyCode": 980,
//		 "commissionRate": 0,
//		 "cashbackAmount": 0,
//		 "balance": 925932,
//		 "hold": true


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
			", amount=" + amount +
			'}';
	}
}

