package view;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Viết ứng dụng xem lịch có xử lý ngoại lệ với menu tùy chọn gồm các
 * chức năng:
 * 1. Xem lịch theo một ngày cụ thể(nếu là ngày hiện tại thì xuất thêm thông
 * tin ngày giờ cụ thể).
 * 2. Xem lịch theo tháng: hiển thị thông tin lịch chứa các ngày của một
 * tháng sắp xếp theo 7 cột từ thứ hai đến chủ nhật, nếu tháng người
 * dùng chọn xem có ngày hiện tại thì đánh dấu ngày hiện tại bằng kí hiệu
 * dễ nhận diện nào đó(dấu * chẳng hạn).
 */


public class Ex03 {
    private static final String pattern = "dd/MM/yyyy";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern(pattern);
        LocalDate calendar = null;
        while (true) {
            try {
                //calendar = watchCalendar(dft);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        //display(calendar);
        display(LocalDate.now());

        // 2
        System.out.printf("%s%10s%10s%10s%10s%10s%10s",
                "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
        Calendar c = Calendar.getInstance();
        showMonth(4, 2022);

        // 3
        System.out.println("\n=============================");
        showYear(2022);

        // 4
    }

    private static void showMonth(int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek() + 1;
        int count = 1;
        for (int i = 1 ; i <= c.getActualMaximum(Calendar.WEEK_OF_MONTH); i++) {
            System.out.println();
            for (int  j = 1; j <= 7; j++) {
                if (count < firstDayOfWeek || (count - firstDayOfWeek + 1) > c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    System.out.printf("%-10s", "_");
                } else {
                    if (c.equals(Calendar.getInstance())) {
                        System.out.print("*");
                    }
                    System.out.printf("%-10d", count - firstDayOfWeek + 1);
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
                count++;
            }
        }
    }



    private static void showYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        for (int i = c.JANUARY; i <= c.DECEMBER; i++) {
            System.out.print("\n\nThang " + (i+1));
            showMonth(i+1, year);
        }
    }

    private static LocalDate watchCalendar(DateTimeFormatter dft) {
        LocalDate output = null;
        String calendar = "";
        try {
            calendar = enterCalendar("Chon lich: ");
            output = LocalDate.parse(calendar, dft);
        } catch (Exception e) {
            throw new IllegalArgumentException("Enter day following pattern \"dd/MM/yyyy\"");
        }
        return output;
    }

    private static String enterCalendar(String input) {
        System.out.println(input);
        return sc.nextLine();
    }

    private static void display(LocalDate date) {
        LocalDateTime now = LocalDateTime.now();
        String output =   "Ngay " + date.getDayOfMonth()
                        + " thang " + date.getMonth().getValue()
                        + " nam " + date.getYear();
        output = !date.isEqual(LocalDate.now()) ? output : output + " " + now.getHour() + " gio "
                + now.getMinute() + " minutes " + now.getSecond() + " seconds ";
        System.out.println(output);
    }
}



