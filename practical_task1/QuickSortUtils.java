public class QuickSortUtils {
    public static int partition(int[] array, int start, int end) {
        int pivotValue = array[end];
        int index = start - 1;

        for (int current = start; current < end; current++) {
            if (array[current] <= pivotValue) {
                index++;
                int temp = array[index];
                array[index] = array[current];
                array[current] = temp;
            }
        }

        int temp = array[index + 1];
        array[index + 1] = array[end];
        array[end] = temp;

        return index + 1;
    }
}