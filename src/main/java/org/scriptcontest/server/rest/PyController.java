package org.scriptcontest.server.rest;

import lombok.AllArgsConstructor;
import org.scriptcontest.server.python.PythonService;
import org.scriptcontest.server.service.SeaWarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PyController {

  private PythonService pythonService;

  private SeaWarService seaWarService;

  @GetMapping("/array")
  public int[][] getArray() {
    return pythonService.getShipsPosition("array.py");
  }

  @GetMapping("/battle")
  public int doBattle() {
    return seaWarService.doBattle();
  }

}
