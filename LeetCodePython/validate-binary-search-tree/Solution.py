"""
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
"""
# Definition for a  binary tree node
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def __init__(self):
        self.order = []
    # @param root, a tree node
    # @return a boolean
    def isValidBST(self, root):
        self.inOrderTraversal(root)
        for i in xrange(1, len(self.order)):
            if self.order[i] <= self.order[i-1]:
                return False
        return True 
    
    def inOrderTraversal(self, root):
        if not root: return
        self.inOrderTraversal(root.left)
        self.order.append(root.val)
        self.inOrderTraversal(root.right)

if __name__ == '__main__':
    s = Solution()
    root = TreeNode(4)
    root.left = TreeNode(2)
    root.right = TreeNode(6)
    root.right.left = TreeNode(5)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)
    print s.isValidBST(root)