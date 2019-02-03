package kejamart.helper;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class StringToCalendar {

	public static Logger logger = Logger.getLogger(StringToCalendar.class);

	// e.g. Wed, 4 Jul 2001
	public static String DATE_PATTERN = "EEE, d MMM yyyy";

	// e.g. Wed, 4 Jul 2001 12:08:56
	public static String DATE_TIME_PATTERN = "EEE, d MMM yyyy HH:mm:ss";

	private SimpleDateFormat simpleDateFormat;

	private String pattern;

	public StringToCalendar() {
		pattern = DATE_PATTERN;
		simpleDateFormat = new SimpleDateFormat(pattern);
	}

	public void checkPattern(String pattern) throws StringToCalendarException {
		boolean validPattern = pattern.equals(DATE_TIME_PATTERN)
				|| pattern.equals(DATE_PATTERN);

		if (!validPattern) {
			throw new StringToCalendarException("Pattern '" + pattern
					+ "' not recognized");
		}
	}

	public boolean isFormattedDate(String formattedDate) {

		ParsePosition parsePosition = new ParsePosition(0);

		simpleDateFormat.parse(formattedDate, parsePosition);

		return (parsePosition.getErrorIndex() == -1);
	}

	public long getTime() {

		return new Date().getTime();
	}

	public String getFormatedDate(Calendar calendar) {

		String formatedDate = null;

		try {
			formatedDate = getFormatedDate(calendar, DATE_TIME_PATTERN);
		} catch (StringToCalendarException e) {
		}

		return formatedDate;
	}

	public String getFormatedDate(Calendar calendar, String pattern)
			throws StringToCalendarException {

		checkPattern(pattern);

		simpleDateFormat = new SimpleDateFormat(pattern);

		String formattedDate = simpleDateFormat.format(calendar.getTime());

		return formattedDate;
	}

	public Calendar getCalendarFromFormattedDate(String formattedDate)
			throws StringToCalendarException {

		return getCalendarFromFormattedDate(formattedDate, DATE_TIME_PATTERN);
	}

	public Calendar getCalendarFromFormattedDate(String formattedDate,
			String pattern) throws StringToCalendarException {

		try {
			checkPattern(pattern);

			simpleDateFormat = new SimpleDateFormat(pattern);

			Date date = simpleDateFormat.parse(formattedDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			return calendar;
		} catch (ParseException e) {

			throw new StringToCalendarException("Could not parse date, "
					+ formattedDate, e);
		}
	}
}
