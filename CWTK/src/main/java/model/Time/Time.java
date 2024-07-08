package model.Time;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Time implements Serializable {
    @Serial
    private static final long serialVersionUID = -2888044945664369582L;
    private Date date;
    private int hour;
    private int minute;
    private int second;
    private Month month;
    private int year;
    private int day;

    private int monthInNum;

    public int getMonthInNum() {
        return monthInNum;
    }

    public Time() {
        date = new Date();
        hour = date.getHours();
        minute = date.getMinutes();
        second = date.getSeconds();
        year = date.getYear();
        day = date.getDay();
        month = Month.getMonthByNum(date.getMonth());
        monthInNum = date.getMonth();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }
}
