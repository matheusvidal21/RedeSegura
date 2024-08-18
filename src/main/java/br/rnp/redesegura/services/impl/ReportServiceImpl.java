package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ReportDto;
import br.rnp.redesegura.dto.response.ReportResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ReportMapper;
import br.rnp.redesegura.models.Report;
import br.rnp.redesegura.repositories.ReportRepository;
import br.rnp.redesegura.repositories.UserRepository;
import br.rnp.redesegura.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReportResponse> findAll() {
        return reportRepository.findAll().stream()
                .map(ReportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponse findById(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new NotFoundException("Report not found"));
        return ReportMapper.toResponse(report);
    }

    @Override
    public ReportResponse create(ReportDto reportDto) {
        var user = userRepository.findById(reportDto.getGeneratedById()).orElseThrow(() -> new NotFoundException("User not found"));
        Report report = ReportMapper.toEntity(reportDto, user);
        return ReportMapper.toResponse(reportRepository.save(report));
    }

    @Override
    public ReportResponse update(Long id, ReportDto reportDto) {
        var user = userRepository.findById(reportDto.getGeneratedById()).orElseThrow(() -> new NotFoundException("User not found"));
        Report existingReport = reportRepository.findById(id).orElseThrow(() -> new NotFoundException("Report not found"));
        Report updatedReport = ReportMapper.toEntity(reportDto, user);
        updatedReport.setId(existingReport.getId());
        return ReportMapper.toResponse(reportRepository.save(updatedReport));
    }

    @Override
    public void delete(Long id) {
        var report = reportRepository.findById(id).orElseThrow(() -> new NotFoundException("Report not found"));
        reportRepository.deleteById(id);
    }
}
