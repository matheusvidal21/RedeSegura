package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ActionLogDto;
import br.rnp.redesegura.dto.response.ActionLogResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ActionLogMapper;
import br.rnp.redesegura.models.ActionLog;
import br.rnp.redesegura.models.Vulnerability;
import br.rnp.redesegura.repositories.ActionLogRepository;
import br.rnp.redesegura.repositories.UserRepository;
import br.rnp.redesegura.repositories.VulnerabilityRepository;
import br.rnp.redesegura.services.ActionLogService;
import br.rnp.redesegura.services.VulnerabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionLogServiceImpl implements ActionLogService {

    @Autowired
    private ActionLogRepository actionLogRepository;

    @Autowired
    private VulnerabilityRepository vulnerabilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ActionLogResponse> findAll() {
        return actionLogRepository.findAll().stream()
                .map(ActionLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ActionLogResponse findById(Long id) {
        ActionLog actionLog = actionLogRepository.findById(id).orElseThrow(() -> new NotFoundException("ActionLog not found"));
        return ActionLogMapper.toResponse(actionLog);
    }

    @Override
    public ActionLogResponse create(ActionLogDto actionLogDto) {
        var vulnerability = vulnerabilityRepository.findById(actionLogDto.getVulnerabilityId()).orElseThrow(() -> new NotFoundException("Vulnerability not found"));
        var user = userRepository.findById(actionLogDto.getPerformedById()).orElseThrow(() -> new NotFoundException("User not found"));
        ActionLog actionLog = ActionLogMapper.toEntity(actionLogDto, vulnerability, user);
        return ActionLogMapper.toResponse(actionLogRepository.save(actionLog));
    }

    @Override
    public ActionLogResponse update(Long id, ActionLogDto actionLogDto) {
        var vulnerability = vulnerabilityRepository.findById(actionLogDto.getVulnerabilityId()).orElseThrow(() -> new NotFoundException("Vulnerability not found"));
        var user = userRepository.findById(actionLogDto.getPerformedById()).orElseThrow(() -> new NotFoundException("User not found"));
        ActionLog existingActionLog = actionLogRepository.findById(id).orElseThrow(() -> new NotFoundException("ActionLog not found"));
        ActionLog updatedActionLog = ActionLogMapper.toEntity(actionLogDto, vulnerability, user);
        updatedActionLog.setId(existingActionLog.getId());
        return ActionLogMapper.toResponse(actionLogRepository.save(updatedActionLog));
    }

    @Override
    public void delete(Long id) {
        var actionLog = actionLogRepository.findById(id).orElseThrow(() -> new NotFoundException("ActionLog not found"));
        actionLogRepository.deleteById(id);
    }
}
