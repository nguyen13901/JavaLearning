import java.io.*;
import java.util.*;

public class EndOfFile {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        String line = "";
        int c = 1;
        do {
            line = sc.nextLine();
            if (line.isEmpty()) break;
            lines.add(c++ + " " + line);
        } while (true);
        lines.forEach(System.out::println);
        sc.close();
    }
}