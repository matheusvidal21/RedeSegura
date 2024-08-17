package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ReportDto;
import br.rnp.redesegura.dto.response.ReportResponse;
import br.rnp.redesegura.mapper.ReportMapper;
import br.rnp.redesegura.models.Report;
import br.rnp.redesegura.repositories.ReportRepository;
import br.rnp.redesegura.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<ReportResponse> findAll() {
        return reportRepository.findAll().stream()
                .map(ReportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponse findById(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        return ReportMapper.toResponse(report);
    }

    @Override
    public ReportResponse create(ReportDto reportDto) {
        Report report = ReportMapper.toEntity(reportDto);
        return ReportMapper.toResponse(reportRepository.save(report));
    }

    @Override
    public ReportResponse update(Long id, ReportDto reportDto) {
        Report existingReport = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        Report updatedReport = ReportMapper.toEntity(reportDto);
        updatedReport.setId(existingReport.getId());
        return ReportMapper.toResponse(reportRepository.save(updatedReport));
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }
}
