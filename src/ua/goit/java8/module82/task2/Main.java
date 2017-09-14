package ua.goit.java8.module82.task2;

import static ua.goit.java8.module82.utils.Utils.getArrayMultiThread;
import static ua.goit.java8.module82.utils.Utils.getArraySingleThread;

/**
 * Created by t.oleksiv on 14/09/2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        // getArrayMultiThread (багатопоточний метод) працює довше
        //int[] array = getArrayMultiThread(80000000);

        // заповнюєм масив одним потоком
        int[] array = getArraySingleThread(80000000);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Filling Array duration: " + duration);

    }

    private double getSinCos(int x){
        return Math.sin(x) + Math.cos(x);
    }
}
