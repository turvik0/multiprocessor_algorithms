import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort extends RecursiveAction {

    private final int[] array;
    private final int start;
    private final int end;
    private final int cutoff;

    public ParallelQuickSort(int[] array, int start, int end, int cutoff) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.cutoff = cutoff;
    }

    @Override
    protected void compute() {
        if (end - start < cutoff) {
            SequentialQuickSort.quickSort(array, start, end);
        } else {
            int pivotIndex = QuickSortUtils.partition(array, start, end);
            ParallelQuickSort leftTask = new ParallelQuickSort(array, start, pivotIndex - 1, cutoff);
            ParallelQuickSort rightTask = new ParallelQuickSort(array, pivotIndex + 1, end, cutoff);
            invokeAll(leftTask, rightTask);
        }
    }
}
