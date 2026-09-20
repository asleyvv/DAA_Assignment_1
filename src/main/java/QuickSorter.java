import java.util.Random;

public class QuickSorter {
    public int maxDepth = 0;
    public long swaps = 0;
    private Random random = new Random();

    public void sort(int[] arr) {
        maxDepth = 0;
        swaps = 0;
        sort(arr, 0, arr.length - 1, 1);
    }

    private void sort(int[] arr, int low, int high, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        while (low < high) {
            int pivotIndex = partition(arr, low, high);

            // Recurse on the smaller partition, iterate on the larger
            if (pivotIndex - low < high - pivotIndex) {
                sort(arr, low, pivotIndex - 1, depth + 1);
                low = pivotIndex + 1;
            } else {
                sort(arr, pivotIndex + 1, high, depth + 1);
                high = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] arr, int low, int high) {
        int randPivot = low + random.nextInt(high - low + 1);
        swap(arr, randPivot, high);
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j) swaps++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
