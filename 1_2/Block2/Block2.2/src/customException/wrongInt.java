package customException;

public class wrongInt {
    public void wBug(int i)
    {
        customTr t = new customTr();
        try {
            t.func(i);
        }catch (customEx e)
        {
            System.out.println("Input cannot be 5.");
        }
    }
}
