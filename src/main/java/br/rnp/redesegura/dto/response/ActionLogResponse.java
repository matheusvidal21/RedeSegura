package br.rnp.redesegura.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLogResponse {

    private Long id;

    private String vulnerabilityTitle;

    private String action;

    private String performedByName;

    private String timestamp;

}