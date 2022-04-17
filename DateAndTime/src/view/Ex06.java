package view;

import bean.DateEnum;

import java.util.Calendar;
import java.util.Date;

public class Ex06 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 29);
        System.out.println(DateEnum.values()[c.get(Calendar.DAY_OF_WEEK) - 1]);
        Calendar firstDayOfWeek = Calendar.getInstance();
        firstDayOfWeek.setTimeInMillis(c.getTimeInMillis());
        firstDayOfWeek.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek() - c.get(Calendar.DAY_OF_WEEK));
        System.out.println("First day of current week: " + firstDayOfWeek.get(Calendar.DAY_OF_MONTH));
        System.out.println("Current day of week: " + c.get(Calendar.DAY_OF_MONTH));
    }
}
