package ex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Second {
    public static void main(String[] args) {
        String intList = file_to_string("src/intList.txt");
        String mixedList = file_to_string("src/mixedList.txt");
        String strList = file_to_string("src/strList.txt");

        String str = mergestr(mixedList,strList) + getArraySum(strTOintArr(intList));

        System.out.println(str);
        string_to_file(str);

    }
    public static int getArraySum(int a[])
    {
        int n = 0;
        for (int i = 0; i < a.length; i++) {
            n =+ a[i];
        }
        return n;
    }
    public static String mergestr(String a, String b)
    {
        return a + b;
    }
    public static int[] strTOintArr(String s)
    {
        String[] as = s.split("\\r?\\n");
        int[] arr = new int[as.length];
        for (int i = 0; i < as.length; i++)
            arr[i] = Integer.parseInt(as[i]);
        return arr;
    }
    public static String file_to_string(String str)
    {
        Path path = Paths.get(str);
        String s = null;
        int len = 0;
        int[] arr = new int[0];
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
    public static void string_to_file(String str){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/out.txt"));
            bw.write(str);
            bw.close();
        }catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}