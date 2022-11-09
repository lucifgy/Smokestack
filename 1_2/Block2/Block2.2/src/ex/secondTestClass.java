package ex;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class secondTestClass {

    @Before
    public void setup()
    {

    }

    @Test
    public void testMerge()
    {
        String r = Second.mergestr("ab", "ba");
        Assert.assertEquals("abba", r);
    }
    @Test
    public void testStrToArray()
    {
        String str1 = "1\n2\n3\n";
        String str2 = "123";

        int[] arr1 = Second.strTOintArr(str1);
        int[] arr2 = Second.strTOintArr(str2);

        Assert.assertEquals(new int[]{1, 2, 3}, arr1);
        Assert.assertEquals(new int[]{1, 2, 3}, arr2);
    }
    @Test
    public void testArraySum(){
        int[] i = {1,2,3};
        Assert.assertEquals(6,Second.getArraySum(i));
    }
    @Test
    public void testStrToFile()
    {
        Second.string_to_file("Hello!");
        Assert.assertEquals("Hello!", Second.file_to_string("src/out.txt"));
    }

}
