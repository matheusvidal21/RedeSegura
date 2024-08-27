package br.rnp.redesegura.dto.response;


import br.rnp.redesegura.models.ValidationError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {

    private int status;

    private HttpStatus httpStatus;

    private String message;

    private List<ValidationError> errors;

}