package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03 {
    public static void main(String[] args) {
        /**
         * Sử dụng regular expression để:
         * - Kiểm tra định dạng số điện thoại
         *
         */

        String phoneNumber = "(0944).753.329";
        String regex = "^\\(\\d{4}\\)([-.]{1}|\\s{1,})\\d{3}([-.]|\\s{1,})\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            System.out.println("OK");
            System.exit(0);
        }
        System.out.println("Phone number is not valid! Please enter phone number again");
    }
}
