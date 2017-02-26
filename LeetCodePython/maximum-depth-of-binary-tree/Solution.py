#Given a binary tree, find its maximum depth.

#The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

# Definition for a  binary tree node
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def maxDepth(self, root):
        if not root:
            return 0
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1
        

if __name__ == '__main__':
    s = Solution()
    root = TreeNode(0)
    root.left = TreeNode(1)
    root.right = TreeNode(1)
    root.right.left = TreeNode(2)
    root.left.left = TreeNode(2)
    root.left.right = TreeNode(3)
    print s.maxDepth(root)