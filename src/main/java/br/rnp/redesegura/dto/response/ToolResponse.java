package br.rnp.redesegura.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolResponse {

    private Long id;

    private String name;

    private String description;

    private String integrationDetails;

}