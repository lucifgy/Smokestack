package numberFormat;

public class numberFormat {
    public void wBug(String s)
    {
        try{
            int num = Integer.parseInt(s);
            System.out.println(num);
        } catch (NumberFormatException e)
        {
            System.out.println("Input is not a number.");
        }
    }
}
