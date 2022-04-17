package view;

import bean.DateEnum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  Kiểm tra năm hiện tại có phải là năm nhuận không
 *  Tháng, năm hiện tại có bao nhiêu ngày
 *  Ngày hiện tại là ngày thứ mấy, in ra ngày theo tiếng việt
 *  In thông tin các ngày trong tháng, tuần hiện tại
 * • dd/MM/yyyy week_day
 *  Đếm xem trong tháng có bao nhiêu ngày chủ nhật và in ra
 */

public class Ex04 {
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        Calendar c = Calendar.getInstance();
        // 1 Kiểm tra năm hiện tại có phải là năm nhuận không
        System.out.println("Current year is leap year: " + calendar.isLeapYear(c.get(Calendar.YEAR)));
        System.out.println("============================");

        // 2 Tháng, năm hiện tại có bao nhiêu ngày
        // Tháng
        System.out.println("Current month has " +  c.getActualMaximum(Calendar.DAY_OF_MONTH)  + " days");
        // Năm
        System.out.println("Current year has " +  c.getActualMaximum(Calendar.DAY_OF_YEAR)  + " days");
        System.out.println("============================");

        // 3 Ngày hiện tại là ngày thứ mấy, in ra ngày theo tiếng việt
        int currenDate = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek() + 1;
        System.out.println("Current day : " + DateEnum.values()[currenDate - 1]);

        // 4.  In thông tin các ngày trong tháng, tuần hiện tại
        //      • dd/MM/yyyy week_day
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy E");
        System.out.println(sdf.format(c.getTime()));
        System.out.println("============================");

        // 5. Đếm xem trong tháng có bao nhiêu ngày chủ nhật và in ra
        System.out.println("Rs : " + noSunday(2022, 5));
        System.out.println("Rs : " + noSunday1(2022, 5));
    }

    private static int noSunday(int year, int month) {
        int nos = 0;
        Calendar dateStart = toCalendar(year, month, 1);
        Calendar dateEnd = toCalendar(year, month, dateStart.getActualMaximum(Calendar.DAY_OF_MONTH));
        Calendar c = dateStart;
        while(c.before(dateEnd)) {
            if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                c.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }
            c.add(Calendar.DAY_OF_MONTH, 7);
            nos++;
        }
        return nos;
    }

    private static int noSunday1(int year, int month) {
        int nos = 0;
        Calendar c = toCalendar(year, month, 1);
        while (true) {
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                break;
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        while (true) {
            nos++;
            c.add(Calendar.DAY_OF_MONTH, 7);
            if (c.get(Calendar.MONTH) > month - 1) {
                break;
            }
        }
        return nos;
    }

    private static Calendar toCalendar(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c;
    }
}
