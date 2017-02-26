class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        for i in range(1,len(A)):
            A[0] ^= A[i]
        return A[0]

if __name__ == '__main__':
    A = [50,51,52,53,52,49,53,50,51]
    s = Solution()
    print s.singleNumber(A)