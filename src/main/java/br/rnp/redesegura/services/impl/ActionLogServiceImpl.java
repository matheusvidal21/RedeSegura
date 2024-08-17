package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ActionLogDto;
import br.rnp.redesegura.dto.response.ActionLogResponse;
import br.rnp.redesegura.services.ActionLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionLogServiceImpl implements ActionLogService {
    @Override
    public List<ActionLogResponse> findAll() {
        return null;
    }

    @Override
    public ActionLogResponse findById(Long id) {
        return null;
    }

    @Override
    public ActionLogResponse create(ActionLogDto actionLogDto) {
        return null;
    }

    @Override
    public ActionLogResponse update(Long id, ActionLogDto actionLogDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
