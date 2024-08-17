package br.rnp.redesegura.dto.response;


import br.rnp.redesegura.models.ValidationError;
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
public class ValidationErrorResponse {

    private int status;

    private HttpStatus httpStatus;

    private String message;

    private List<ValidationError> errors;

}