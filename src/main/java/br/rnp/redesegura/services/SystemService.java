package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.SystemDto;
import br.rnp.redesegura.dto.response.SystemResponse;
import br.rnp.redesegura.models.System;

import java.util.List;

public interface SystemService {

    List<SystemResponse> findAll();

    SystemResponse findById(Long id);

    SystemResponse create(SystemDto systemDto);

    SystemResponse update(Long id, SystemDto systemDto);

    void delete(Long id);

}
