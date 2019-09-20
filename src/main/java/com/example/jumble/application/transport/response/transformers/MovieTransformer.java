package com.example.jumble.application.transport.response.transformers;

import com.example.jumble.application.transformer.ResponseEntityInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class MovieTransformer implements ResponseEntityInterface {

  @Override
  public Map transform(Object entity) {

    ObjectMapper mapper = new ObjectMapper();
    Map entityMap = mapper.convertValue(entity, Map.class);

    Map<String, Object> mapping = new HashMap<>();

    mapping.put("transformed_id", entityMap.get("id"));
    mapping.put("transformed_title", entityMap.get("title"));

    return mapping;
  }
}
