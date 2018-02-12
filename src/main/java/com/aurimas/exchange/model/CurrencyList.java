package com.aurimas.exchange.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.aurimas.exchange.util.CurrencyUtil;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ExchangeRates")
public class CurrencyList implements Serializable {
	private static final long serialVersionUID = 1L;

	@JacksonXmlProperty(localName = "item")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Currency> currencyList;

	private LocalDate date;

	public CurrencyList() {
		super();
	}

	public Date getDate() {
		return CurrencyUtil.toDate(date);
	}

	public void setDate(Date date) {
		this.date = CurrencyUtil.toLocalDate(date);
	}

	public void setLocalDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getLocalDate() {
		return this.date;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}
}
