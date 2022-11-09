package java_class;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {2,1,6,5};

        Sort(array, "Insertion");

        System.out.println(Arrays.toString(array));
    }
    public static <E extends Comparable<E>> void Sort(E[] arr, String sortType)
    {
        switch (sortType)
        {
            case "Selection":
                SelectionSortGeneric ss = new SelectionSortGeneric();
                ss.selectionSort(arr);
            case "Merge":
                MergeSortGeneric ms = new MergeSortGeneric();
                ms.mergeSort(arr, 0, arr.length - 1);
            case "Insertion":
                InsertionSortGeneric is = new InsertionSortGeneric();
                is.insertionSort(arr);
            default:
                System.out.println("Sort type not found.");
        }
    }
}

