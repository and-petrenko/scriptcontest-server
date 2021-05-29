package org.scriptcontest.server.service;

import lombok.AllArgsConstructor;
import org.scriptcontest.server.python.PythonService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeaWarServiceImpl implements SeaWarService {

  private PythonService pythonService;

  @Override
  public int doBattle() {
    int[][] area1 = pythonService.getShipsPosition("array.py");
    printArea(area1);
    if (!checkArea(area1)) {
      return 2;
    }

    int[][] area2 = pythonService.getShipsPosition("array2.py");
    if (!checkArea(area2)) {
      return 1;
    }


    return 3;
  }

  private void printArea(int[][] area1) {
    for (int[] row : area1) {
      for (int item : row) {
        System.out.print(item);
      }
      System.out.println();
    }
  }

  /**
   * функция возвращает true, если расстановка правильная
   * и false, если нет
   */
  protected boolean checkArea(int[][] area) {
    boolean test = true;
    int s = 0;
    //считаем кол-во занятых клеток
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (area[i][j] == 1) {
          s++;
        }
      }
    }
    if (s != 20) {
      //если клеток ного, или не хватает, то очевидно, расстановка неправильная
      return false;
    }
    else {
      //i - это столбцы, а j - строки
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 10; j++) {
          if (area[i][j] == 1) {
            if ((j != 0) & (j != 9)) {
              if ((area[i + 1][j + 1] == 1) | (area[i + 1][j + 1] == 1)){
                test = false;
              }
            }
            else if (j == 0) {
              if (area[i + 1][j + 1] == 1) {
                test = false;
              }
            }
            else {
              if (area[i + 1][j - 1] == 1) {
                test = false;
              }
            }
          }
        }
      }
    }
    if (test) {
      int[][] area2 = new int[12][12];
      int[] ships = new int[4];
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          area2[i + 1][j + 1] = area[i][j];
        }
      }
      for (int i = 1; i < 11; i++) {
        for (int j = 1; j < 11; j++) {
          if (area2[i][j] == 1) {
            if ((area2[i - 1][j] != 1) & (area2[i][j - 1] != 1)) {
              int l = 0;
              if (area2[i + 1][j] == 1) {
                int c = 0;
                while (area2[i + c][j] == 1){
                  l++;
                  c++;
                }
                if (l > 4){
                  test = false;
                }
                else{
                  ships[l-1]++;
                }
              }
              else if (area2[i][j + 1] == 1) {
                int c = 0;
                while (area2[i][j + c] == 1){
                  l++;
                  c++;
                }
                if (l > 4){
                  test = false;
                }
                else{
                  ships[l-1]++;
                }
              }
              else {
                ships[0]++;
              }
            }
          }
        }
      }
      for (int i = 0; i < 4; i++){
        if (4 - i != ships[i]){
          test = false;
        }
      }
      return test;
    } else {
      return false;
    }
  }

}
