package view;

import java.time.*;


/**
 * Bài 1: Xây dựng ứng dụng The Ultimate Relationship Calculator
 *  Nhập thời gian bắt đầu hẹn hò của 2 người.
 *  Nếu đã chia tay, nhập ngày chia tay ngược lại lấy thời gian hiện tại
 *  Nhấn Enter để thực hiện tính toán. Hỏi
 *  Ngày bắt đầu hẹn họ là ngày thứ mấy.
 *  Mối tình đã bắt đầu được bao nhiêu năm, tháng, ngày, giờ, phút, giây
 */
public class Ex01 {
    public static void main(String[] args) {
        //DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy'||'HH:mm:ss");
        // Nhập thời gian bắt đầu hẹn hò của 2 người.
        LocalDateTime timeStartDate = LocalDateTime.of(2010, 1, 1, 8, 30, 4);
        // Nếu đã chia tay, nhập ngày chia tay ngược lại lấy thời gian hiện tại
        LocalDateTime timeBreakUp = LocalDateTime.of(2011, 1, 1, 8, 30, 3);
        // Ngày bắt đầu hẹn họ là ngày thứ mấy.
        System.out.println("Ngay bat dau hen ho: " + getDateStartDate(timeStartDate));
        // Mối tình đã bắt đầu được bao nhiêu năm, tháng, ngày, giờ, phút, giây
        //System.out.println("Moi tinh da keo dai: " + dft.format(getKnownTime(timeStartDate, timeBreakUp)));
        System.out.println("Moi tinh da keo dai: " + getKnownTime(timeStartDate, timeBreakUp));
    }

    private static int getDateStartDate(LocalDateTime time) {
        return time.getDayOfMonth();
    }

    private static String getKnownTime(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Date start must be shorter time end");
        }
        LocalDate dateStart = start.toLocalDate();
        LocalDate dateEnd = end.toLocalDate();

        LocalTime timeStart = start.toLocalTime();
        LocalTime timeEnd = end.toLocalTime();

        Duration duration = Duration.between(timeStart, timeEnd);

        if (timeStart.isAfter(timeEnd)) {
            duration = duration.plusDays(1);
            dateEnd = dateEnd.minusDays(1);
        }

        Period period = Period.between(dateStart, dateEnd);

        return period.getYears() + " years " + period.getMonths() + " months "
                + period.getDays() + " days " + duration.toHoursPart()
                + " hours " + duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds ";
    }

//    private static LocalDateTime getKnownTime(LocalDateTime start, LocalDateTime end) {
//        if (start.isAfter(end)) {
//            throw new IllegalArgumentException("Date start must be shorter time end");
//        }
//        LocalDate dateStart = start.toLocalDate();
//        LocalDate dateEnd = end.toLocalDate();
//
//        LocalTime timeStart = start.toLocalTime();
//        LocalTime timeEnd = end.toLocalTime();
//
//        Duration duration = Duration.between(timeStart, timeEnd);
//        Period period = Period.between(dateStart, dateEnd);
//
//        if (timeStart.isAfter(timeEnd)) {
//            duration = duration.plusDays(1);
//            period = Period.between(dateStart, dateEnd.minusDays(1));
//        }
//
//        LocalDate date = LocalDate.of(period.getYears(), period.getMonths(), period.getDays());
//        LocalTime time = LocalTime.of(duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
//
//        return date.atTime(time);
//    }
}
