package com.example.jumble.application.transport.response.transformers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppDetailTransformer {

  private String name;
  private String version;
  private String usage;
}
