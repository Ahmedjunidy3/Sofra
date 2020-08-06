package com.example.sofra.utility;


import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;


import com.example.sofra.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FormatDate {
    /**
     * Created by Yehia on 02/08/2017.
     */

        private static final int SECOND_MILLIS = 1000;
        private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


        public static String getTimeAgo(long time, Context context) {
            if (time < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time *= 1000;
            }

            long now = System.currentTimeMillis();
            if (time > now || time <= 0) {
                return null;
            }

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return context.getString(R.string.just_now);
            } else if (diff < 2 * MINUTE_MILLIS) {
                return context.getString(R.string.a_minute_ago);
            } else if (diff < 50 * MINUTE_MILLIS) {
                return context.getString(R.string.From) + " " + diff / MINUTE_MILLIS + " " + context.getString(R.string.minutes_ago);
            } else if (diff < 90 * MINUTE_MILLIS) {
                return context.getString(R.string.an_hour_ago);
            } else if (diff < 24 * HOUR_MILLIS) {
                return context.getString(R.string.From) + " " + diff / HOUR_MILLIS + " " + context.getString(R.string.hours_ago);
            } else if (diff < 48 * HOUR_MILLIS) {
                return context.getString(R.string.yesterday);
            } else {
                return context.getString(R.string.From) + " " + diff / DAY_MILLIS + " " + context.getString(R.string.days_ago);
            }
        }

        public static void getTimeAgo2(String time, TextView tv) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                String finalTime;
                SimpleDateFormat dayFormatOnly = new SimpleDateFormat("dd", Locale.ENGLISH);
                Date date = df.parse(time);
                df.setTimeZone(TimeZone.getDefault());
                assert date != null;
                String dayFormatPubLocale = dayFormatOnly.format(date);

                Date updatedLocaleTime = Calendar.getInstance().getTime();
                String dayFormatUpdatedLocaleTime = dayFormatOnly.format(updatedLocaleTime);

                if (Integer.parseInt(dayFormatUpdatedLocaleTime) > Integer.parseInt(dayFormatPubLocale)) {
                    finalTime = (Integer.parseInt(dayFormatUpdatedLocaleTime) - Integer.parseInt(dayFormatPubLocale)) + "d";
                    tv.setText(finalTime);
                }

                if (dayFormatUpdatedLocaleTime.equals(dayFormatPubLocale)) {
                    SimpleDateFormat hoursFormatOnly = new SimpleDateFormat("HH", Locale.ENGLISH);
                    String hoursFormatOriginalTime = hoursFormatOnly.format(date);
                    String hoursFormatLocalTime = hoursFormatOnly.format(updatedLocaleTime);
                    finalTime = (Integer.parseInt(hoursFormatLocalTime) - Integer.parseInt(hoursFormatOriginalTime)) + "hrs" + " " + "ago";
                    tv.setText(finalTime);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



        public static boolean differenceBetweenTwoDates(long time, long time2) {
            if (time < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time *= 1000;
            }

            if (time2 < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time2 *= 1000;
            }

            final long diff = time - time2;
            if (diff < MINUTE_MILLIS) {
                return false;
            } else if (diff < 2 * MINUTE_MILLIS) {
                return false;
            } else if (diff < 50 * MINUTE_MILLIS) {
                return false;
            } else if (diff < 90 * MINUTE_MILLIS) {
                return false;
            } else if (diff < 24 * HOUR_MILLIS) {
                return true;
            } else if (diff < 48 * HOUR_MILLIS) {
                return true;
            } else {
                return true;
            }
        }

        public static String getDate(String time, Locale lang ) {
            try {
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", lang);
                Date parse2 = inFormat.parse(time);

                String dayOfTheWeek =(String) DateFormat.format("EEE", parse2); // Thursday
                String day = (String) DateFormat.format("dd", parse2); // 20
                String monthString = (String) DateFormat.format("MMM", parse2); // Jun
                String monthNumber = (String) DateFormat.format("MM", parse2); // 06
                String year = (String) DateFormat.format("yyyy", parse2); // 2013
                String hour = (String) DateFormat.format("HH", parse2); // 2013
                String MINUTE = (String) DateFormat.format("mm", parse2); // 2013

                return day + " " + dayOfTheWeek + "," + " " + hour + ":" + MINUTE;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

