package org.scriptcontest.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Координаты точки от 0 до 9
 */
@Getter
@ToString
@AllArgsConstructor
public class Coords {

  private final int x;

  private final int y;

}
