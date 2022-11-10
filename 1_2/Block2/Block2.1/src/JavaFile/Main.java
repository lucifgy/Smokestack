package JavaFile;
public class Main {
    public static void main(String[] args) {
        GenericDoublyLinkedList<Sabbath> list = new GenericDoublyLinkedList<Sabbath>();
        list.addLast(new Sabbath("NIB", 1970, "Black_Sabbath"));
        list.addLast(new Sabbath("The Wizard", 1970, "Black_Sabbath"));
        list.addAfter(new Sabbath("Electric Funeral", 1970, "Paranoid"), 1);
        list.addFirst(new Sabbath("Sweet_Leaf", 1971, "Masters_Of_Reality"));
        System.out.println(list);
    }
}
