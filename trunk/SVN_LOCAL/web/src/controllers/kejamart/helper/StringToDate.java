package kejamart.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringToDate {
	public Date convertStringToDate(String str_date, String format) {
		Date date = null;
		try {
			// String str_date = "11/June/07";
			DateFormat formatter;
			//format "dd/mm/yy"
			formatter = new SimpleDateFormat(format);
			date = (Date) formatter.parse(str_date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}
		return date;
	}
	
	public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
