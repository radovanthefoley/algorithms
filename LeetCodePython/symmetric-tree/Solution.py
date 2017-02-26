# Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

# Definition for a  binary tree node
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # @param root, a tree node
    # @return a boolean
    def isSymmetric(self, root):
        if not root:
            return True
        left, right = [], []
        self.traverseLeft(root.left, left)
        self.traverseRight(root.right, right)
        return not cmp(left, right)
        
    def traverseLeft(self, root, result):
        if not root:
            result.append('#')
            return
        result.append(root.val)
        self.traverseLeft(root.left, result)
        self.traverseLeft(root.right, result)
        
    def traverseRight(self, root, result):
        if not root:
            result.append('#')
            return
        result.append(root.val)
        self.traverseRight(root.right, result)
        self.traverseRight(root.left, result)

def main():
    s = Solution()
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.left = TreeNode(3)
    #root.left.right = TreeNode('2')
    root.right.left = TreeNode(2)
    #root.left.right.right = TreeNode('left.right.right')
    #root.left.left.right = TreeNode('left.left.right')
    print s.isSymmetric(root)

if __name__ == "__main__":
    main()