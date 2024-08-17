package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.ToolDto;
import br.rnp.redesegura.dto.response.ToolResponse;
import br.rnp.redesegura.models.Tool;

public class ToolMapper {

    private ToolMapper() {
    }

    public static ToolResponse toResponse(Tool tool) {
        return ToolResponse.builder()
                .id(tool.getId())
                .name(tool.getName())
                .description(tool.getDescription())
                .integrationDetails(tool.getIntegrationDetails())
                .build();
    }

    public static Tool toEntity(ToolDto toolDto) {
        return Tool.builder()
                .name(toolDto.getName())
                .description(toolDto.getDescription())
                .integrationDetails(toolDto.getIntegrationDetails())
                .build();
    }
}
