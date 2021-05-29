package org.scriptcontest.server.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeaWarServiceTest {

  SeaWarServiceImpl seaWarService = new SeaWarServiceImpl(null);

  @Test
  public void testCheck_empty() {
    int[][] area = new int[10][10];
    assertFalse(seaWarService.checkArea(area));
  }

  @Test
  public void testCheck_tooLong() {
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


    assertFalse(seaWarService.checkArea(area));
  }
  @Test
  public void testCheck_peresechenie() {
    int[][] area = convertArea(new String[]{
        "0000000011",
        "0000000000",
        "1110000110",
        "0001110000",
        "1000000011",
        "0000100000",
        "0000000000",
        "0011110000",
        "0000000000",
        "0100010000"
    });


    assertFalse(seaWarService.checkArea(area));
  }

  @Test
  public void testCheck_right() {
    int[][] area = convertArea(new String[]{
        "0000000011",
        "0000000000",
        "1110000110",
        "0000110000",
        "1000000111",
        "0000100000",
        "0000000100",
        "0111100000",
        "0000000000",
        "0000010000"
    });

    assertTrue(seaWarService.checkArea(area));
  }

  @Test
  public void testCheck_right2() {
    int[][] area = convertArea(new String[]{
        "0001000101",
        "0101000000",
        "0100000000",
        "0001000100",
        "0001000100",
        "1001000100",
        "1000000000",
        "0010111100",
        "0000000000",
        "0001000000"
    });
    assertTrue(seaWarService.checkArea(area));
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
