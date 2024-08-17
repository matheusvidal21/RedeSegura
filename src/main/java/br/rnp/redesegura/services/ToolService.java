package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.ToolDto;
import br.rnp.redesegura.dto.response.ToolResponse;
import br.rnp.redesegura.models.Tool;

import java.util.List;

public interface ToolService {

    List<ToolResponse> findAll();

    ToolResponse findById(Long id);

    ToolResponse create(ToolDto toolDto);

    ToolResponse update(Long id, ToolDto toolDto);

    void delete(Long id);

}
