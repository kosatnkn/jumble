package com.example.jumble.application.transport.request.entities;

import com.example.jumble.application.validator.RequestEntity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestEntity implements RequestEntity {

    @NotEmpty
    @Size(min = 4, max = 7)
    private String title;
}
