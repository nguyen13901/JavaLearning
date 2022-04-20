package reverse_string;

public class Ex01 {
    public static void main(String[] args) {
        String input = "Hello";
        System.out.println("reverse: " + reverse(input));
    }


    private static String reverse(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += input.charAt(input.length() - i - 1);
        }
        return output;
    }
}