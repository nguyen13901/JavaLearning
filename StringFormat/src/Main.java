public class Main {
    public static void main(String[] args) {
        String[] fullNames = new String[] {"Tom", "Jerry", "Donald"};
        float[] salaries = new float[] {1000, 1500, 1200};

        System.out.printf("%-10s|%-30s|%-15s%n", "No", "FullName", "Salary");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-10d|%-30s|%-15.0f%n", i+1, fullNames[i], salaries[i]);
        }

        System.out.printf("%-14s %03d", "123456789012345", 1);
    }
}
