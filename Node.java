package myTree;

public class Node<T> {
    T elem;
    Node<T> left;
    Node<T> right;

    public Node() {
    }

    public Node(T elem) {
        this.elem = elem;
    }

    public Node(T elem, Node<T> left, Node<T> right) {
        this.elem = elem;
        this.left = left;
        this.right = right;
    }
}
