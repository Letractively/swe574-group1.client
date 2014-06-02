package tr.edu.boun.swe574.fsn.web.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

	public DateUtil() {
	}

	public static Date addDaysToDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, day);
		return calendar.getTime();
	}

	public static Date addMinutesToDate(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(12, minute);
		return calendar.getTime();
	}

	public static String getDateString(Date date) {
		SimpleDateFormat sdfCalendarDate = new SimpleDateFormat("dd.MM.yyyy");
		if (date == null)
			return null;
		else
			return sdfCalendarDate.format(date);
	}

	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat sdfCalendarDate = new SimpleDateFormat(pattern);
		if (date == null)
			return null;
		else
			return sdfCalendarDate.format(date);
	}
	
	public static String getDateString(XMLGregorianCalendar date, String pattern) {
		SimpleDateFormat sdfCalendarDate = new SimpleDateFormat(pattern);
		if (date == null)
			return null;
		else {
			GregorianCalendar gregorianCalendar = date.toGregorianCalendar();
			Date time = gregorianCalendar.getTime();
			return sdfCalendarDate.format(time);
		}
	}

	public static String getDateTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		if (date == null)
			return null;
		else
			return sdf.format(date);
	}

	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getTimeStamp(Date date) {
		if (date == null)
			return null;
		else
			return new Timestamp(date.getTime());
	}

	public static Date dateDayAdd(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, days);
		return calendar.getTime();
	}

	public static final String datePattern = "dd.MM.yyyy";
	public static final String dateTimePattern = "dd.MM.yyyy HH:mm:ss";
	public static final String DATE_FORMAT_DDMMYYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_MM_DD_YYYY = "MM-dd-yyyy HH:mm:ss";
	
	
	public static XMLGregorianCalendar getXMLDate() throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		return xmlGregorianCalendar;
	}
	
}
