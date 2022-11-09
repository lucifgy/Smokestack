package test;

import java_class.MergeSortGeneric;
import org.junit.Before;
import org.junit.Test;

public class MsortTest {
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
        MergeSortGeneric is = new MergeSortGeneric();
        Integer[] a = Iarr;
        is.mergeSort(a, 0, a.length - 1);
    }
    @Test
    public void testDoublesort()
    {
        MergeSortGeneric is = new MergeSortGeneric();
        Double[] a = Darr;
        is.mergeSort(a, 0, a.length - 1);
    }
    @Test
    public void testStringsort()
    {
        MergeSortGeneric is = new MergeSortGeneric();
        String[] a = Sarr;
        is.mergeSort(a, 0, a.length - 1);
    }
    @Test
    public void testCharsort()
    {
        MergeSortGeneric is = new MergeSortGeneric();
        Character[] a = Carr;
        is.mergeSort(a, 0, a.length - 1);
    }

}
