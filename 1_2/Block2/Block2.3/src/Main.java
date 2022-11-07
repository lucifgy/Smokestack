import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SelectionSortGenerics firstsort = new SelectionSortGenerics();

        Integer[] arr = {3,4,1,5};
        System.out.println("before sorting int: "+ Arrays.toString(arr));
        firstsort.selectionSort(arr);
        System.out.println("After sorting int : "+Arrays.toString(arr));
        String[] arr1= {"acd","ded","dal","bad","cle"};
        System.out.println("before sorting String: "+ Arrays.toString(arr1));
        firstsort.selectionSort(arr1);
        System.out.println("After sorting String : "+Arrays.toString(arr1));
        Character[] arr2= {'c','e','a','d','c'};
        System.out.println("before sorting char: "+ Arrays.toString(arr2));
        firstsort.selectionSort(arr2);
        System.out.println("After sorting char : "+Arrays.toString(arr2));
    }
}

