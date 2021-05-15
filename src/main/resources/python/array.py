def getShipsPosition():
    arr = []
    for i in range(10):
        arr.append([])
        for j in range(10):
            arr[i].append(0)
    arr[1][2] = 1
    arr[5][7] = 3
    arr[2][6] = 6
    arr[8][2] = 2
    return arr


