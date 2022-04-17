package bean;

public class AgeRange {
    private Integer min;
    private Integer max;

    public AgeRange() {

    }

    public static AgeRange of(int min) {
        return new AgeRange().min(min);
    }

    public static AgeRange of(int min, int max) {
        return new AgeRange().min(min).max(max);
    }

    public AgeRange min(int min) {
        this.min = min;
        return this;
    }

    public AgeRange max(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean constrain(int age) {
        return (max == null && age >= min) || (min <= age && max >= age);
    }
}
