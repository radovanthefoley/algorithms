'''
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
'''

class Solution:
    # @param triangle, a list of lists of integers
    # @return an integer
    def minimumTotal(self, triangle):
        tmp = triangle[len(triangle) - 1]
        for level in range(1, len(triangle))[::-1]:
            for i in range(0, len(triangle[level]) - 1):
                tmp [i] = triangle[level-1][i] + min(tmp[i], tmp[i+1])
        return tmp[0]
    
    def minimumTotalInPlace(self, triangle):
        for level in range(1, len(triangle))[::-1]:
            for i in range(0, len(triangle[level]) - 1):
                triangle[level-1][i] += min(triangle[level][i], triangle[level][i+1])
        
        return triangle[0][0]

if __name__ == '__main__':
    s = Solution()
    triangle = [\
           [2],\
          [3,4],\
         [6,5,7],\
        [4,1,8,3]\
        ]
    #triangle = [[46],[43,61],[10,-16,3],[-26,41,36,-72],[-28,-76,-22,26,51],[56,-53,38,67,86,-45],[58,53,47,-52,-54,-95,56],[-54,-93,58,68,26,-4,-45,86],[75,28,27,12,33,98,35,87,1],[-13,20,25,-98,-13,11,-44,-77,-59,-97],[-53,-14,83,80,31,89,38,-1,15,-88,53],[-22,86,-41,-94,-25,68,-96,87,55,-18,-49,-25],[-93,-48,39,17,8,61,57,-13,-92,-79,-29,87,51],[-63,3,-72,29,-9,57,-93,-46,-84,88,29,83,69,-7],[15,-49,43,90,-43,94,29,50,-21,-33,-16,43,-26,4,90]]
    print s.minimumTotal(triangle)