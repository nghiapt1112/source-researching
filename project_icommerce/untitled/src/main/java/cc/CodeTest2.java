package cc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeTest2 {
    public static void main(String[] args) {
        int[] array = new int[3000];
        Random random = new Random();
        for (int i = 0; i < 3000; i++) {
            array[i] = random.nextInt(53);
            Arrays.sort(array);
        }
        int max = array[0];
        int arraySum = 0;
        for (int i = 1; i < 3; i++) {
            arraySum += array[i];
            if (array[i] > max)
                max = array[i];
        }
        Arrays.stream(array).forEach(System.out::println);
        LocalDateTime startTime = LocalDateTime.now();

        CodeTest2 codeTest = new CodeTest2();
        // TODO start - optimise the below code so that result is true.
        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        Future<Integer> future1 = executorService.submit(() -> codeTest.doSomething(array[0]));
//        Future<Integer> future2 = executorService.submit(() -> codeTest.doSomething(array[1]));
//        Future<Integer> future3 = executorService.submit(() -> codeTest.doSomething(array[2]));
//
//        List<Future<Integer>> listFuture = new ArrayList<>();
//        listFuture.add(future1);
//        listFuture.add(future2);
//        listFuture.add(future3);
        int sum = 0;
        AtomicInteger aSum = new AtomicInteger(0);
        var arrCf = new CompletableFuture[3];
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            arrCf[i] = CompletableFuture
                    .supplyAsync(() ->  CodeTest.doSomething(array[finalI]))
                    .thenAccept(aSum::addAndGet);
        }
        CompletableFuture<Void> combinedFuture = CompletableFuture
                .allOf(arrCf);
        try {
            combinedFuture.get();
            sum = aSum.intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        }
        int sum = 0;

        for (Future<Integer> future : listFuture) {
            try {
                sum += future.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum value: " + sum);

        // TODO end

        // We expect both result and result2 are true
        LocalDateTime endTime = LocalDateTime.now();
        boolean result = Duration.between(startTime, endTime).get(ChronoUnit.SECONDS) == max;
        System.out.println("Max value: " + max);


        System.out.println("result is " + result);
        boolean result2 = arraySum == sum;
        System.out.println("result2 is " + Duration.between(startTime, endTime).toMillis());

    }

    public int doSomething(int sleepTime) {
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sleepTime;
    }
}