package api.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import api.constants.DateValues;

public class DateUtils {

	private DateUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String getCurrentDateTimeMMddHHmmss() {
		return getCurrentDateTime("MMddHHmmss");
	}

	public static String getCurrentDateTimeddMMYYYYHHmm() {
		return getCurrentDateTime("dd-MM-YYYY HH:mm");
	}

	public static String getCurrentDateTimeHHmmss() {
		return getCurrentDateTime("HHmmss");
	}

	public static String getCurrentMonthDateOnly() {
		return getCurrentDateTime("d");
	}

	public static String getTomorrowsDateOnly() {
		return getTomorrowsDateTime("d");
	}

	public static String getYesterdaysDateOnly() {
		return getYesterdaysDateTime("d");
	}

	private static String getCurrentDateTime(String formatter, int dayNumber) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime updatedTime2 = currentTime.plusDays(dayNumber);
		return dtf.format(updatedTime2);
	}

	public static String getDayName(int dayNumber) {
		return getCurrentDateTime("EEEE", dayNumber);
	}

	private static String getCurrentDateTime(String formatter) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		return dtf.format(currentTime);
	}

	private static String getYesterdaysDateTime(String formatter) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime updatedTime = currentTime.minusDays(1);
		return dtf.format(updatedTime);
	}

	public static String getCurrentDateTimeMMMMdYYYY() {
		return getCurrentDateTime("MMMM d, yyyy");
	}

	/**
	 * @return Returns current date in a format of dd-MM-yyyy. Ex- 15-10-2019
	 */
	public static String getCurrentDateTimeInFormatddMMYYYY() {
		return getCurrentDateTime("dd-MM-yyyy");
	}

	public static String getDateTimeForDashboard() {
		return getCurrentDateTime("dd / MM / yyyy");
	}

	public static String getNextDay() {
		String currentDate = getCurrentDateTimeInFormatddMMYYYY();
		return getTomorrowsDateTime(currentDate);
	}

	private static String getTomorrowsDateTime(String formatter) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		currentTime = currentTime.plusDays(1);
		return dtf.format(currentTime);
	}

	public static int getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * @return Returns Yesterday's date in a format of dd-MM-yyyy. Ex- 15-10-2019
	 */
	public static String getYesterdaysDateTimeInFormatddMMYYYY() {
		return getYesterdaysDateTime("dd-MM-yyyy");
	}

	/**
	 * @return Returns current date in a format of dd-MM-YYYY. Ex- 15-10-2019
	 */
	public static String getDateInFormatddMMYYYY(String dateValue) {
		String format = "dd-MM-yyyy";
		switch (dateValue) {
		case DateValues.YESTERDAY:
			return getDateTimeInFormat(format, -1);
		case DateValues.TODAY:
			return getDateTimeInFormat(format, 0);
		case DateValues.TOMORROW:
			return getDateTimeInFormat(format, 1);
		default:
			if (dateValue.startsWith(DateValues.LAST_MONTH)) {
				String datePart = dateValue.split("\\|")[1];
				int intDateToSelect = Integer.parseInt(datePart);
				return getDateTimeInFormat(format, -1, intDateToSelect);
			}
			return "Date Not Configured";
		}
	}

	public static String getDateTimeInFormat(String formatter, int addDay) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime updatedTime = currentTime.plusDays(addDay);
		return dtf.format(updatedTime);
	}

	public static String getDateTimeInFormat(String formatter, int addMonth, int addDay) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime updatedTime = currentTime.plusMonths(addMonth);
		updatedTime = updatedTime.withDayOfMonth(addDay);
		return dtf.format(updatedTime);
	}

	public static String getLastMonthName(String formatter) {
		return getDateTimeInFormat(formatter, -1, 1);
	}

	public static String getCurrentMonthName(String formatter) {
		return getDateTimeInFormat(formatter, 0, 1);
	}

	public static String getCurrentDateTimeYYYMMddHHmmss() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}

	public static String changeDateFormat(String date, String originalFormat, String expectedFormat) {
		try {
			DateFormat srcDf = new SimpleDateFormat(originalFormat);
			// parse the date string into Date object
			Date dateToBeFormatted = srcDf.parse(date);
			SimpleDateFormat newDateFormat = new SimpleDateFormat(expectedFormat);
			return newDateFormat.format(dateToBeFormatted);
		} catch (ParseException e) {
			return "FAIL";
		}
	}

	public static String getCurrentDateTimeYYddHHmmss() {
		return getCurrentDateTime("YYddHHmmss");
	}

	/*
	 * This method calculates the number of days between two dates. Both dates are
	 * inclusive.
	 * 
	 * @param Both date should be in DD-MM-yyyy format.
	 * 
	 * @return number of days returns in int format. Both dates are inclusive.
	 */
	public static int getNumberOfDaysBetweenTwoDates(String strFromDate, String strToDate) {
		String[] strFromDateArray = strFromDate.split("-");
		String[] strToDateArray = strToDate.split("-");
		LocalDate dateFrom = LocalDate.of(Integer.parseInt(strFromDateArray[2]),
				Month.of(Integer.parseInt(strFromDateArray[1])), Integer.parseInt(strFromDateArray[0]));
		LocalDate dateTo = LocalDate.of(Integer.parseInt(strToDateArray[2]),
				Month.of(Integer.parseInt(strToDateArray[1])), Integer.parseInt(strToDateArray[0]));
		long difference = ChronoUnit.DAYS.between(dateFrom, dateTo);
		// Since both of the dates are inclusive adding 1 in the difference.
		return (int) difference + 1;
	}
	
	public static String getCurrentDateTimeHmmssSSS() {
		return getCurrentDateTime("ddMMHHSSS");
	}

	public static String getDateTime(String format, int dateTypeMonthOrYear, int dateMinusPlusFromCurrent) {
		Calendar cal = Calendar.getInstance();
		cal.add(dateTypeMonthOrYear, dateMinusPlusFromCurrent);
		Date result = cal.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String date = dateFormat.format(result);
		return date;
	}

}
