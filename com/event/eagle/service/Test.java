package com.event.eagle.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test test = new Test();

		System.out.println(test.isDateAboveYears("08161999"));

	}

	public boolean isDateAboveYears(String date) {

		DateFormat format = new SimpleDateFormat("MMddyyyy");
		Date previousDate = null;
		try {
			previousDate = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date currentDate = new Date();
		Calendar a = getCalendar(previousDate);
		Calendar b = getCalendar(currentDate);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff >= 19;

	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}
}
