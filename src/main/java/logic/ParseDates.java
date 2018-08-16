package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParseDates {

    public Date parseDate(String stringDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            parsedDate = df.parse(stringDate);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return parsedDate;
    }

    public Date parseDatesLongFormat(String stringDate) {
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        Date parsedDate = new Date();
        try {
            parsedDate = df.parse(stringDate);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return parsedDate;
    }

    /* TODO: see if this should be five weeks from user indicated date, not actual
    today's date.*/
    public String returnDateFiveWeeksFromNow() {

        Calendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, 35);
        Date fiveWeeks = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        String fiveWeeksFromNow = df.format(fiveWeeks);
        return fiveWeeksFromNow;
    }

    public Date returnDate(Date today, Integer daysToAdd) {

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.setTime(today);
        calendarInstance.add(Calendar.DATE, daysToAdd);
        return calendarInstance.getTime();
    }

    public Date returnToday() {
        return new Date();
    }

    public String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        return df.format(date);
    }

}
