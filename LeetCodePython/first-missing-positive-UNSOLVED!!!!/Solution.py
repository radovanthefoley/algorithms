'''
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
[1,1] return 2,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
'''

class Solution:
    # @param A, a list of integers
    # @return an integer
    def firstMissingPositive(self, A):
        fmpMaxPossible = len(A) + 1
        for num in A:
            if (num <= 0 or num > len(A) + 1):
                fmpMaxPossible -= 1
        
        B = {}
        fmp = 1
        for num in A:
            if (num > 0 and num <= fmpMaxPossible):
                B[num] = 1
            if num == fmp:
                fmp += 1
                while B.has_key(fmp):
                    fmp += 1
        return fmp


if __name__ == '__main__':
    s = Solution()
    print s.firstMissingPositive([1,2,0])
    print s.firstMissingPositive([3,4,-1,1])
    print s.firstMissingPositive([])
    print s.firstMissingPositive([100])
    print s.firstMissingPositive([1,1])
    print s.firstMissingPositive([2,1])