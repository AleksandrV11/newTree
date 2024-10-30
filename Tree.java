package myTree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> implements MyTree<T> {
    private Node<T> root;

    public boolean isEmpty() {
        if (root != null) {
            return true;
        }
        return false;
    }

    @Override
    public void add(T elem) {
        if (!isEmpty()) {
            root = new Node<>(elem); // Устанавливаем корневой узел
        } else {
            addMidl(root, elem);
        }
    }

    @Override
    public void addMidl(Node<T> node, T elem) {
        if (elem.compareTo(node.elem) < 0) {            // їдемо в ліво
            if (node.left == null) {                    // якщо відсутній
                node.left = new Node<>(elem);
            } else {                                    // якщо є
                addMidl(node.left, elem);
            }
        }
        if (elem.compareTo(node.elem) > 0) {     // їдемо у право
            if (node.right == null) {                   // відсутній
                node.right = new Node<>(elem);
            } else {                                    //є
                addMidl(node.right, elem);
            }
        }
    }

    @Override
    public void remove(T elem) {
        if (elem.compareTo(root.elem) == 0) {
            root = null;
            return;
        }
        Node<T> nodeElem = elemDel(elem);
        Node<T> nodeRodElem = rodElemDel(elem);
        if (nodeElem.left == null && nodeElem.right == null) {
            if (nodeRodElem.left.elem == nodeElem.elem) {
                nodeRodElem.left = null;
            } else {
                nodeRodElem.right = null;
            }
            return;
        }
        if (nodeElem.left != null && nodeElem.right == null || nodeElem.left == null && nodeElem.right != null) {
            elementWithOneHeir(elem);
            return;
        }
        if (nodeElem.left != null && nodeElem.right != null) {
            Node<T> tNode = nodeElem.right;
            if (tNode.left == null && tNode.right == null) {
                elementWithTwoChildrenRightWithoutHeir(elem);
            } else
                elementWithTwoDescendantsRightWithDescendants(elem);
        }
    }
    public void elementWithTwoDescendantsRightWithDescendants(T elem) {
        Node<T> min = minNode(elem);
        Node<T> rodMin = rodMinNode(elem);
        Node<T> nodeElem = elemDel(elem);
        min.left = nodeElem.left;
        min.right = nodeElem.right;
        Node<T> nodeRodElem = rodElemDel(elem);
        nodeRodElem.right = min;
        rodMin.left = null;
    }
    public Node rodMinNode(T elem) {
        Node<T> rodMin = null;
        Node<T> nodeElem = elemDel(elem);
        Node<T> rightNodeRodElem = nodeElem.right;
        while (rightNodeRodElem != null) {
            if (rightNodeRodElem.left != null) {
                rodMin = rightNodeRodElem;
                rightNodeRodElem = rightNodeRodElem.left;
            } else return rodMin;
        }
        return null;
    }
    public Node minNode(T elem) {
        Node<T> min = null;
        Node<T> nodeElem = elemDel(elem);
        Node<T> rightNodeRodElem = nodeElem.right;
        while (rightNodeRodElem != null) {
            if (rightNodeRodElem.left != null) {
                min = rightNodeRodElem.left;
                rightNodeRodElem = rightNodeRodElem.left;
            } else return min;
        }
        return null;
    }
    public void elementWithTwoChildrenRightWithoutHeir(T elem) {
        Node<T> nodeElem = elemDel(elem);
        Node<T> nodeRodElem = rodElemDel(elem);
        Node<T> rightNodeRodElem = nodeElem.right;
        if (nodeRodElem.left.elem.compareTo(nodeElem.elem) == 0) {
            rightNodeRodElem.left = nodeElem.left;
            nodeRodElem.left = rightNodeRodElem;
        } else if (nodeRodElem.right.elem.compareTo(nodeElem.elem) == 0) {
            rightNodeRodElem.left = nodeElem.left;
            nodeRodElem.right = rightNodeRodElem;
        }
    }
    public void elementWithOneHeir(T elem) {
        Node<T> nodeElem = elemDel(elem);
        Node<T> nodeRodElem = rodElemDel(elem);
        if (nodeRodElem.left == nodeElem) {
            if (nodeElem.left != null) {
                nodeRodElem.left = nodeElem.left;
            } else nodeRodElem.left = nodeElem.right;
        } else if (nodeRodElem.right == nodeElem) {
            if (nodeElem.left != null) {
                nodeRodElem.right = nodeElem.left;
            } else nodeRodElem.right = nodeElem.right;
        }
    }
    public Node elemDel(T elem) {
        Node<T> nodeElem = root;
        while (nodeElem != null) {
            if (elem.compareTo(nodeElem.elem) < 0) {
                nodeElem = nodeElem.left;
            }
            if (elem.compareTo(nodeElem.elem) > 0) {
                nodeElem = nodeElem.right;
            }
            if (elem.compareTo(nodeElem.elem) == 0) {
                return nodeElem;
            }
        }
        return null;
    }
    public Node rodElemDel(T elem) {
            Node<T> nodeElem = root;
            Node<T> rodNodeElem = root;
            while (nodeElem != null) {
                if (elem.compareTo(nodeElem.elem) < 0) {
                    rodNodeElem = nodeElem;
                    nodeElem = nodeElem.left;
                }
                if (elem.compareTo(nodeElem.elem) > 0) {
                    rodNodeElem = nodeElem;
                    nodeElem = nodeElem.right;
                }
                if (elem.compareTo(nodeElem.elem) == 0) {
                    return rodNodeElem;
                }
            }
        return null;
    }

    @Override
    public void printTreeDepth() {
        if (root == null) {
            System.out.println("Tree pystue ");
        } else {
            printTreeDepth(root);
        }
    }

    public void printTreeDepth(Node<T> tek) {           //обход в глубину РЕКУРСИВНЫЙ
        if (tek == null) {
            return;
        }
        if (tek.left != null) {
            printTreeDepth(tek.left);
        }
        System.out.print(" " + tek.elem + "    ");
        if (tek.right != null) {
            printTreeDepth(tek.right);
        }
    }
    @Override
    public void printTreeWidth() { //в ширіну
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        if (root == null) {
            System.out.println("Tree pystue ");
        } else {
            while (!queue.isEmpty()) {
                Node<T> node = queue.remove();
                System.out.print(" " + node.elem + "    ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
}
