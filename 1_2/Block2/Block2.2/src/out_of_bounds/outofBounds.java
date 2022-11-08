public class outofBounds {
    private int[] nums = {0,1,2};
    public  void wBug()
    {
        try{
            System.out.println("Success: " + nums[4]);
        } catch (Exception e) {
            System.out.println("Tried to access out of bounds value of an array.");
        }
    }
    public void corrected()
    {
        try{
            System.out.println("Success: " + nums[1]);
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }
}
