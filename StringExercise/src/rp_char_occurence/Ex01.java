package rp_char_occurence;

public class Ex01 {
    // input text: OPENTEXT
    // T
    // output text:OPEN1EX2

    public static void main(String[] args) {
        String input = "Hellllooo javaaa";
        System.out.println(replace_char_occurence1(input, 'l'));
    }

    private static String replace_char_occurence(String input, char c) {
        int count = 49;
        char[] ouputArr = input.toCharArray();
        if (input.indexOf(c) == -1) {
            System.out.println("Given character not available in input string");
        }
        for (int i = 0; i < ouputArr.length; i++) {
            if (ouputArr[i] == c) {
                ouputArr[i] = (char)count++;
            }
        }
        return String.valueOf(ouputArr);
    }
    private static String replace_char_occurence1(String input, char c) {
        int count = 1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c) {
                input = input.replaceFirst("" + c, count++ + "");
            }
        }
        return input;
    }

}
