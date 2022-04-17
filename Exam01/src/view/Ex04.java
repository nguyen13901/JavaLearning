package view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Ex04 {

    /**
     * 2. Viết chương trình. Tìm kiếm kí tự có số lần xuất hiện nhiều nhất trong chuỗi.
     * Input: Chuỗi gồm các kí tự [a-zA-Z]
     * Example: aaaababbbddc Output: “a”
     * aaaccdcee Output: “a”, “c”
     * @param args
     */

    public static void main(String[] args) {
        Map<Character, Integer> frequency = handle("aaaccdcee");
        int max = frequency.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .reduce(Integer.MIN_VALUE, Integer::max);

        frequency.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == max)
                .map(entry -> entry.getKey())
                .forEach(System.out::println);
    }

    private static Map<Character, Integer> handle(String input) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (!result.containsKey(input.charAt(i))) {
                result.put(input.charAt(i), 1);
            } else {
                result.put(input.charAt(i), result.get(input.charAt(i)) + 1);
            }
        }
        return result;
    }
}
