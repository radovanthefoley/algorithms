'''
Bubble sort
'''

def sortArray(arr):
    for i in range(0, len(arr)):
        noSwaps = True
        for j in range(i+1, len(arr))[::-1]:
            if arr[j] < arr[j-1]:
                arr[j], arr[j-1] = arr[j-1], arr[j]
                noSwaps = False
        if noSwaps:
            break
    
    return arr

if __name__ == '__main__':
    a = sortArray([5,2,8,4,0,3,1])
    print a