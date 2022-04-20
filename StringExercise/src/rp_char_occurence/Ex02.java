package rp_char_occurence;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ex02 {
    // Find first non repeated character in string
    public static void main(String[] args) {
        String input = "i loei javaeojbv";
         LinkedHashMap<Character, Long> ouput = input.chars().boxed()
                .map(e -> (char)e.intValue())
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
         ouput.entrySet().forEach(e -> {
             if (e.getValue().compareTo(Long.valueOf(1)) == 0) {
                 System.out.println(e.getKey());
                 System.exit(0);
             }
         });
    }
}
