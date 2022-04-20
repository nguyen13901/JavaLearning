package reverse_string;

public class Ex02 {
    public static void main(String[] args) {
        String input = "Hello Java";
        System.out.println("Input: " + input);
        StringBuffer buffer = new StringBuffer(input);
        System.out.println("Output: " + buffer.reverse());
    }
}
