'''
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
'''

class Solution:
    # @param num, a list of integer
    # @return an integer
    def longestConsecutive(self, arr):
        result = 1
        temp = 1
        arr.sort()
        for i in xrange(len(arr)-1):
            if arr[i] == arr[i+1]:
                continue
            if arr[i]+1 == arr[i+1]:
                temp+=1
                if temp > result:
                    result = temp
            else:
                    temp = 1
        return result

if __name__ == '__main__':
    s = Solution()
    print s.longestConsecutive([100, 4, 200, 201, 1, 3, 2])
    print s.longestConsecutive([0,-1])
    print s.longestConsecutive([1,2,0,1])