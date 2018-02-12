package com.aurimas.exchange.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@JacksonXmlProperty(localName = "currency")
	private String name;

	@JacksonXmlProperty(localName = "quantity")
	private BigDecimal quantity;

	@JacksonXmlProperty(localName = "rate")
	private BigDecimal rate;

	@JacksonXmlProperty(localName = "unit")
	private String unit;

	private BigDecimal rateDifference;

	public Currency() {
		super();
	}

	public String getName() {
		return name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public String getUnit() {
		return unit;
	}

	public BigDecimal getRateDifference() {
		return rateDifference;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setRateDifference(BigDecimal rateDifference) {
		this.rateDifference = rateDifference;
	}
}
