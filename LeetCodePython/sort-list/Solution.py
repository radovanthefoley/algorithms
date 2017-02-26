'''
Sort a linked list in O(n log n) time using constant space complexity.
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def sortList(self, head):
        pop = self.popNext(head)
        self.insertNext(head.next, pop)
        return head
    
    def popNext(self, node):
        if node.next is None:
            return None
        pop = node.next
        node.next = node.next.next
        pop.next = None
        return pop
    
    def insertNext(self, node, new):
        new.next = node.next
        node.next = new

if __name__ == '__main__':
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    
    node1.next = node2
    node2.next = node3
    
    s = Solution()
    
    sorted = s.sortList(node1)
    node = sorted
    while node != None:
        print node.val
        node = node.next