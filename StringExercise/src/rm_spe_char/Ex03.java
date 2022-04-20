package rm_spe_char;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Ex03 {
    // Remove Duplicate Character
    public static void main(String[] args) {
        String input = "programing";
        // output : pogramin
        String output = input.chars()
                .distinct()
                .mapToObj(i -> (char)i)
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println("Output: " + output);

        //
        Set<Character> charInput = new LinkedHashSet<>();
        for (int i = 0; i < input.length(); i++) {
            charInput.add(input.charAt(i));
        }

        String output2 = charInput.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Output2: " + output2);
    }
}
