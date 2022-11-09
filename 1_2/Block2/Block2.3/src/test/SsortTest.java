package test;

import java_class.SelectionSortGeneric;
import org.junit.Before;
import org.junit.Test;

import java_class.SelectionSortGeneric;

public class SsortTest {
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
        SelectionSortGeneric is = new SelectionSortGeneric();
        Integer[] a = Iarr;
        is.selectionSort(a);
    }
    @Test
    public void testDoublesort()
    {
        SelectionSortGeneric is = new SelectionSortGeneric();
        Double[] a = Darr;
        is.selectionSort(a);
    }
    @Test
    public void testStringsort()
    {
        SelectionSortGeneric is = new SelectionSortGeneric();
        String[] a = Sarr;
        is.selectionSort(a);
    }
    @Test
    public void testCharsort()
    {
        SelectionSortGeneric is = new SelectionSortGeneric();
        Character[] a = Carr;
        is.selectionSort(a);
    }

}
