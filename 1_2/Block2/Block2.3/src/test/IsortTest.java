package test;

import java_class.InsertionSortGeneric;
import org.junit.Before;
import org.junit.Test;

public class IsortTest {
    static Integer[] Iarr;
    static Double[] Darr;
    static String[] Sarr;
    static Character[] Carr;
    @Before
    public void setup()
    {
        Iarr = new Integer[]{66, 42, 1, 6, 4};
        Darr = new Double[]{1.2, 1.1, 1.66, 1.01};
        Sarr = new String[]{"aza", "baz", "sas", "axa"};
        Carr = new Character[]{'v', 'x', 'a', 'd', 'q'};
    }
    @Test
    public void testIntsort()
    {
        InsertionSortGeneric is = new InsertionSortGeneric();
        Integer[] a = Iarr;
        is.insertionSort(a);
    }
    @Test
    public void testDoublesort()
    {
        InsertionSortGeneric is = new InsertionSortGeneric();
        Double[] a = Darr;
        is.insertionSort(a);
    }
    @Test
    public void testStringsort()
    {
        InsertionSortGeneric is = new InsertionSortGeneric();
        String[] a = Sarr;
        is.insertionSort(a);
    }
    @Test
    public void testCharsort()
    {
        InsertionSortGeneric is = new InsertionSortGeneric();
        Character[] a = Carr;
        is.insertionSort(a);
    }

}
