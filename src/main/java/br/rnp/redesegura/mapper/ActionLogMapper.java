package br.rnp.redesegura.mapper;


import br.rnp.redesegura.dto.ActionLogDto;
import br.rnp.redesegura.dto.response.ActionLogResponse;
import br.rnp.redesegura.models.ActionLog;
import br.rnp.redesegura.models.User;
import br.rnp.redesegura.models.Vulnerability;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionLogMapper {

    private ActionLogMapper() {
    }

    public static ActionLogResponse toResponse(ActionLog actionLog) {
        return ActionLogResponse.builder()
                .id(actionLog.getId())
                .vulnerabilityTitle(actionLog.getVulnerability().getTitle())
                .action(actionLog.getAction())
                .performedByName(actionLog.getPerformedBy().getName())
                .timestamp(actionLog.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    public static ActionLog toEntity(ActionLogDto actionLogDto, Vulnerability vulnerability, User performedBy) {
        return ActionLog.builder()
                .vulnerability(vulnerability)
                .action(actionLogDto.getAction())
                .performedBy(performedBy)
                .timestamp(LocalDateTime.now())
                .build();
    }

}