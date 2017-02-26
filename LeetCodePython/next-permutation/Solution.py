'''
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 -> 1,3,2
3,2,1 -> 1,2,3
1,1,5 -> 1,5,1
'''

class Solution:
    # @param num, a list of integer
    # @return a list of integer
    def nextPermutation(self, num):
        for i in range(1,len(num))[::-1]:
            for j in range(0, i)[::-1]:
                if num[i] > num[j]:
                    for k in range(j,i)[::-1]:
                        num[k], num[k+1] = num[k+1], num[k]
                    return num
        for i in xrange(0,len(num)//2):
            num[i], num[-i-1] = num[-i-1], num[i]
        return num

if __name__ == '__main__':
    s = Solution()
    #num = [3,1]
    num = [1,2,3]
    #num = [1,3,2]
    num = [2,3,1]
    print s.nextPermutation(num)