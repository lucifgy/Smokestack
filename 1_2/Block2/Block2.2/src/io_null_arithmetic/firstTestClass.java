package io_null_arithmetic;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class firstTestClass {
    Path path;
    String s = null;

    @Before
    public void setup(){
        path = Paths.get("src/list.txt"); //src/intList.txt
        //s = "123\n213\n412\n213";
    }
    @Test //IOException
    public void fileTest() throws IOException {
        String s = null;
        s = Files.readString(path, StandardCharsets.UTF_8);
    }
    @Test //String null
    public void nullstrTest()
    {
        String[] as = s.split("\\r?\\n");
        int[] arr = new int[as.length];
        for (int i = 0; i < as.length; i++) {
            arr[i] = Integer.parseInt(as[i]);
        }
    }
    @Test
    public void chanceTest()
    {
        chance(1,3);
        chance(3,1);
        chance(0,0);
        chance(1,0);
        chance(0,1);
    }
    public static double chance(int a, int b)
    {
        return a / (a * b);
    }
}
