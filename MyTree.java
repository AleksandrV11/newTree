package myTree;

import java.util.ArrayList;

public interface MyTree<T extends Comparable<T>> {
    void add(T elem);

    void addMidl(Node<T> node, T elem);

    void remove(T elem);

    void printTreeDepth();

    void printTreeDepth(Node<T> tek);

    void printTreeWidth();


}
