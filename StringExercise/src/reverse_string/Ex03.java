package reverse_string;

public class Ex03 {
    public static void main(String[] args) {
        String input = "Hello Java";
        System.out.println("Input: " + input);
        StringBuilder builder = new StringBuilder(input);
        System.out.println("Output: " + builder.reverse());
    }
}
