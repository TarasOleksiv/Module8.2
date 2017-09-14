package ua.goit.java8.module82.task1;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by t.oleksiv on 14/09/2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть число А: ");
        double a = sc.nextDouble();
        sc.nextLine();
        System.out.print("Введіть число B: ");
        double b = sc.nextDouble();
        sc.nextLine();
        System.out.print("Введіть символ однієї з наступних операцій ( \"+\" ; \"-\" ; \"*\" ; \"/\" ; \"%\" ; \"==\" ; \"<\" ; \">\" ): ");
        String operation = sc.nextLine();

        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/") || operation.equals("%")){
            OperationDouble operationDouble = new OperationDouble(operation, a, b);
            System.out.println("Результат (" + a + " " + operation + " " + b + " ) = " + getResult(operationDouble));
        } else if (operation.equals("==") || operation.equals("<") || operation.equals(">")){
            OperationBoolean operationBoolean = new OperationBoolean(operation, a, b);
            System.out.println("Результат (" + a + " " + operation + " " + b + " ) = " + getResult(operationBoolean));
        } else {
            System.out.println("Введено недопустимий символ операції. Продовження роботи програми неможливе.");
        }

    }

    private static double getResult(OperationDouble operationDouble){
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

    private static boolean getResult(OperationBoolean operationBoolean){
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
}
