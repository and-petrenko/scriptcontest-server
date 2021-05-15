package org.scriptcontest.server.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FunctionsTest {

  @Test
  public void testCheck_empty() {
    int[][] area = new int[10][10];
    assertFalse(Functions.checkArea(area));
  }

  @Test
  public void testCheck_right() {
    int[][] area = convertArea(new String[]{
        "0000000011",
        "0000000000",
        "1110000110",
        "0000110000",
        "1010000011",
        "0000100000",
        "0000000000",
        "0111110000",
        "0000000000",
        "0000000000"
    });

    assertFalse(Functions.checkArea(area));
  }

  private static int[][] convertArea(String[] area) {
    int[][] result = new int[10][10];
    for (int i = 0; i < area.length; i++) {
      for (int j = 0; j < area[i].length(); j++) {
        result[i][j] = Integer.parseInt(area[i].substring(j, j+1));
      }
    }
    return result;
  }

}
