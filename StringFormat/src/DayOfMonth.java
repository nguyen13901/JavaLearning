import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class DayOfMonth {
    public static void main(String[] args) {
        System.out.println("Today is " + findDay(8, 14, 2017));
    }

    public static String findDay(int month, int day, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int dom = c.getFirstDayOfWeek() + c.da
    }
}