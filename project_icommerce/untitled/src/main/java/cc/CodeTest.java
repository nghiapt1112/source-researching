package cc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

public class CodeTest {
    public static void main(String[] args) {
        int[] array = new int[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            array[i] = random.nextInt(5);
        }
        int max = array[0];
        int arraySum = array[0];
        for (int i = 1; i < 3; i++) {
            arraySum += array[i];
            if (array[i] > max)
                max = array[i];
        }
        LocalDateTime startTime = LocalDateTime.now();
        int sum = 0;

        // TODO Start, optimise below code to invoke them simultaneously to get the sum rather than calculate it sequentially
        sum = Arrays.stream(array).parallel().map(el -> doSomething(el)).sum();

        System.out.println("Sum value: " + sum);
        // TODO end, it's good not to update below code unless you have no other choices. Manipulating result is considering failed.

        LocalDateTime endTime = LocalDateTime.now();
        boolean result1 = Duration.between(startTime, endTime).get(ChronoUnit.SECONDS) == max;
        System.out.println("result is " + result1);
        boolean result2 = arraySum == sum;
        System.out.println("result2 is " + result2);
    }

    public static int doSomething(int sleepTime) {
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sleepTime;
    }
}