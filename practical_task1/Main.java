import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int ARRAY_SIZE = 100_000_000;
    private static final int PARALLEL_THRESHOLD = 1_000;
    private static final int NUM_TRIALS = 5;

    public static void main(String[] args) {
        long totalSeqTime = 0;
        long totalParTime = 0;

        for (int trial = 1; trial <= NUM_TRIALS; trial++) {
            int[] sequentialArray = generateRandomArray();
            int[] parallelArray = sequentialArray.clone();

            totalSeqTime += measureSequentialSort(sequentialArray, trial);
            totalParTime += measureParallelSort(parallelArray, trial);
        }

        System.out.println("\nСреднее время последовательной сортировки: " + (totalSeqTime / NUM_TRIALS) + " мс");
        System.out.println("Среднее время параллельной сортировки: " + (totalParTime / NUM_TRIALS) + " мс");
    }

    private static long measureSequentialSort(int[] array, int trial) {
        long startTime = System.currentTimeMillis();
        SequentialQuickSort.quickSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();

        long seqTime = endTime - startTime;
        System.out.printf("Запуск %d: Время последовательной сортировки: %d мс%n", trial, seqTime);

        return seqTime;
    }

    private static long measureParallelSort(int[] array, int trial) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        long startTime = System.currentTimeMillis();
        forkJoinPool.invoke(new ParallelQuickSort(array, 0, array.length - 1, PARALLEL_THRESHOLD));
        long endTime = System.currentTimeMillis();

        long parTime = endTime - startTime;
        System.out.printf("Запуск %d: Время параллельной сортировки: %d мс%n", trial, parTime);

        return parTime;
    }

    private static int[] generateRandomArray() {
        int[] array = new int[ARRAY_SIZE];
        Random randomGenerator = new Random();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = randomGenerator.nextInt();
        }

        return array;
    }
}