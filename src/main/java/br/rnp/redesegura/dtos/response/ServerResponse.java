package br.rnp.redesegura.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse {

    private Long id;

    private String name;

    private String institutionName;

    private Long institutionId;

    private String health;

    private String createdAt;

    private String updatedAt;

}