package com.itea.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Программа на Java для обхода двоичного дерева
 * Применяется обход по порядку и печать всех узлов в отсортированном порядке
          1
        /   \
       /     \
      2       3
     / \     / \
    4   5   6   7
           /     \
          8       9
*/
class BinaryTreeDemo {

    public static void main(String[] args) {
        //TODO: построить бинарное дерево
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.right.left.left = new Node(8);
        root.right.right.right = new Node(9);

//        System.out.println(root); // {val:'1', L:{val:'2', L:{val:'4'}, R:{val:'5'}}, R:{val:'3', L:{val:'6', L:{val:'8'}}, R:{val:'7', R:{val:'9'}}}}
        //TODO: обход дерева по порядку
        List<List> nodeTree = treeTraversal(root, new ArrayList(), new ArrayList());
//        System.out.println( nodeTree ); // [[1, 2, 4], [1, 2, 5], [1, 3, 6, 8], [1, 3, 7, 9]]
        //TODO: печать всех узлов в отсортированном порядке
        root.prettyPrint(nodeTree, 5);
    }

    static List<List> treeTraversal(Node node, List path, List<List> nodeTree) {
        if (node != null) { // base case
            path.add(node.value); //TODO: добавить текущий узел в путь
            if (node.left == null && node.right == null) nodeTree.add(Arrays.asList(path.toArray())); //TODO: если листовой узел найден тогда вывести путь

            treeTraversal(node.left, path, nodeTree); // base case
            treeTraversal(node.right, path, nodeTree); // base case

            path.remove(path.size() - 1); //TODO: удалить текущий узел после левого и правого поддерева.
        }
        return nodeTree;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "val:'" + value + "'" +
                (left!=null ? ", L:" + left : "") +
                (right!=null ? ", R:" + right : "") +
                '}';
    }

    void prettyPrint(List<List> nodeTree, int maxDepthNodeTree) {
        List<List> prettyTree = new ArrayList<>();

        for (int depthNodeTree = 0; depthNodeTree < maxDepthNodeTree; depthNodeTree++) {
            List prettyPath = new ArrayList<>();
            for (List path: nodeTree) {
                try {
                    int value = (int) path.get(depthNodeTree);
                    if (!prettyPath.contains(value)) prettyPath.add(value);
                } catch (Exception e) {}
            }
            if (!prettyPath.isEmpty()) prettyTree.add(prettyPath);
        }

        for (List l: prettyTree) System.out.println(l);
    }
}
