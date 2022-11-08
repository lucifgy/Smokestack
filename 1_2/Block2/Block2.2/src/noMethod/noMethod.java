package noMethod;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Set;

public class noMethod {
    public void wBug(){
        Set hs = new HashSet();

        try {
            hs.iterator().next();
            System.out.println("Success");
        }catch (NoSuchElementException e)
        {
            System.out.println("No element in set");
        }
    }
    public void corrected()
    {
        Set hs = new HashSet();
        hs.add("a");
        try {
            hs.iterator().next();
            System.out.println("Success");
        }catch (NoSuchElementException e)
        {
            System.out.println(e);
        }
    }
}
