package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02 {
    /**
     * Sử dụng regular expression để:
     * - Kiểm tra định dạng mã SV
     */

    public static void main(String[] args) {
        String input = "B19DSCN222";

        String regex = "^[bcBC]{1}\\d{2}[dDcCnNQqTtsSkK]{4}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println("Success");
            System.exit(0);
        }
        System.out.println("Student code's Format is not valid! Please enter Student Code again!");
    }
}
