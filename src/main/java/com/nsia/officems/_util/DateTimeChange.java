package com.nsia.officems._util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.github.mfathi91.time.PersianDate;

@Component
public class DateTimeChange {

    ZoneId defaultZoneId = ZoneId.systemDefault();

    public LocalDateTime getCurrentDate() {
        Instant instant = (new Date()).toInstant();
        LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        return dateTime;
    }

    public LocalDateTime convertStringToLocalDateTime(String dateStr) {
        if (dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public Date convertLocalDateTOStringAndStringToDate(Date date) {
        if (date != null) {

            ZoneId defaultZoneId = ZoneId.systemDefault();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String stringDate = formatter.format(date);
            LocalDate localDate = LocalDate.parse(stringDate);

            Date convertDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

            return convertDate;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeWithMilisec(String dateStr) {
        if (dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeSlashMDYHMS(String dateStr) {
        if (dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public Timestamp convertStringToTimestamp(String dateStr) {
        if (dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTime(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public Timestamp convertStringToTimestampWithMilisec(String dateStr) {
        if (dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTimeWithMilisec(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public LocalDateTime convertDateToLocalDateTime(Date date) {
        if (date != null) {
            Instant instant = date.toInstant();
            LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
            return dateTime;
        }
        return null;
    }

    public Date convertPersianDateToGregorianDate(int tperYear, int startMonth, int startDay) {
        PersianDate persianDate = PersianDate.of(tperYear, startMonth, startDay);
        LocalDate gregDate = persianDate.toGregorian();
        Date date = java.sql.Date.valueOf(gregDate);
        return date;
    }

    public Date convertPersianDateToGregorianDate(String date) {
        PersianDate persianDate = PersianDate.parse(date);
        LocalDate gregDate = persianDate.toGregorian();
        Date d = java.sql.Date.valueOf(gregDate);
        return d;
    }

    public String convertGregorianDateToPersianDate(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        PersianDate persianDate = PersianDate.fromGregorian(localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return persianDate.format(formatter);
    }

    public String convertGregorianDateToPersianDateWithDash(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        PersianDate persianDate = PersianDate.fromGregorian(localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return persianDate.format(formatter);
    }

    public Date convertStringToDate(String date) {
        try {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter1.parse(date);
            return date1;
        } catch (Exception e) {
            System.out.println("exception in parseInt");
        }

        return null;
    }

    public static Date subtractDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }
}
