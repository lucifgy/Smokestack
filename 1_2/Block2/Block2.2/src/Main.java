public class Main {
    public static void main(String[] args) {
        int[] num = {1,2,3};
        try {
            System.out.println(num[4]);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            System.out.println(num[2]);
        }
    }
}