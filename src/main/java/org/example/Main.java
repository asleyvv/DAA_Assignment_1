import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Running DAA Assignment 1 Correctness Tests...");

        // 1. Sorting Tests
        int[] arr = {10, 7, 8, 9, 1, 5, 2, 2, 8};

        MergeSorter ms = new MergeSorter();
        int[] msArr = arr.clone();
        ms.sort(msArr);

        QuickSorter qs = new QuickSorter();
        int[] qsArr = arr.clone();
        qs.sort(qsArr);

        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println("MergeSort: " + Arrays.toString(msArr));
        System.out.println("QuickSort: " + Arrays.toString(qsArr));

        // 2. Select Test
        DeterministicSelector ds = new DeterministicSelector();
        int[] dsArr = arr.clone();
        int k = 4; // 5th smallest element
        int kthElement = ds.select(dsArr, k);
        Arrays.sort(arr); // Reference
        System.out.println("Select index " + k + ": " + kthElement + " (Expected: " + arr[k] + ")");

        // 3. Closest Pair Test
        Point[] points = {
                new Point(2, 3), new Point(12, 30), new Point(40, 50),
                new Point(5, 1), new Point(12, 10), new Point(3, 4)
        };
        ClosestPairSolver cps = new ClosestPairSolver();
        double minDist = cps.findClosestPair(points);
        double expectedDist = cps.bruteForce(points, 0, points.length - 1);
        System.out.println("Closest Pair Dist: " + minDist + " (Expected: " + expectedDist + ")");

        // Run Metrics & Experiments
        System.out.println("\nRunning Scale Experiments...");
        Experiment.runAll();
    }
}