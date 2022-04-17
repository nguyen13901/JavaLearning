package view;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Các tổ chức nghiên cứu về giấc ngủ uy tín đã đưa ra khuyến cáo về thời gian ngủ hợp lý
 * theo lứa tuổi như sau
 * Đối tượng Độ tuổi (tuổi) Thời gian ngủ tối thiểu (giờ/ngày)
 * Trẻ mới sinh 1 - 3 20
 * Trẻ nhỏ 4 - 13 10 - 12
 * Thanh thiếu niên 14 - 17 8 - 10
 * Thanh niên và người trưởng thành 18 - 64 7 - 9
 * Người lớn tuổi 65 7 - 8
 * Viết chương trình mô phỏng bài toán như sau
 * B1. Nhập tuổi hiện tại của bạn
 * B2. Nhập thời gian đã ngủ trong ngày (giờ-phút)
 * B3. Nhập thời gian bắt đầu ngủ
 * B4. In ra thời gian bạn nên thức dậy để có một sức khỏe tốt
 * Example:
 * Tuổi: 22
 * Thời gian đã ngủ
 * + Số giờ : 1
 * + Số phút: 15
 * Thời gian bắt đầu ngủ: 23H
 *
 * Bạn nên thức dậy lúc: 5H45
 */

public class Ex02 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhap tuoi hien tai cua ban: ");
        int age = sc.nextInt();
        LocalTime timeSleep = getTime("Nhap thoi gian da ngu trong ngay");
        LocalTime timeStartSleep = getTime("Nhap thoi gian ban bat dau ngu");
        System.out.println("Time wake up: " + wakeUp(timeSleep, timeStartSleep, age));
    }

    private static LocalTime getTime(String input) {
        System.out.println(input);
        System.out.print("Nhap gio: ");
        int hour = sc.nextInt();
        System.out.print("Nhap phut: ");
        int minute = sc.nextInt();
        return LocalTime.of(hour, minute);
    }

    private static LocalTime wakeUp(LocalTime timeSleep, LocalTime timeStartSleep, int age) {
        LocalTime timeWakeUp = timeStartSleep;
        LocalTime requiredTime = null;

        if (age >= 1 && age <= 3) {
            requiredTime = LocalTime.of(20,0);
        }
        else if (age >= 4 && age <= 13) {
            requiredTime = LocalTime.of(11, 0);
        }
        else if (age >= 14 && age <= 17) {
            requiredTime = LocalTime.of(9, 0);
        }
        else if (age >= 18 && age <= 64) {
            requiredTime = LocalTime.of(8, 0);
        }
        else {
            requiredTime = LocalTime.of(7, 30);
        }
        return getTimeWakeUp(timeSleep, requiredTime, timeStartSleep);
    }

    private static LocalTime getTimeWakeUp(LocalTime timeSleep, LocalTime requiredTime, LocalTime timeStartSleep) {
        LocalTime timeWakeUp = timeStartSleep;
        long totalMinute = Duration.between(timeSleep, requiredTime).toMinutes();
        timeWakeUp = timeWakeUp.plusHours(totalMinute / 60);
        timeWakeUp = timeWakeUp.plusMinutes(totalMinute % 60);
        return timeWakeUp;
    }
}
