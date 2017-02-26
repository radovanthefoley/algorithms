#Follow up for "Remove Duplicates":
#What if duplicates are allowed at most twice?
#
#For example,
#Given sorted array A = [1,1,1,2,2,3],
#
#Your function should return length = 5, and A is now [1,1,2,2,3].

class Solution:
    # @param A a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) < 3:
            return len(A)
        
        for i in range(2,len(A))[::-1]:
            if A[i-2] == A[i]:
                del(A[i])
            
        return len(A)
            

def main():
    s = Solution()
    A = [1,1]
    print s.removeDuplicates(A)
    print A

if __name__ == "__main__":
    main()