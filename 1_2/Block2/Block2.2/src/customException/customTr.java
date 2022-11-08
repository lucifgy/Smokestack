package customException;

public class customTr {
    public void func(int a) throws customEx
    {
        if(a == 5)
        {
            throw new customEx("Input cannot be 5");
        }
        else {
            System.out.println(a);
        }
    }
}
