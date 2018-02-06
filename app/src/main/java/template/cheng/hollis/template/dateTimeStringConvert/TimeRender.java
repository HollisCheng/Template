package template.cheng.hollis.template.dateTimeStringConvert;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
//***TopicAdater using getDateTime and getPeriod

public class TimeRender {
    private static SimpleDateFormat formatBuilder;

    private static String getDefaultDateFormat() {
        return "EEE MMM dd HH:mm:ss z yyyy";
    }

    private static String getTimeFormat() {
        return "hh:mm:ss";
    }

    private static String NoticeGetHrMinFormat() {
        return "HH:mm";
    }

    private static String FacilityDateFormat() {
        return "dd/MM/yyyy hh:mm:ss";
    }

    private static String NoticeGetDateFormat() {
        return "dd/MM/yyyy";
    }

    private static String getYear() {
        return "yyyy";
    }

    private static String getMonth() {
        return "MM";
    }

    private static String getDay() {
        return "dd";
    }

    private static String getHour() {
        return "HH";
    }

    private static String getMin() {
        return "mm";
    }

    private static String getDateFormat() {
        return "yyyy/MM/dd";
    }

    private static String getDateFormat1() {
        return "dd/MM/yyyy";
    }

    public static String getDayMonth(String string) {
        String newFormat = "";
        try {
            formatBuilder = new SimpleDateFormat(getFormat());
            Date date = formatBuilder.parse(string);
            long ldate = date.getTime();
            formatBuilder = new SimpleDateFormat(getwriteFormat(), Locale.US);
            newFormat = formatBuilder.format(new Date(ldate));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFormat;
    }

    private static String getDateTimeFormat() {
        return "dd/MM/yyyy HH:mm:ss";
    }

    private static String getFormat() {
        return "dd/MM/yyyy HH:mm";
    }

    private static String getwriteFormat() {
        return "MMMdd,yy";
    }

    //region event
    private static String getHrMinFormat() {
        return "HH:mm";
    }

    public static String getDateForm(String string) {
        String newFormat = "";
        try {
            formatBuilder = new SimpleDateFormat(getFormat());
            Date date = formatBuilder.parse(string);
            long ldate = date.getTime();
            formatBuilder = new SimpleDateFormat(getDateFormat1());
            newFormat = formatBuilder.format(new Date(ldate));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFormat;
    }

    public static String getTime(String string) {
        String dateFrom = "";
        try {
            formatBuilder = new SimpleDateFormat(getFormat());
            Date date = formatBuilder.parse(string);
            long ldate = date.getTime();
            formatBuilder = new SimpleDateFormat(getHrMinFormat());
            dateFrom = formatBuilder.format(new Date(ldate));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFrom;

    }
    //endregion

    public static String getYearFromDate(Date date) {
        formatBuilder = new SimpleDateFormat(getYear());
        return formatBuilder.format(date);
    }

    public static String getMonthFromDate(Date date) {
        formatBuilder = new SimpleDateFormat(getMonth());
        return formatBuilder.format(date);
    }

    public static String getDayFromDate(Date date) {
        formatBuilder = new SimpleDateFormat(getDay());
        return formatBuilder.format(date);
    }

    public static String getHourFromDate(Date date) {
        formatBuilder = new SimpleDateFormat(getHour());
        return formatBuilder.format(date);
    }

    public static String getMinFromDate(Date date) {
        formatBuilder = new SimpleDateFormat(getMin());
        return formatBuilder.format(date);
    }

    public static String FacilityGetDate(Date date) {
        formatBuilder = new SimpleDateFormat(FacilityDateFormat());
        return formatBuilder.format(date);
    }

    public static String NoticeGetDate(Date date) {
        formatBuilder = new SimpleDateFormat(NoticeGetDateFormat());
        return formatBuilder.format(date);
    }

    public static String NoticeGetTime(Date date) {
        formatBuilder = new SimpleDateFormat(NoticeGetHrMinFormat());
        return formatBuilder.format(date);
    }

    public static String getDate(String format) {
        formatBuilder = new SimpleDateFormat(format);
        return formatBuilder.format(new Date());
    }


    public static String getDate() {
        return getDate(getTimeFormat());
    }

    public static String getDateTimeString() {
        return getDate(getDateTimeFormat());
    }

    public static String getDate(Date date, String format) {
        formatBuilder = new SimpleDateFormat(format);
        return formatBuilder.format(date);
    }

    public static String getDate(Date date) {
//        System.out.println("date="+date.toString());
//        System.out.println("today="+new Date().toString());

        formatBuilder = new SimpleDateFormat(getDateTimeFormat());
        return formatBuilder.format(date);
    }

    public static Date getDateTime() {
        return new Date();
    }

    public static long getToday() {
        return new Date().getTime();
    }

    public static Date getDateTime(String dateString) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(getDateTimeFormat());
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDefaultDateFormat(String dateString) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(getDefaultDateFormat());
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateTime(String dateString, String dataFormat) {
        Date date = null;
        if (dataFormat.equals(""))
            dataFormat = getDateTimeFormat();

        SimpleDateFormat format = new SimpleDateFormat(dataFormat);

        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            //Log.w("TR","Error: " + e.toString());
            e.printStackTrace();
        }
        return date;
    }

    public static String getLocaleDatetime(long datetime) {
        return String.format(Locale.getDefault(), "%tF %<tR", new Date(datetime));
    }

    public static String getLocaleDate(long datetime) {
        return String.format(Locale.getDefault(), "%tF", new Date(datetime));
    }

    public static String getLocaleTime(long datetime) {
        return String.format(Locale.getDefault(), "%tR", new Date(datetime));
    }

    public static String getPeriod(long datetime) {
        return getPeriod(null, datetime);
    }

    public static String getPeriod(Context context, long datetime) {
        String result = "";

        int iNum = getState(datetime);

        if (iNum >= 7)
            result = getLocaleDate(datetime);
        else if (iNum > 1)
            result = getWeekDay(iNum * -1);
        else if (iNum == 1) {
            if (context != null)
//                result = context.getString(R.string.Yesterday);
                result = "Yesterday";
            else
                result = getWeekDay(iNum * -1);
        } else if (iNum == 0)
            result = getLocaleTime(datetime);
        else
            result = getLocaleDatetime(datetime);

        return result;
    }

    public static String getDatePeriod(Context context, long datetime) {
        String result = "";

        int iNum = getState(datetime);

        if (iNum >= 7)
            result = getLocaleDate(datetime);
        else if (iNum > 1)
            result = getWeekDay(iNum * -1);
        else if (iNum == 1) {
            if (context != null)
                //                result = context.getString(R.string.Yesterday);
                result = "Yesterday";
            else
                result = getWeekDay(iNum * -1);
        } else if (iNum == 0)
//            result = context.getString(R.string.Today);
            result = "Today";

        return result;
    }

    public static int getState(long datetime) {
        int iNum = 0;
        Date today = new Date();
        Date passDate = new Date(datetime);

        iNum = compareToDay("yyyy", today, passDate);
        if (iNum > 0)
            iNum = 8;
        else {
            iNum = compareToDay("MM", today, passDate);
            if (iNum > 0)
                iNum = 8;
            else {
                int iNumDay = compareToDay("dd", today, passDate);
                iNum = getDaysDifference(passDate, today);

                if (iNum >= 7)
                    iNum = 7;
                else if (iNumDay > 0 && iNum == 0)
                    iNum = iNumDay;
            }
        }

        return iNum;
    }

    public static int compareDate(long datetime1, long datetime2) {
        int iNum = 0;
        Date date1 = new Date(datetime1);
        Date date2 = new Date(datetime2);

        iNum = compareToDay("yyyy", date1, date2);
        if (iNum > 0)
            iNum = 8;
        else {
            iNum = compareToDay("MM", date1, date2);

            if (iNum > 0)
                iNum = 8;
            else
                iNum = compareToDay("dd", date1, date2);
        }
        return iNum;
    }

    private static String getWeekDay(int iDay) {
        String weekName = "";
        Date today = new Date();

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        localCalendar.add(Calendar.DATE, iDay);
        today.setTime(localCalendar.getTime().getTime());

        //formatting day of week in E format like Sun, Mon etc.
        String strDateFormat = "E";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.getDefault());

        //formatting day of week in EEEE format like Sunday, Monday etc.
        strDateFormat = "EEEE";
        //sdf = new SimpleDateFormat(strDateFormat, Locale.getDefault());

        weekName = sdf.format(today);
        return weekName;
    }

    public static int compareToDay(String format, String sDate1, String sDate2) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Date date1 = formatter.parse(sDate1);
            Date date2 = formatter.parse(sDate2);
            return compareToDay(format, date1, date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int compareToDay(String format, Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date1).compareTo(sdf.format(date2));
    }

    public static int getDaysDifference(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null)
            return 0;

        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static int getDaysDifference(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null || calendar2 == null)
            return 0;

        return (int) ((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
    }
}
