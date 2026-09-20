import java.util.Arrays;

public class MergeSorter {
    private static final int CUTOFF = 15;
    public int maxDepth = 0;
    public long comparisons = 0;

    public void sort(int[] arr) {
        maxDepth = 0;
        comparisons = 0;
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1, 1);
    }

    private void sort(int[] arr, int[] aux, int low, int high, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        if (high <= low + CUTOFF - 1) {
            insertionSort(arr, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        sort(arr, aux, low, mid, depth + 1);
        sort(arr, aux, mid + 1, high, depth + 1);
        merge(arr, aux, low, mid, high);
    }

    private void merge(int[] arr, int[] aux, int low, int mid, int high) {
        System.arraycopy(arr, low, aux, low, high - low + 1);
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            comparisons++;
            if (i > mid) arr[k] = aux[j++];
            else if (j > high) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= low) comparisons++;
            arr[j + 1] = key;
        }
    }
}
