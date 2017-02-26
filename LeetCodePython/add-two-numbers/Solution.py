'''
You are given two linked lists representing two non-negative numbers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
'''
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # @return a ListNode
    def addTwoNumbers(self, l1, l2):
        sum, rank = 0, 1
        while l1 is not None or l2 is not None:
            if l1 is not None:
                sum += l1.val * rank
                l1 = l1.next
            if l2 is not None:
                sum += l2.val * rank
                l2 = l2.next
            rank *= 10
        
        result = node = ListNode(sum%10)
        sum//=10
        
        while sum:
            node.next = ListNode(sum%10)
            node = node.next
            sum//=10
        
        return result

if __name__ == '__main__':
    s = Solution()
    l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next.next = ListNode(3)
    l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next.next = ListNode(4)
    result = s.addTwoNumbers(l1, l2)
    while result is not None:
        print result.val
        result = result.next