package generics.utils;

import java.util.OptionalDouble;
import java.util.stream.Stream;

public class StatsUtils<T extends Number> {

    private T[] nums;

    public StatsUtils(T[] nums) {
        this.nums = nums;
    }

    public double avg() {
        return Stream.of(nums)
                .mapToDouble(Number::doubleValue)
                .average()
//                .getAsDouble();
                .orElse(0.0);



//        double sum = 0.0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i].doubleValue();
//        }
//
//        return sum / nums.length;

    }

    public boolean isAvgSame(StatsUtils<?> anotherStats) {
        return Math.abs(this.avg() - anotherStats.avg()) < 0.00001;
    }

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
//        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Double[] doubles = {1.0, 2.0, 3.0, 4.0, 5.0};

        StatsUtils<Integer> intStats = new StatsUtils<>(new Integer[] {});
//        StatsUtils<Integer> intStats = new StatsUtils<>(ints);
        System.out.println("avg int is " + intStats.avg());

        StatsUtils<Double> doubleStats = new StatsUtils<>(doubles);
        System.out.println("avg double is " + doubleStats.avg());

        System.out.println("avg is same " + intStats.isAvgSame(doubleStats));
    }
}
