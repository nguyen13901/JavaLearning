package anagram;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Word     orWd    true
 * Word     Word    true
 * word     DoRw    true
 * Word     worrd   false
 * Java     ajav    true
 * Vava     Avav    true
 * Hello    Hellloo false
 */
public class Ex01 {
    public static void main(String[] args) {
        String s1 = "Word";
        String s2 = "orWd";

        System.out.println("Is Anagram : " + isAnagram(s1, s2));
        System.out.println("Is Anagram : " + isAnagram2(s1, s2));
    }

    private static boolean isAnagram(String s1, String s2) {
        String temp = s1 + s2;
        Map<Integer, Long> checkMap = temp.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return !checkMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .anyMatch(e -> e % 2 != 0);
    }

    private static boolean isAnagram2(String s1, String s2) {
        char[] char1 = s1.toLowerCase().toCharArray();
        char[] char2 = s1.toLowerCase().toCharArray();

        Arrays.sort(char1);
        Arrays.sort(char2);

        for (int i = 0; i < char1.length; i++) {
            if (char1[i] != char2[i]) {
                return false;
            }
        }
        return true;
    }
}
