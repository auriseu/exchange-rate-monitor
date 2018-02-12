package com.aurimas.exchange.rest;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.aurimas.exchange.model.CurrencyList;
import com.aurimas.exchange.util.CurrencyUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ExchangeRateRestClient {
	final String uri = CurrencyUtil.getProperty("restUrl");

	// TODO: Split to separate methods
	public CurrencyList getExchangeList(LocalDate date) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		RestTemplate rest = new RestTemplate();

		XmlMapper objectMapper = new XmlMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		rest.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter(objectMapper));

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("date", date.toString());

		CurrencyList result = rest.postForObject(uri, parameters, CurrencyList.class);
		result.setLocalDate(date);

		return result;
	}
}
