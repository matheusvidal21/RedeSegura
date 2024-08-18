package br.rnp.redesegura.services;

import java.util.List;

public interface ActionLogService {

    List<ActionLogResponse> findAll();

    ActionLogResponse findById(Long id);

    ActionLogResponse create(ActionLogDto actionLogDto);

    ActionLogResponse update(Long id, ActionLogDto actionLogDto);

    void delete(Long id);

}
