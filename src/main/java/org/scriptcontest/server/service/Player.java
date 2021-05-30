package org.scriptcontest.server.service;

import lombok.AllArgsConstructor;
import org.scriptcontest.server.dto.Coords;
import org.scriptcontest.server.python.PythonService;

@AllArgsConstructor
public class Player {

  private String script;

  private PythonService pythonService;

  public int[][] getShipsPosition() {
    return pythonService.getShipsPosition(script);
  }


  public Coords fire(int[][] area) {
//    return new Coords(new Random().nextInt(10), new Random().nextInt(10));
    return pythonService.fire(script, area);
  }

}
