package org.scriptcontest.server.service;

public class Shoter {
  public static int Checher(int[][] area,int x,int y){
    if (area[x][y] == 0){
      area[x][y] = 2;
      return 2;
    }
    else if(area[x][y] == 1){
      area[x][y] = 3;
      return 3;
    }
    else{
      return -1;
    }
  }
}
