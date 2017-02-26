
public class BST {
    
    private Node root;
    
    public void insert(int value) {
        root = insert(root, value);
    }
    
    private Node insert(Node node, int value) {
        if(node == null) {
            return new Node(value);
        }
        if(value < node.value) {
            node.left = insert(node.left, value);
        } else if(value > node.value) {
            node.right = insert(node.right, value);
        }
        return node;
    }
    
    private class Node {
        int value;
        Node left;
        Node right;
        
        Node(int value) {
            this.value = value;
        }
    }
    
    public void traverseInOrder() {
        if(root != null) traverseInOrder(root);
    }
    
    private void traverseInOrder(Node node) {
        
        if(node == null) return;
        traverseInOrder(node.left);
        System.out.println(node.value);
        traverseInOrder(node.right);
    }
    
    public void printAllHeights() {
        if(root == null) System.out.println(0);
        else printAllHeights(root, 1);
    }
    
    private void printAllHeights(Node node, int height) {
        if(node == null){
            System.out.println(height);
            return;
        }
        printAllHeights(node.left, height + 1);
        printAllHeights(node.right, height + 1);
    }
    
    public static void main(String... args) {
        BST bst = new BST();
        bst.insert(2);
        bst.insert(2);
        bst.insert(0);
        bst.insert(4);
        bst.insert(1);
        bst.insert(4);
        bst.insert(3);
        
        bst.printAllHeights();
        //bst.traverseInOrder();
        
        System.out.println();
    }
}
