'''
Insertion sort
'''

def sortArray(arr):
    for i,j in range(1, len(arr)):
        j = i
        while(j > 0 and arr[j] < arr[j-1]):
            arr[j], arr[j-1] = arr[j-1], arr[j]
            j-=1
    
    return arr

if __name__ == '__main__':
    a = sortArray([5,2,8,4,0,3,1])
    print a