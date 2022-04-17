import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ex02 {

    private static final String PATTERN = "#,###.00000";

    public static void main(String[] args) {
        double value = 123243556657.2124d;
        NumberFormat nf = NumberFormat.getInstance(Locale.ITALIAN);
        NumberFormat nf1 = NumberFormat.getInstance();
        System.out.println(nf.format(value));
        System.out.println(nf1.format(value));

        DecimalFormat df = new DecimalFormat(PATTERN);
        System.out.println(df.format(value));




    }
}
