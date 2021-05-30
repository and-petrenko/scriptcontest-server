package org.scriptcontest.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayResult {

  private int winner;

  private String comment;

}
