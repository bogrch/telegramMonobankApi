package com.bogrych.service;

import com.bogrych.model.CurrencyRate;
import com.bogrych.model.CurrencyResponse;
import com.bogrych.model.StatementNode;
import com.bogrych.utils.Utils;
import kong.unirest.JsonObjectMapper;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonobankService {
	private static final ObjectMapper objectMapper = new JsonObjectMapper();
	private List<CurrencyRate> currencyRates = new ArrayList<>();
	private List<StatementNode> decemberStatement = new ArrayList<>();
	private List<StatementNode> novemberStatement = new ArrayList<>();
	private List<StatementNode> octoberStatement = new ArrayList<>();

//	public static void main(String[] args) {
//		Optional<Integer> sum = new MonobankService().getNovemberStatement().stream()
//			.map(StatementNode::getAmount)
//			.reduce(Integer::sum);
//		System.out.println(sum.orElse(null) / 100);
//	}

	@Value("${monobank.token}")
	private String monoToken;
	private List<CurrencyRate> getCurrencyInfo() {
		var currencyList = new ArrayList<CurrencyResponse>();
		var currenciesJson = Unirest.get("https://api.monobank.ua/bank/currency").asJson().getBody().getArray();
		if (currenciesJson.length() < 2) {
			System.out.println("Too Many requests");
		}
		for (int i = 0; i < currenciesJson.length(); i++) {
			currencyList.add(objectMapper.readValue(currenciesJson.getJSONObject(i).toString(), CurrencyResponse.class));
		}
		return Utils.convertCurrencyResponse(currencyList);
	}

	private List<StatementNode> getStatement(long startDate, long endDate) {
		var array = Unirest.get(String.format("https://api.monobank.ua/personal/statement/0/%d/%d", startDate, endDate))
			.header("X-Token", monoToken)
			.asJson().getBody().getArray();

		var statementList = new ArrayList<StatementNode>();

		for (int i = 0; i < array.length(); i++) {
			statementList.add(objectMapper.readValue(array.getJSONObject(i).toString(), StatementNode.class));
		}
		return statementList.stream()
			.peek(statement -> statement.setAmount(statement.getAmount() / 100))
			.collect(Collectors.toList());
	}

	public List<StatementNode> getDecemberStatement() {
		if(decemberStatement.size() < 2) {
			decemberStatement = getStatement(1638316800, System.currentTimeMillis() / 1000);
		}
		return decemberStatement;
	}

	public List<StatementNode> getOctoberStatement() {
		if(octoberStatement.size() < 2) {
			octoberStatement =  getStatement(1633046400, 1635724800);
		}
		return octoberStatement;
	}
	public List<StatementNode> getNovemberStatement() {
		if(novemberStatement.size() < 2) {
			novemberStatement = getStatement(1635724800, 1638316800);
		}
		return novemberStatement;
	}

	public String getSpecificRate(String currencyName) {
		if (currencyRates.size() < 2) {
			currencyRates = getCurrencyInfo();
		}
		if (currencyRates.size() < 2) {
			return "Too many requests";
		}
		Optional<CurrencyRate> usdRate = currencyRates.stream().filter(currencyRate -> currencyRate.getCurrencyA().equals(currencyName)).findFirst();
		CurrencyRate currencyRate = usdRate.orElse(null);
		return currencyRate != null ? currencyRate.toString() : "no info";
	}
}
