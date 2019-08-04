import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class ComparePerformance {

    private static final int SIZE = 1000000;
    private static final int RUNS = 500;
    private static final Integer ONE = Integer.valueOf(1);

    static class Run {
        private final List<Integer> list;

        Run(final List<Integer> list) {
            this.list = list;
        }

        public long perform() {
            long oldNanos = System.nanoTime();
            for (int i = 0; i < SIZE; i++) {
                list.add(ONE);
            }

            return System.nanoTime() - oldNanos;
        }
    }

    public static void main(final String[] args) {

        long arrayListTotal = 0L;
        long vectorTotal = 0L;
        for (int i = 0; i < RUNS; i++) {
            if (i % 50 == 49) {
                System.out.println("Run " + (i + 1));
            }

            arrayListTotal += new Run(new ArrayList<Integer>()).perform();
            vectorTotal += new Run(new Vector<Integer>()).perform();
        }

        System.out.println();


        System.out.println("Runs: "+RUNS+", list size: "+SIZE);
        output(arrayListTotal, "List");
        output(vectorTotal, "Vector");
    }

    private static void output(final long value, final String name) {
        System.out.println(name + " total time: " + value + " (" + TimeUnit.NANOSECONDS.toMillis(value) + " " + "ms)");

        long avg = value / RUNS;
        System.out.println(name + " average time: " + avg + " (" + TimeUnit.NANOSECONDS.toMillis(avg) + " " + "ms)");
    }
}