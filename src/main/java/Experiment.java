import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Experiment {
    private static final Random random = new Random();

    public static void runAll() throws IOException {
        FileWriter csv = new FileWriter("results.csv");
        csv.write("Algorithm,ArrayType,Size,ExecutionTimeNs,MaxDepth,ExtraMetric\n");

        int[] sizes = {1000, 10000, 100000};
        String[] types = {"Random", "Sorted", "Reverse", "Duplicate"};

        for (int size : sizes) {
            for (String type : types) {
                int[] arr = generateArray(size, type);

                // MergeSort
                int[] mergeArr = arr.clone();
                MergeSorter ms = new MergeSorter();
                long startMs = System.nanoTime();
                ms.sort(mergeArr);
                long endMs = System.nanoTime();
                csv.write(String.format("MergeSort,%s,%d,%d,%d,%d\n", type, size, (endMs - startMs), ms.maxDepth, ms.comparisons));

                // QuickSort
                int[] quickArr = arr.clone();
                QuickSorter qs = new QuickSorter();
                long startQs = System.nanoTime();
                qs.sort(quickArr);
                long endQs = System.nanoTime();
                csv.write(String.format("QuickSort,%s,%d,%d,%d,%d\n", type, size, (endQs - startQs), qs.maxDepth, qs.swaps));
            }
        }
        csv.close();
        System.out.println("Experiment results saved to results.csv");
    }

    private static int[] generateArray(int size, String type) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(size);

        if (type.equals("Sorted")) Arrays.sort(arr);
        else if (type.equals("Reverse")) {
            Arrays.sort(arr);
            for (int i = 0; i < size / 2; i++) {
                int temp = arr[i]; arr[i] = arr[size - 1 - i]; arr[size - 1 - i] = temp;
            }
        }
        else if (type.equals("Duplicate")) {
            for (int i = 0; i < size; i++) arr[i] = random.nextInt(5);
        }
        return arr;
    }
}