package com.example.jumble.application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppDetail {

  private String name;
  private String version;
  private String usage;
}
