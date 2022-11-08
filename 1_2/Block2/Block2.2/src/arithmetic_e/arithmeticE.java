package arithmetic_e;

public class arithmeticE {
    int a = 3;
    int b = 0;
    int c = 0;
    public void wBug(){
        try {
            c = a/b;
        }catch (ArithmeticException e){
            System.out.println("Error: Cannot divide.");
        }
        finally {
            System.out.println(c);
        }
    }
    public void corrected()
    {
        if (a == 0)
            a++;
        if (b == 0)
            b++;
        try {
            c = a/b;
        } catch (ArithmeticException e){
            System.out.println("Error: Cannot divide.");
        } finally {
            System.out.println(c);
        }
    }
}
