package org.scriptcontest.server.service;

import lombok.AllArgsConstructor;
import org.scriptcontest.server.dto.Coords;
import org.scriptcontest.server.dto.PlayResult;
import org.scriptcontest.server.python.PythonService;
import org.springframework.stereotype.Service;

/**
 * Состояние клеток:
 * 0 - пустая
 * 1 - кораблик (живой)
 * 2 - кораблик (потопленный)
 * 3 - выстрел мимо
 */
@Service
@AllArgsConstructor
public class SeaWarServiceImpl implements SeaWarService {

  public static final int CELL_EMPTY = 0;
  public static final int CELL_SHIP = 1;
  public static final int CELL_INJURED = 2;
  public static final int CELL_MISSED = 3;

  private PythonService pythonService;

  @Override
  public PlayResult doBattle() {
    Player p1 = new Player("array.py", pythonService);
    Player p2 = new Player("array2.py", pythonService);

    int[][] area1 = p1.getShipsPosition();
    printArea(area1);
    if (!checkArea(area1)) {
      return new PlayResult(2, "Player 1 filled ships in wrong way");
    }

    int[][] area2 = p2.getShipsPosition();
    if (!checkArea(area2)) {
      return new PlayResult(1, "Player 1 filled ships in wrong way");
    }

    Coords coords = p1.fire(area2);

    System.out.println("!!! fire=" + coords);

    int newState = executeFire(coords, area2);
    if (newState == CELL_INJURED) {
      // еще раз
    } else if (newState == CELL_MISSED) {
      // передаем управление
    }

    return new PlayResult(0, "We don't kow who won yet");
  }

  private int executeFire(Coords coords, int[][] area) {
    int currentState = area[coords.getX()][coords.getY()];
    if (currentState == CELL_SHIP) {
      area[coords.getX()][coords.getY()] = CELL_INJURED;
      return CELL_INJURED;
    } else if (currentState == CELL_EMPTY) {
      area[coords.getX()][coords.getY()] = CELL_MISSED;
      return CELL_MISSED;
    } else {
      return area[coords.getX()][coords.getY()];
    }
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
