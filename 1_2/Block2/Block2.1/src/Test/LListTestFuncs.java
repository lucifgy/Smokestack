package Test;

import JavaFile.GenericDoublyLinkedList;
import JavaFile.Sabbath;
import org.junit.Before;
import org.junit.Test;

public class LListTestFuncs {
    GenericDoublyLinkedList list;
    Sabbath sab = new Sabbath("Lady_Evil", 1980, "Heaven_And_Hell");
    Sabbath sab2 = new Sabbath("The_Thrill_Of_It_All", 1975, "Sabotage");
    @Before
    public void setup(){
        list = new GenericDoublyLinkedList<Sabbath>();
        list.addLast(new Sabbath("NIB", 1970, "Black_Sabbath"));
        list.addLast(new Sabbath("The Wizard", 1970, "Black_Sabbath"));
        list.addAfter(new Sabbath("Electric Funeral", 1970, "Paranoid"), 1);
        list.addFirst(new Sabbath("Sweet_Leaf", 1971, "Masters_Of_Reality"));
    }
    @Test
    public void testAdd()
    {
        list.addAfter(new Sabbath("Fluff", 1973,"Sabbath_Bloody_Sabbath"), 1);
        list.addLast(new Sabbath("War_Pigs", 1970, "Paranoid"));
        list.addBefore(new Sabbath("Iron_Man", 1970, "Paranoid"), 1);
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
}
