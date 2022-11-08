package nullPointer;

public class nullPointer {
    String a = null;
    public void wBug()
    {
        try {
            System.out.println(a.length());
        }catch (NullPointerException e){
            System.out.println("Init the variable first!");
        }
    }
    public void corrected()
    {
        a = "";
        try {
            System.out.println(a.length());
        }catch (NullPointerException e){
            System.out.println("Variable is null");
        }
    }
}
