package io_null_arithmetic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class First {
    public static void main(String[] args) {
        Path path = Paths.get("src/intList.txt");
        String s = null;
        int len = 0;
        int[] arr = new int[0];
        try {
            s = Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Wrong file");
        }

        try {
            String[] as = s.split("\\r?\\n");
            len = as.length;
            arr = new int[len];
            for (int i = 0; i < len; i++) {
                try {
                    arr[i] = Integer.parseInt(as[i]);
                    System.out.println(arr[i]);
                } catch (NumberFormatException e) {
                    System.out.println("line " + i + " is not int");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("String is null");
        }
        for (int i = 1; i < len; i++) {
            try {
                System.out.println(chance(arr[i + 1],  arr[0]));
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println(chance(arr[len],  arr[0]));
            }
        }
    }
    public static double chance(int a, int b)
    {
        double d;
        try {
            d = a / (a * b);
        }catch (ArithmeticException e) {
            d = 0.0;
        }
        return d;
    }
    public static int calcArea(int radius)
    {
        return radius * radius;
    }
}