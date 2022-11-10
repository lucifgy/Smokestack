package JavaFile;
public class GenericDoublyLinkedList<T> {
    class Node {
        T value;
        Node next;
        Node previous;
        Node() {
        }

        Node(Node next, Node previous, T value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }

        public String toString() {
            return value + "";
        }
    }
    Node header;
    public GenericDoublyLinkedList() {
        header = new Node(null, null, null);
    }

    //Base
    //Return the first element
    public Node first(){
        return getNode(0);
    }
    //Return the last element
    public Node last()
    {
        return getNode(size()-1);
    }
    //Get count
    public int size() {
        int count = 0;
        Node node = header.next;
        while (node != null) {
            ++count;
            node = node.next;
        }
        return count;
    }

    //Methods
    //Add anywhere after the given index
    public Node addAfter(T item, int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("pos = " + index + " does not exist");
        }

        Node nodeIns = null;

        if (item != null) {
            Node node = header;

            if (node.next != null) {
                for (int i = 0; i <= index; i++) {
                    node = node.next;
                }
            }

            nodeIns = new Node(node.next, node, item);

            if (node.next != null) {
                node.next.previous = nodeIns;
            }
            node.next = nodeIns;
        }
        return nodeIns;
    }
    //Add anywhere before the given index. //Test needed.
    public Node addBefore(T item, int index){
        //if (index >= size() || index < 0) {throw new IndexOutOfBoundsException("pos = " + index + " does not exist");}

        Node nodeIns = null;

        if (item != null) {
            Node node = header;

            if (node.previous != null) {
                for (int i = 0; i <= index; i++) {
                    node = node.previous;
                }
            }

            nodeIns = new Node(node.previous, node, item);

            if (node.previous != null) {
                node.next.next = nodeIns;
            }
            node.previous = nodeIns;
        }
        return nodeIns;
    }
    //Add to beginning of the list
    public Node addFirst(T item) {
        Node n = new Node(header.next, header, item);
        if (header.next != null) {
            header.next.previous = n;
        }
        header.next = n;
        return n;
    }
    //Add to end of the list
    public Node addLast(T item) {
        Node end = getNode(size() - 1);
        Node next = new Node(null, end, item);
        end.next = next;
        return next;
    }
    //Purge list //Test needed.
    public void clear(){
        Node temp = new Node();
        while(this.header != null){
            temp = this.header;
            this.header = this.header.next;
            temp = null;
        }
    }
    //Checks if given index has the given value
    public boolean contains(String value, int i) {
        return value.equals(get(i));
    }
    //Checks if given object is equal to the current object
    public boolean equals(Object o){
        //Overwritten in Class
        return false;
    }
    //Returns the index of found item
    public int find(T item) {
        if (item == null)
            return -1;
        Node currentNode = header;
        int i = -1;
        while (currentNode.next != null) {
            ++i;
            currentNode = currentNode.next;
            if (item.equals(currentNode.value))
                return i;
        }
        return -1;
    }
    //Returns the index of last node
    public int findLast(T item){
        return size()-1;
    }
    //Remove item at given index
    public boolean delete(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("pos = " + index + " does not exist");
        }
        Node toBeRemoved = getNode(index);
        if (toBeRemoved == null)
            return false;
        Node previous = toBeRemoved.previous;
        Node next = toBeRemoved.next;

        previous.next = next;
        if (next != null) {
            next.previous = toBeRemoved.previous;
        }
        toBeRemoved = null;
        return true;
    }
    //Remove item
    public boolean delete(T item) {
        int index = find(item);
        if (index == -1)
            return false;
        return delete(index);

    }
    //Delete the first item
    public Node deleteFirst() {
        Node temp = getNode(0);
        if (temp != null && temp.next != null) {
            temp.next.previous = header;
            header.next = temp.next;
        } else {
            header.next = null;
        }

        return temp;
    }
    //Delete the last item
    public Node deleteLast() {
        Node temp = getNode(size() - 1);
        Node newLast = getNode(size() - 2);
        newLast.next = null;
        return temp;
    }

    //Helpers:
    //Get node at index
    private Node getNode(int index) {
        if (index > size())
            throw new IndexOutOfBoundsException();
        Node node = header;
        for (int i = 0; i <= index; ++i) {
            node = node.next;
        }
        return node;
    }
    //Get the value at index
    private String get(int index) {
        Node node = getNode(index);
        return (node != null) ? node.value.toString() : null;
    }
    //Build string in array format
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node next = header.next;
        while (next != null) {
            sb.append(next.value + ", ");
            next = next.next;
        }
        String ret = sb.toString();
        if (ret.length() > 1) {
            ret = ret.substring(0, ret.length() - 2);
        }
        return (ret + "]");
    }
}