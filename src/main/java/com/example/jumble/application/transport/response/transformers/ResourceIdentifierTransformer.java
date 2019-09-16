package com.example.jumble.application.transport.response.transformers;

import com.example.jumble.application.transformer.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResourceIdentifierTransformer implements ResponseEntity {

  @Override
  public Map transform(Object entity) {

    Map<String, Object> mapping = new HashMap<>();

    mapping.put("id", entity);

    return mapping;

  }
}
