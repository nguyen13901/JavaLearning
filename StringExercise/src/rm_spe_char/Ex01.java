package rm_spe_char;

public class Ex01 {
    // Remove Special char
    public static void main(String[] args) {
        String input = "$jav!a%@st/\\@@^ar";
        System.out.println("output: " + removeSpeChar(input));
    }

    private static String removeSpeChar(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}
