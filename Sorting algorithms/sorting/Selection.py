'''
Selection sort
'''

def sortArray(arr):
    for i in range(0, len(arr)-1):
        min = i
        for j in range(i+1, len(arr)):
            if arr[j] < arr[min]:
                min = j
            pass
        arr[i], arr[min] = arr[min], arr[i]
    
    return arr

if __name__ == '__main__':
    a = sortArray([5,2,8,4,0,3,1])
    print a