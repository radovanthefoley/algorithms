'''
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
'''
class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if not A:
            return 0
        j = 0
        for i in xrange(1, len(A)):
            if A[j] != A[i]:
                A[j+1] = A[i]
                j+=1
        return j+1

if __name__ == '__main__':
    A = [1,2,2,2,2,3,3]
    s = Solution()
    print s.removeDuplicates(A)