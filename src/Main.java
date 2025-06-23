import java.util.Queue;

class SumThread extends Thread {
    private int[] array;
    private int start, end;
    private int partialSum = 0;

    public SumThread(int[] array, int start, int end) {  // SumThread 가 생성될때 필요한 것들
        this.array = array;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
    }
    public int getPartialSum () {
        return partialSum;
    }
}
public class Main {         // 모든 쓰레드가 끝날때 까지 join 으로 대기 한 후 각쓰레드 값을 더해 전체를 구한다.
    public static void main(String[] args) {
        int[] numbers = new int[100];              // {0, 0, 0, ..., 0}

        for (int i = 0; i < numbers.length; i++) { // {1, 2, 3, ...,100}
            numbers[i] = i + 1;
        }

        int numThreads = 4;                          // 쓰레드 몇개로 나눌지
        int chunkSize = numbers.length / numThreads; // 쓰레드 갯수당 몇개

        SumThread[] threads = new SumThread[numThreads]; // 쓰레드의 껍데기만 만듬

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;                                // 0, 25, 50, 75
            int end = (i == numThreads - 1) ? numbers.length : start + chunkSize; // 25, 50, 75, 100

            threads[i] = new SumThread(numbers, start, end);
            threads[i].start();
        }
        int totalSum = 0;
        try {
            for (SumThread thread : threads) {
                thread.join();      // InterruptedException 이 발생해 try-catch 가 필요
                // Queue : 먼저 작업이끝난 스레드부터 계산하려면
                int threadResult = thread.getPartialSum();

                System.out.println("현재 쓰레드의 총합 : " + threadResult);

                totalSum += threadResult;
            }
        } catch (InterruptedException e) {
            System.out.println("Tread interrupted : " + e.getMessage());
        }
        System.out.println("모든 쓰레드의 총합 : " + totalSum);
    }
}