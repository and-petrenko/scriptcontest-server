# coding=UTF-8
# импорт библиотек

import random


# функция генерации для кораблей длиной 2-4 клетки
# генераци проходит на крайних клетках
# на вход получает массив 12x12
# обозначения: 0 - пустая клетка; 1 - корабль; 2 - клетка возле корабля

def BuildLongShips(arr, cells):
    # определяет поворот корабля

    a = random.randint(0, 1)
    centralTest = True
    if a == 0:

        # генерация вертикального корабля

        while centralTest:
            test = True

            # получение левой нижней клетки корабля

            x = random.randint(1, 11 - cells)
            y = random.randint(0, 1)
            y = y * 9 + 1

            # проверка на отсутствие пересечеий с другими кораблями

            for i in range(cells):
                if arr[x + i][y] != 0:
                    test = False
                    break

            # если тест правильный - ставим корабль

            if test:

                # заполнение клеток возле корабля

                for i in range(cells + 2):
                    for j in range(3):
                        arr[x + i - 1][y + j - 1] = 2

                # заполнение клеток корабля

                for i in range(cells):
                    arr[x + i][y] = 1
                centralTest = False

    else:

        # генерация горизонтального корабля

        while centralTest:
            test = True

            # получение левой нижней клетки корабля

            x = random.randint(0, 1)
            y = random.randint(1, 11 - cells)
            x = x * 9 + 1

            # проверка на отсутствие пересечеий с другими кораблями

            for i in range(cells):
                if arr[x][y + i] != 0:
                    test = False
                    break

            # если тест правильный - ставим корабль

            if test:

                # заполнение клеток возле корабля

                for i in range(3):
                    for j in range(cells + 2):
                        arr[x + i - 1][y + j - 1] = 2

                # заполнение клеток корабля

                for i in range(cells):
                    arr[x][y + i] = 1
                centralTest = False


def build1x1Ships(arr):
    centralTest = True

    # генерация вертикального корабля

    while centralTest:
        test = True
        x = random.randint(3, 8)
        y = random.randint(3, 8)

        if arr[x][y] != 0:
            test = False

        if test:

            # заполнение клеток возле корабля

            for i in range(3):
                for j in range(3):
                    arr[x + i - 1][y + j - 1] = 2

            # заполнение клеток корабля
            arr[x][y] = 1

            centralTest = False


# функция создает два массива 10x10 и 12x12
# затем заполняет больший кораблями и переносит в правильном формате в меньший
# на выход получаем массив с умной расстановкой кораблей

def getShipsPosition():
    # создание массивов

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

    # вызов генерации кораблей

    BuildLongShips(array12, 4)
    for i in range(2):
        BuildLongShips(array12, 3)
    for i in range(3):
        BuildLongShips(array12, 2)
    for i in range(4):
        build1x1Ships(array12)

    # перенос из большего массива в меньший

    for i in range(1, 11):
        for j in range(1, 11):
            if array12[i][j] == 1:
                array[i - 1][j - 1] = 1

    # печать конечного массива

    for n in array:
        print(n)

    return array


def fire(area):
    return random.randint(0,9), random.randint(0,9)


getShipsPosition()
