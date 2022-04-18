package view;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Ex04 {

    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n = Integer.parseInt(sc.nextLine());
        String input = "";
        for (int i = 0; i < n; i++) {
            input = sc.nextLine();
            System.out.println(hackerrankpos(input));
        }
    }

    private static int hackerrankpos(String input) {
        Pattern pattern = Pattern.compile("^(h)ackerrank");
        Pattern pattern1 = Pattern.compile("\\shackerrank$");
        Matcher matcher = pattern.matcher(input);
        Matcher matcher1 = pattern1.matcher(input);
        if (matcher.find()) {
            if (matcher1.find()) {
                return 0;
            }
            return 1;
        }
        if (matcher1.find()) {
            return 2;
        }

        return -1;
    }
}