package noClassFound;

public class noClass {

    public void wBug(String a){
        try {
            Class c = Class.forName(a);
            System.out.println("Success");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
    }
}
