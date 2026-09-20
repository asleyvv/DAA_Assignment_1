import java.util.Arrays;

public class DeterministicSelector {
    public int maxDepth = 0;

    public int select(int[] arr, int k) {
        maxDepth = 0;
        return select(arr, 0, arr.length - 1, k, 1);
    }

    private int select(int[] arr, int left, int right, int k, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        if (left == right) return arr[left];

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return select(arr, left, pivotIndex - 1, k, depth + 1);
        } else {
            return select(arr, pivotIndex + 1, right, k, depth + 1);
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) return partition5(arr, left, right);

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(left + i * 5 + 4, right);
            int medianIndex = partition5(arr, subLeft, subRight);
            medians[i] = arr[medianIndex];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private int partition5(int[] arr, int left, int right) {
        Arrays.sort(arr, left, right + 1);
        return left + (right - left) / 2;
    }

    private int partition(int[] arr, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
}