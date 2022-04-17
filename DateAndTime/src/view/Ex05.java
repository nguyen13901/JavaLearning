package view;

import java.time.*;
import java.util.Calendar;

/**
 *  Bài tập
 *  Hoàng và Lan yêu nhau đã được chừng ấy năm
 *
 * Ngày bắt đầu: 20/02/2014
 *
 *  Kiểm tra xem 2 bạn đã yêu nhau được bao nhiêu
 *  Ngày – Giờ - Phút - Giây
 */
public class Ex05 {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2014, 2, 20, 0, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Duration they have known: " + getDuration(start, now));
    }

    private static String getDuration(LocalDateTime start, LocalDateTime end) {

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start must be before end");
        }

        LocalDate dateStart = start.toLocalDate();
        LocalDate dateEnd = end.toLocalDate();

        LocalTime timeStart = start.toLocalTime();
        LocalTime timeEnd = end.toLocalTime();

        Duration duration  = Duration.between(timeStart, timeEnd);

        if (timeEnd.isBefore(timeStart)) {
            duration.plusDays(1);
            dateEnd = dateEnd.minusDays(1);
        }

        Period period = Period.between(dateStart, dateEnd);

        return period.getDays() + "/" + period.getMonths() + "/" + period.getYears() + " "
                + duration.toHoursPart() + ":" + duration.toMinutesPart() + ":" + duration.toSecondsPart();
    }
}
