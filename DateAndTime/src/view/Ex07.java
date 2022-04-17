package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ex07 {
    public static void main(String[] args) {
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.of(2021, 11, 21);
        Date subdate = new Date();
        System.out.println(spf.format(subdate));
        System.out.println(dtf.format(date));

        try {
            subdate = spf.parse("20/04/2022");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(spf.format(subdate));
    }
}
