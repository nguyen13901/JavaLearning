package rm_spe_char;

public class Ex02 {
    // Remove whiter Space
    public static void main(String[] args) {
        String input = "   jac  a d d  a s  ";
        System.out.println(input.replaceAll("[\\s]+", ""));
    }

}
