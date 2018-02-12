package com.aurimas.exchange.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.aurimas.exchange.model.Currency;
import com.aurimas.exchange.model.CurrencyList;
import com.aurimas.exchange.rest.ExchangeRateRestClient;
import com.aurimas.exchange.util.CurrencyUtil;

public class CurrencyService {
	private static final String PROPERTY_MAX_DATE = "maxDate";

	private ExchangeRateRestClient restClient;
	private CurrencyList currencyList;
	private LocalDate maxDate;

	public CurrencyService(ExchangeRateRestClient restClient) {
		this.restClient = restClient;
		this.maxDate = CurrencyUtil.parseDate(CurrencyUtil.getProperty(PROPERTY_MAX_DATE));
	}

	public void searchExchangeRates() {
		searchExchangeRates(getMaxDate());
	}

	public void searchExchangeRates(Date date) {
		LocalDate localDate = CurrencyUtil.toLocalDate(date);

		CurrencyList current = restClient.getExchangeList(localDate);
		CurrencyList previous = restClient.getExchangeList(localDate.minusDays(1));
		
		// Calculate rate differences
		current.getCurrencyList().forEach((Currency c) -> {
			BigDecimal rate = previous.getCurrencyList().stream().filter(p -> p.getName().equals(c.getName()))
					.findFirst().get().getRate();

			c.setRateDifference((c.getRate().subtract(rate)).setScale(4));
		});
		
		// Sort by difference
		List<Currency> collected = current.getCurrencyList().stream()
				.sorted((o1, o2) -> o2.getRateDifference().compareTo(o1.getRateDifference()))
				.collect(Collectors.toList());

		current.setCurrencyList(collected);

		this.currencyList = current;
	}

	public CurrencyList getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(CurrencyList currencyList) {
		this.currencyList = currencyList;
	}

	public Date getMaxDate() {
		return CurrencyUtil.toDate(maxDate);
	}
}
