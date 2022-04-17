import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Ex01 {
    public static void main(String[] args) {
        BigDecimal bd = BigDecimal.valueOf(1000.001);
        BigDecimal bd1 = BigDecimal.valueOf(2000.2234);

        bd1 = bd1.add(bd);
        bd1 = bd1.divide(bd, 2);
        BigDecimal bd2 = BigDecimal.valueOf(7.49244);

        System.out.println(bd1);
        System.out.println(bd2.setScale(1, RoundingMode.HALF_UP));

        int[] a = {1, 7,3, 5, 2};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
