package Test;

import JavaFile.Emethods;
import JavaFile.GenericDoublyLinkedList;
import JavaFile.Sabbath;
import org.junit.Before;
import org.junit.Test;

public class LListTestFuncs {
    GenericDoublyLinkedList list;
    Emethods funcs;
    Sabbath sab = new Sabbath( 1980,"Lady_Evil", "Heaven_And_Hell");
    Sabbath sab2 = new Sabbath(1975, "The_Thrill_Of_It_All", "Sabotage");
    @Before
    public void setup(){
        list = new GenericDoublyLinkedList<Sabbath>();
        list.addLast(new Sabbath(1970,"NIB", "Black_Sabbath"));
        list.addLast(new Sabbath(1970, "The Wizard", "Black_Sabbath"));
        list.addAfter(new Sabbath(1970, "Electric Funeral", "Paranoid"), 1);
        list.addFirst(new Sabbath(1971, "Sweet_Leaf", "Masters_Of_Reality"));
    }
    @Test
    public void testAdd()
    {
        list.addAfter(new Sabbath(1973, "Fluff","Sabbath_Bloody_Sabbath"), 1);
        list.addLast(new Sabbath(1970, "War_Pigs", "Paranoid"));
        list.addBefore(new Sabbath(1970, "Iron_Man", "Paranoid"), 1);
        list.addFirst(sab);
    }
    @Test
    public void testDelete()
    {
        list.deleteFirst();
        list.deleteLast();
        list.delete(0);
        list.delete(sab);
    }
    @Test
    public void testClear()
    {
        list.clear();
    }
    @Test
    public void testFind()
    {
        list.find(sab);
        list.findLast(sab);
    }
    @Test
    public void testBase() {
        list.first();
        list.last();
        list.size();
    }
    //Extention methods
    @Test
    public void testEmpty() {
        funcs.isEmpty(list);
    }
    @Test
    public void testItemCount()
    {
        funcs.count(list);
    }
    @Test
    public void testElementAt()
    {
        funcs.ElementAt(list, 2);
        funcs.elementAtOrDefault(list, 10);
    }
    @Test
    public void testFirstMinMax()
    {
        funcs.First(list);
        funcs.Max(list);
        funcs.Min(list);
    }
    public void testReverse(){
        funcs.reverse(list);
    }
}
