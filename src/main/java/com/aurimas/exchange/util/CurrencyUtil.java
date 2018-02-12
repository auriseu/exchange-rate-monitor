package com.aurimas.exchange.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class CurrencyUtil {
	public static String getProperty(String key) {
		return ResourceBundle.getBundle("configuration").getString(key);
	}

	/**
	 * YYYY-MM-DD
	 */
	public static LocalDate parseDate(String date) {
		return LocalDate.parse(date);
	}

	public static LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
