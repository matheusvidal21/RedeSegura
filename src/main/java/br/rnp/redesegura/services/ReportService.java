package br.rnp.redesegura.services;

import java.util.List;

public interface ReportService {

    List<ReportResponse> findAll();

    ReportResponse findById(Long id);

    ReportResponse create(ReportDto reportDto);

    ReportResponse update(Long id, ReportDto reportDto);

    void delete(Long id);

}
