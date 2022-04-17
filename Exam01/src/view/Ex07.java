package view;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex07 {
    public static void main(String[] args) {
        String text = "aaaabbbscada";

        Map<Character, Long> fre = text.chars()
                .mapToObj(letter -> (char)letter)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional max = fre.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .reduce(Math::max);

        if (max.isPresent()) {
            fre.forEach((letter, counting) -> {
                if (counting == max.get()) {
                    System.out.println(letter + ", " + counting);
                }
            });
        }

        if (max.isPresent()) {
            fre.forEach(new BiConsumer<Character, Long>() {
                @Override
                public void accept(Character character, Long aLong) {
                    if (aLong == max.get()) {
                        System.out.println(character + ", " + aLong);
                    }
                }
            });
        }
    }


}
