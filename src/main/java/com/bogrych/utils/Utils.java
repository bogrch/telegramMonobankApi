package com.bogrych.utils;

import com.bogrych.model.CurrencyRate;
import com.bogrych.model.CurrencyResponse;
import com.bogrych.model.StatementNode;
import kong.unirest.JsonNode;
import kong.unirest.JsonObjectMapper;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	private Utils() {

	}

	private static final ObjectMapper objectMapper = new JsonObjectMapper();
	private static final List<Currency> currencies = initCurrencyList();

	public static List<CurrencyRate> convertCurrencyResponse(List<CurrencyResponse> currencyResponseList) {
		return currencyResponseList.stream().map(response -> {
			CurrencyRate currencyRate = new CurrencyRate(response);
			var currencyA = currencies.stream()
				.filter(currency -> currency.getNumericCode() == response.getCurrencyCodeA())
				.findFirst().orElse(null);

			var currencyB = currencies.stream()
				.filter(currency -> currency.getNumericCode() == response.getCurrencyCodeB())
				.findFirst().orElse(null);

			if(currencyA != null) currencyRate.setCurrencyA(currencyA.getCurrency());
			if(currencyB != null) currencyRate.setCurrencyB(currencyB.getCurrency());

			return currencyRate;
		}).collect(Collectors.toList());
	}

	private static List<Currency> initCurrencyList() {

		List<Currency> currencies = new ArrayList<>();
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("currencyNumbers.json");
			if (is == null) return currencies;
			String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
			JSONArray array = new JsonNode(text).getArray();
			for (int i = 0; i < array.length(); i++) {
				currencies.add(objectMapper.readValue(array.getJSONObject(i).toString(), Currency.class));
			}
		} catch (Exception exception) {

		}
		return currencies;
	}

	public static String convertListToString(List<StatementNode> statementNodes) {
		StringBuilder stringBuilder = new StringBuilder();
		statementNodes.forEach(statementNode -> stringBuilder.append(statementNode).append("\n"));
		String s = stringBuilder.toString();
		if(s.length() > 3000) {
			return s.substring(0, 3000);
		}
		return s;
	}

}
