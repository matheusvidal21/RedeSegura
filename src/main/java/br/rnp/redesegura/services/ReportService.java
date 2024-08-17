package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.ReportDto;
import br.rnp.redesegura.dto.response.ReportResponse;

import java.util.List;

public interface ReportService {

    List<ReportResponse> findAll();

    ReportResponse findById(Long id);

    ReportResponse create(ReportDto reportDto);

    ReportResponse update(Long id, ReportDto reportDto);

    void delete(Long id);

}
