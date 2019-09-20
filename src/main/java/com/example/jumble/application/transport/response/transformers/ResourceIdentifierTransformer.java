package com.example.jumble.application.transport.response.transformers;

import com.example.jumble.application.transformer.ResponseEntityInterface;
import java.util.HashMap;
import java.util.Map;

public class ResourceIdentifierTransformer implements ResponseEntityInterface {

  @Override
  public Map transform(Object entity) {

    Map<String, Object> mapping = new HashMap<>();

    mapping.put("id", entity);

    return mapping;

  }
}
