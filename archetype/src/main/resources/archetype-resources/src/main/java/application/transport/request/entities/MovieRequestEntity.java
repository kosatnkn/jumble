#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.transport.request.entities;

import ${package}.application.validator.RequestEntityInterface;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestEntity implements RequestEntityInterface {

    @NotEmpty
    @Size(min = 4, max = 7)
    private String title;
}
