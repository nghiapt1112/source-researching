package com.demo.Icommerce;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CodeTest {
    public static void main(String[] args) {
        int[] array = new int[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            array[i] = random.nextInt(5);
            Arrays.sort(array);
        }
        int max = array[0];
        int arraySum = 0;
        for (int i = 1; i < 3; i++) {
            arraySum += array[i];
            if (array[i] > max)
                max = array[i];
        }
        LocalDateTime startTime = LocalDateTime.now();

        CodeTest codeTest = new CodeTest();
        // TODO start - optimise the below code so that result is true.
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> future1 = executorService.submit(() -> codeTest.doSomething(array[0]));
        Future<Integer> future2 = executorService.submit(() -> codeTest.doSomething(array[1]));
        Future<Integer> future3 = executorService.submit(() -> codeTest.doSomething(array[2]));

        List<Future<Integer>> listFuture = new ArrayList<>();
        listFuture.add(future1);
        listFuture.add(future2);
        listFuture.add(future3);

        int sum = 0;

        for (Future<Integer> future : listFuture) {
            try {
                sum += future.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        // TODO end

        // We expect both result and result2 are true
        LocalDateTime endTime = LocalDateTime.now();
        boolean result = Duration.between(startTime, endTime).get(ChronoUnit.SECONDS) == max;
        System.out.println("result is " + result);
        boolean result2 = arraySum == sum;
        System.out.println("result2 is " + result2);
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