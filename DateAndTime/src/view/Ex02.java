package view;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Ex02 {
    public static void main(String[] args) {
        //Viết chương trình tính số ngày giữa hai thời điểm cho trước.
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end = LocalDate.of(2019, 9, 20);
        System.out.println("Duration: " + getDuration(start, end));
    }

    private static String getDuration(LocalDate start, LocalDate end) {
        Period period = Period.between(start, end);
        return period.getDays() + " days " + period.getMonths() + " months " + period.getYears() + " years ";
    }
}
