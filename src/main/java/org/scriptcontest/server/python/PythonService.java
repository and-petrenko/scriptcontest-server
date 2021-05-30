package org.scriptcontest.server.python;

import org.python.core.PyInteger;
import org.python.core.PyList;
import org.python.core.PyTuple;
import org.python.util.PythonInterpreter;
import org.scriptcontest.server.dto.Coords;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@Service
public class PythonService {

  public int[][] getShipsPosition(String file){
    try (PythonInterpreter pi = new PythonInterpreter()) {
      StringWriter output = new StringWriter();
      pi.setOut(output);
      try (InputStream inputStream = PythonService.class.getClassLoader().getResourceAsStream("./python/" + file)) {
        pi.execfile(inputStream);
        pi.exec("result = getShipsPosition()");
        PyList result = (PyList) pi.get("result");
        return convertToArray(result);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public Coords fire(String file, int[][] area){
    try (PythonInterpreter pi = new PythonInterpreter()) {
      StringWriter output = new StringWriter();
      pi.setOut(output);
      try (InputStream inputStream = PythonService.class.getClassLoader().getResourceAsStream("./python/" + file)) {
        pi.execfile(inputStream);
        pi.set("area", convertToPyList(area));
        pi.exec("result = fire(area)");
        PyTuple result = (PyTuple) pi.get("result");
        return new Coords(((Integer)result.get(0)).intValue(), ((Integer)result.get(1)).intValue());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private int[][] convertToArray(PyList pyList) {
    int[][] result = new int[10][10];

    for (int i = 0; i < pyList.size(); i++) {
      PyList list = (PyList)pyList.get(i);
      for (int j = 0; j < list.size(); j++) {
        result[i][j] = ((Integer)list.get(j)).intValue();
      }
    }

    return result;
  }

  private PyList convertToPyList(int[][] area) {
    PyList result = new PyList();

    for (int i = 0; i < area.length; i++) {
      PyList pyRow = new PyList();
      for (int j = 0; j < area[i].length; j++) {
        pyRow.add(area[i][j]);
      }
      result.add(pyRow);
    }
    return result;
  }

//  public static void main(String[] args) throws Exception {
//    int[][] array = getArray();
//
//    for (int i = 0; i < array.length; i++) {
//      for (int j = 0; j < array[i].length; j++) {
//        System.out.print(array[i][j]);
//        System.out.print(" ");
//      }
//      System.out.println();
//    }
//  }

}
