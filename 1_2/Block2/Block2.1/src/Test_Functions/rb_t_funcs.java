package Test_Functions;

import red_black_tree.RedBlackTree;

public class rb_t_funcs{

    public void add_test()
    {
        RedBlackTree tree = new RedBlackTree<>();
        System.out.println("Adding numbers 1 to 10");
        for (int i = 1; i < 11; i++) {
            tree.add(i);
        }
        System.out.println("Tree after addition:");
        tree.printTree();
    }

    public void remove_test()
    {
        RedBlackTree tree = new RedBlackTree<>();
        tree.add(2);
        tree.add(5);
        tree.add(1);
        tree.add(6);
        tree.add(7);
        System.out.println("Tree before removing element 5:");
        tree.printTree();
        tree.remove(5);
        System.out.println("Tree after removal:");
        tree.printTree();
    }
    //TODO: Test functions, files..
}
