class SimpleLinkedList {
    Node head;
    
    void addNode(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node next = head;
            while (next.next != null) {
                next = next.next;
            }
            next.next = new Node(data);
        }
    }
    
    static Node reverse(Node node) {
        
        if(node == null) {
            return null;
        }
        
        //System.out.println("a " + node.data);
        
        if(node.next == null) {
            return node;
        }
        
        //System.out.println("b " + node.data);
        
        Node next = node.next;
        
        Node last = reverse(next);
        
        next.next = node;
        node.next = null;
        
        
        //System.out.println("b " + node.data);
        
        return last;
    }
    
    @Override
    public String toString() {
        if(head == null) return "EMPTY";
        
        String result = "" + head.data;
        Node next = head.next;
        while (next != null) {
            result += " " + next.data;
            next = next.next;
        }
        
        return result;
    }
    
    public static void main(String... args) {
        SimpleLinkedList ll = new SimpleLinkedList();
        
        ll.addNode(0);
        ll.addNode(1);
        ll.addNode(2);
        ll.addNode(3);
        ll.addNode(4);
        
        System.out.println(ll);
        
//        Node middle = ll.head.next.next;
//        System.out.println("middle node ref " + middle.data);
        
//        middle = null;
//        middle = middle.next;
//        System.out.println(ll);
        
//        middle.data = middle.next.data;
//        middle.next = middle.next.next;
//        System.out.println(ll);
        
        ll.head = reverse(ll.head);
        System.out.println(ll);
    }
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
        }
    }
    
}