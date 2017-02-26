#Given an array of integers, every element appears three times except for one. Find that single one.
#
#Note:
#Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

class Solution:
    # @param A, a list of integer
    # @return an integer   
    def singleNumber(self, A):
        ones, twos = 0, 0;
        for i in range(0,len(A)):
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
    
        return ones;


def main():
    s = Solution()
    A = [1,1,1,3,3,2,3]
    print s.singleNumber(A)

if __name__ == "__main__":
    main()