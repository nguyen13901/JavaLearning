package view;

import bean.AgeRange;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Ex06 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Nhap tuoi hien tai cua ban: ");
        int age = sc.nextInt();
        LocalTime neededSleepTime = getNeededSleepTime(age, getStandardSleepTime());
        System.out.println("Thoi gian ngu can thiet: " + neededSleepTime);
        LocalTime sleptTime = getTime("Nhap thoi gian da ngu trong ngay: ");
        LocalTime startSleepTime = getTime("Nhap thoi gian bat dau di ngu: ");
        System.out.println("Ban nen thuc day: " + getWakeUpTime(neededSleepTime, sleptTime, startSleepTime));
    }

    private static LocalTime getWakeUpTime(LocalTime neededSleepTime, LocalTime sleptTime, LocalTime startSleepTime) {
        Duration requiredTime = Duration.between(sleptTime, neededSleepTime);
        if (requiredTime.isNegative()) {
            System.out.println("Ban da ngu qua nhieu!!");
            System.exit(0);
        }
        return startSleepTime.plusMinutes(requiredTime.toMinutes());
    }

    private static LocalTime getTime(String input) {
        System.out.println(input);
        System.out.println("Nhap gio: ");
        int hour = sc.nextInt();
        System.out.println("Nhap phut: ");
        int minutes = sc.nextInt();
        return LocalTime.of(hour, minutes);
    }

    private static LocalTime getNeededSleepTime(int age, Map<AgeRange, LocalTime> standardSleepTime) {
        for (Entry<AgeRange, LocalTime> entry : standardSleepTime.entrySet()) {
            if (entry.getKey().constrain(age)) {
                return entry.getValue();
            }
        }
        return LocalTime.of(10, 30);
    }

    private static Map<AgeRange, LocalTime> getStandardSleepTime() {
        return new HashMap<AgeRange, LocalTime>() {
            private static final long serialVersionUID = -6186346563674004230L;
            {
                put(AgeRange.of(1,3), LocalTime.of(20, 0));
                put(AgeRange.of(4,13), LocalTime.of(11, 0));
                put(AgeRange.of(14,17), LocalTime.of(9, 0));
                put(AgeRange.of(18,64), LocalTime.of(8, 0));
                put(AgeRange.of(65), LocalTime.of(7, 30));
            }
        };
    }
}
