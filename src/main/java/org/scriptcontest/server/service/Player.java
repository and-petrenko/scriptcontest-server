package org.scriptcontest.server.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.scriptcontest.server.dto.Coords;
import org.scriptcontest.server.python.PythonService;

@AllArgsConstructor
public class Player {

  @Getter
  private String name;

  private String script;

  private PythonService pythonService;

  public int[][] getShipsPosition() {
    return pythonService.getShipsPosition(script);
  }

  public Coords fire(int[][] area) {
    return pythonService.fire(script, area);
  }

}
