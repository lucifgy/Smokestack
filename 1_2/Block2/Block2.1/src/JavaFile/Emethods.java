package JavaFile;

public class Emethods<T extends GenericDoublyLinkedList> {
    //Extended Methods:
    //Check if list is empty
    public boolean isEmpty(T list) {
        return list.size() != 0;
    }
    //Get list size
    public int count(T item){
        return item.size();
    }
    //Check index
    public GenericDoublyLinkedList.Node ElementAt(T list, int i){
        return list.getNode(i);
    }
    //Check index or return defaulf
    public GenericDoublyLinkedList.Node elementAtOrDefault(T list, int i) {
        if(i >= list.size())
            return ElementAt(list, 1);
        return list.getNode(i);
    }
    //First element of the list
    public GenericDoublyLinkedList.Node First(T list)
    {
        return list.first();
    }
    //Get the greatest Value in a list
    public GenericDoublyLinkedList.Node Max(T list) {

        int t = 0;
        GenericDoublyLinkedList.Node node = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if((int) list.getNode(i).value > t){
                t = (int) list.getNode(i).value;
                node = list.getNode(i);
            }
        }
        return node;
    }
    //Get the smallest value in a list
    public GenericDoublyLinkedList.Node Min(T list) {
        int t = (int) list.getNode(1).value;
        GenericDoublyLinkedList.Node node = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if((int) list.getNode(i).value < t){
                t = (int) list.getNode(i).value;
                node = list.getNode(i);
            }
        }
        return node;
    }
    //Reverses the given list
    public T reverse(T list) {
        T res = null;

        for (int i = list.size() - 1; i >= 0; i--) {
            res.addLast(list.getNode(i));
        }
        return res;
    }
}
