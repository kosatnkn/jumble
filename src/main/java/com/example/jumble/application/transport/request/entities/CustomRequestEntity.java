package com.example.jumble.application.transport.request.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomRequestEntity implements RequestEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 4, max = 7)
    private String code;
}
