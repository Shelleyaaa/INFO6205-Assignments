/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;
import java.util.Arrays;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        // TO BE IMPLEMENTED
        for (int i = from + 1; i < to; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (helper.compare(xs, j, j + 1) > 0) {
                    helper.swap(xs, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    public static void main(String[] args) throws IOException {
        int nRuns = 1000;
        for (int i = 200; i <= 3200; i *= 2) {
            // random array
            int n = i;
            BaseHelper<Integer> helper = new BaseHelper<>("InsertionSort", n,
                    Config.load(InsertionSort.class));
            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
            Benchmark<Integer[]> bm1 = new Benchmark_Timer<>(
                    "random input insertion sort run time", sorter::preProcess,
                    sorter::sort);
            double x1 = bm1.run(helper.random(Integer.class, r -> r.nextInt(n)), nRuns);
            System.out.println("when n is " + n + ", run time is " + x1);

            // ordered array
            Benchmark<Integer[]> bm2 = new Benchmark_Timer<>(
                    "ordered input insertion sort run time", sorter::preProcess,
                    sorter::sort);
            double x2 = bm2.run(orderedArray(n), nRuns);
            System.out.println("when n is " + n + ", run time is " + x2);


            // partially ordered array
            Benchmark<Integer[]> bm3 = new Benchmark_Timer<>(
                    "partially ordered input insertion sort run time",
                    sorter::preProcess,
                    sorter::sort);
            double x3 = bm3.run(partiallyOrderedNumber(n, helper), nRuns);
            System.out.println("when n is " + n + ", run time is " + x3);


            // reverse ordered array
            Benchmark<Integer[]> bm4 = new Benchmark_Timer<>(
                    "reverse ordered input insertion sort run time", sorter::preProcess,
                    sorter::sort);
            double x4 = bm4.run(reversOrderedNumber(n), nRuns);
            System.out.println("when n is " + n + ", run time is " + x4);

        }
    }

    private static Integer[] orderedArray(int n) {
        Integer[] xs = new Integer[n];
        for (int i = 0; i < n; i++) {
            xs[i] = i;
        }
        return xs;
    }

    private static Integer[] partiallyOrderedNumber(int n, BaseHelper<Integer> helper) {
        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(n));
        Arrays.sort(xs, xs.length / 2, xs.length);
        return xs;
    }

    private static Integer[] reversOrderedNumber(int n) {
        Integer[] xs = new Integer[n];
        for (int i = 0; i < n; i++) {
            xs[i] = n - i - 1;
        }
        return xs;
    }

}
