package JavaFile;
public class Main {
    public static void main(String[] args) {
        GenericDoublyLinkedList<Sabbath> list = new GenericDoublyLinkedList<Sabbath>();
        list.addLast(new Sabbath(1970,"NIB", "Black_Sabbath"));
        list.addLast(new Sabbath(1970, "The Wizard", "Black_Sabbath"));
        list.addAfter(new Sabbath(1970, "Electric Funeral", "Paranoid"), 1);
        list.addFirst(new Sabbath(1971, "Sweet_Leaf", "Masters_Of_Reality"));

        System.out.println(list.getNode(1).value);
    }
}
