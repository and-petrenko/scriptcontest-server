package org.scriptcontest.server.rest;

import org.scriptcontest.server.python.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PyController {

  @Autowired
  private PythonService pythonService;

  @GetMapping("/array")
  public int[][] getArray() {
    return pythonService.getArray();
  }

}
