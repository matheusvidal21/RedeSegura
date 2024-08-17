package br.rnp.redesegura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLogDto {

    private Long vulnerabilityId;

    private String action;

    private Long performedById;

}
