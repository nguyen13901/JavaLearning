import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MultiplicationTable {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(bufferedReader.readLine().trim());
//        for (int i = 0; i < 10; i++) {
//            System.out.printf("%d x %d = %d", N, i+1, N * (i+1));
//        }
//        bufferedReader.close();
//    }
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d x %d = %d", 2, i + 1, 2 * (i + 1));
        }
    }
}


