package myTree;

import java.util.Arrays;

public class TestTree {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.add(8);
        tree.add(6);
        tree.add(20);
        tree.add(4);
        tree.add(7);
        tree.add(18);
        tree.add(21);
        tree.add(2);
        tree.add(5);
        tree.add(17);
        tree.add(19);
        tree.add(1);
        tree.add(16);
        tree.printTreeWidth();
        System.out.println();
     //   tree.printTreeDepth();
        tree.remove(17);
        System.out.println();
        tree.printTreeWidth();


    }


}
