import random
def build(arr,l):
    a = random.randint(0,1)
    Testt = True
    test = True
    if a == 0:
        while Testt:
            test = True
            x = random.randint(1,11-l)
            y = random.randint(1,10)
            for i in range(l):
                if arr[x + i][y] != 0:
                    test = False
                    break
            if test == True:
                for i in range(3):
                    for j in range(l+2):
                        arr[x + j - 1][y + i - 1] = 2
                for i in range(l):
                    arr[x+i][y] = 1
                Testt = False
    else:
        while Testt:
            test = True
            x = random.randint(1,10)
            y = random.randint(1,11-l)
            for i in range(l):
                if arr[x][y + i] != 0:
                    test = False
                    break
            if test == True:
                for i in range(l+2):
                    for j in range(3):
                        arr[x + j - 1][y + i - 1] = 2
                for i in range(l):
                    arr[x][y+i] = 1
                Testt = False

def getShipsPosition():
    array = []
    array12 = []
    for i in range(12):
        array12.append([])
        for j in range(12):
            array12[i].append(0)
    for i in range(10):
        array.append([])
        for j in range(10):
            array[i].append(0)
    build(array12, 4)
    for i in range(2):
        build(array12, 3)
    for i in range(3):
        build(array12, 2)
    for i in range(4):
        build(array12, 1)
    for i in range(1,11):
        for j in range(1,11):
            if array12[i][j] == 1:
                array[i-1][j-1] = 1
    for n in array:
        print(n)
    return array

getShipsPosition()


