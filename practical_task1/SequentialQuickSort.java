public class SequentialQuickSort {
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = QuickSortUtils.partition(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }
}