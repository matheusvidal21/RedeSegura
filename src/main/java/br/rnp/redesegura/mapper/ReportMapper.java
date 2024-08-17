package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.ReportDto;
import br.rnp.redesegura.dto.response.ReportResponse;
import br.rnp.redesegura.models.Report;
import br.rnp.redesegura.models.User;

import java.time.format.DateTimeFormatter;

public class ReportMapper {


    private ReportMapper() {
    }

    public static ReportResponse toResponse(Report report) {
        return ReportResponse.builder()
                .id(report.getId())
                .generatedBy(report.getGeneratedBy().getName())
                .content(report.getContent())
                .createdAt(report.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    public static Report toEntity(ReportDto reportDto, User generatedBy) {
        return Report.builder()
                .generatedBy(generatedBy)
                .content(reportDto.getContent())
                .build();
    }

    public static Report toEntity(ReportDto reportDto) {
        return Report.builder()
                .content(reportDto.getContent())
                .build();
    }

}
