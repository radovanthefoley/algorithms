#Suppose a sorted array is rotated at some pivot unknown to you beforehand.
#
#(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
#
#Find the minimum element.
#
#You may assume no duplicate exists in the array.

class Solution:
    # @param num, a list of integer
    # @return an integer
    def findMin(self, num):
        if num[0] < num[-1]: return num[0]
        return self.min(num, 0, len(num)-1)
        
    def min(self, num, a, b):
        if a == b: return num[a]
        if a+1 == b: return min(num[a], num[b])
        mid = (a+b)/2
        return self.min(num, a, mid) if num[a] > num[mid] else self.min(num, mid, b)

def main():
    s = Solution()
    print s.findMin([6,7,8,9,10,1,2,3,4,5])

if __name__ == "__main__":
    main()