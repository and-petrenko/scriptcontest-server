package org.scriptcontest.server.service;

public class Functions {

  public static boolean checkArea(int[][] area) {
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
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
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
            if (area2[i][j + 1] == 1) {
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
  }
  else {
    return false;
  }
  }
}
