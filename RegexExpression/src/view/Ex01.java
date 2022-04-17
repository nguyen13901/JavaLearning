package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex01 {
    /**
     *  Sử dụng regular expression để:
     *  Kiểm tra định dạng email
     *  ví dụ: nguyen13901@gmail.com
     */
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String regex = "^[a-zA-z]+[a-zA-z0-9]*@{1}[a-zA-z]+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        String input = "";
        while(true) {
            System.out.println("Enter email: ");
            input = sc.nextLine();
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                System.out.println("Success");
                break;
            }
            System.out.println("Email's Format is not valid! Please enter email again!");
        }
    }
}
