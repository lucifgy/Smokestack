package maxValue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class maxInt {
    Scanner sr = new Scanner(System.in);
    public void wBug(){
        int a = 0;
        System.out.println("Input an Int:(2147483647 max)");
        try {
            a = sr.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Int max value reached.");
        }finally {
            System.out.println(a);
        }
    }
}
