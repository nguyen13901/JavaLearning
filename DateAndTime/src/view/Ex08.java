package view;

import java.util.*;
import java.util.function.Consumer;

public class Ex08 {
    private static final String regex = "\\(\\)|\\[\\]|\\{\\}";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.sort(Comparator.comparing(Integer::byteValue, Comparator.reverseOrder()));
    }
}

