package ua.goit.java8.module82.utils;

import ua.goit.java8.module82.task1.OperationBoolean;
import ua.goit.java8.module82.task1.OperationDouble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * Created by t.oleksiv on 14/09/2017.
 */
public class Utils {
    public static double getResult(OperationDouble operationDouble){
        FutureTask<Double> futureTask = new FutureTask<Double>(operationDouble);

        Thread thread = new Thread(futureTask);
        System.out.println("Thread started...");
        thread.start();
        double result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished.");
        return result;
    }

    public static boolean getResult(OperationBoolean operationBoolean){
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(operationBoolean);

        Thread thread = new Thread(futureTask);
        System.out.println("Thread started...");
        thread.start();
        boolean result = false;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished.");
        return result;
    }

    // заповнення ArrayList - працює дуже довго
    public static ArrayList<Integer> getArrayList(int size) {
        System.out.println("Started filling ArrayList...");
        final ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i+1);
        }
        System.out.println("Finished filling ArrayList.");
        return result;
    }


    // заповнення масиву багатьма потоками - працює довше
    public static int[] getArrayMultiThread(int size) throws InterruptedException {
        int coreCount = Runtime.getRuntime().availableProcessors();
        int length = size / coreCount;
        System.out.println("Started filling Array...");
        final int[] result = new int[size];

        List<Thread> threads = new ArrayList<>(coreCount);

        for(int i = 0; i < coreCount; i++) {
            final int left = length * i;
            final int right = left + length;

            Thread thread = new Thread(()->{
                for (int j = left; j < right; j++) {
                    result[j] = j+1;
                }
            });
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) thread.join();

        System.out.println("Finished filling Array.");
        return result;
    }

    // заповнення масиву одним потоком - працює швидше
    public static int[] getArraySingleThread(int size){
        System.out.println("Started filling Array...");
        final int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i+1;
        }
        System.out.println("Finished filling Array.");
        return result;
    }

}


