package view;

import java.util.Arrays;
import java.util.Locale;

public class Ex03 {

    /**
     * 1. Kiểm tra 2 chuỗi Strings có phải là đảo ngữ (anagram) của nhau không
     * 2 Strings được gọi là đảo ngữ nếu chúng sử dụng chung các kí tự, không qua tâm các
     * kí tự trống, không phân biệt hoa thường. Mỗi kí tự phải có cùng số lượng trong cả 2
     * Strings
     * @param args
     */

    public static void main(String[] args) {
        System.out.println("is Anagram: " + isAnagram("Vava", "Aviv"));
    }

    private static boolean isAnagram(String first, String second) {
        if (first == second) {
            return true;
        }
        if (first.length() != second.length()) {
            return false;
        }
        first = first.toUpperCase().trim();
        second = second.toUpperCase().trim();

        char[] charFirst = first.toCharArray();
        char[] charSecond = second.toCharArray();

        Arrays.sort(charFirst);
        Arrays.sort(charSecond);

        for (int i = 0; i < charFirst.length; i++) {
            if (charFirst[i] != charSecond[i]) {
                return false;
            }
        }
        return true;
    }


}
