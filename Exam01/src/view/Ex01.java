package view;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex01 {
    /**
     * Bài 1 (30đ): Cho dãy N (0 <= N < 100) phần tử. Mỗi phần tử N[i] (1 <= N[i] <= 20) là
     * một số nguyên
     * Yêu cầu: Mỗi câu không được quá 10 dòng code
     * @param args
     */

    public static void main(String[] args) {
        /**
         * Liệt kê các phần tử chỉ xuất hiện một lần duy nhất trong dãy N
         * Example: input: 1 2 3 4 5 6 5 5 3 1
         * output: 2 4 6
         */

        //List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 5, 3, 1);
        List<Integer> numbers = Arrays.asList(5, 7, 9, 10, 20, 9, 7, 11);
        Map<Integer, Long> numbersMap =  numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> uniqueNumbers = numbersMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        uniqueNumbers.forEach(System.out::println);
        System.out.println("==========================");

        /**
         *  Liệt kê các phần tử xuất nhiều hơn một lần trong dãy N
         * Example: input: 5 7 9 10 20 9 7 11
         * output: 7 9
         */

        List<Integer> notUniqueNumbers = numbersMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        notUniqueNumbers.forEach(System.out::println);

    }
}
