package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ToolDto;
import br.rnp.redesegura.dto.response.ToolResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ToolMapper;
import br.rnp.redesegura.models.Tool;
import br.rnp.redesegura.repositories.ToolRepository;
import br.rnp.redesegura.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public List<ToolResponse> findAll() {
        return toolRepository.findAll().stream()
                .map(ToolMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ToolResponse findById(Long id) {
        Tool tool = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found"));
        return ToolMapper.toResponse(tool);
    }

    @Override
    public ToolResponse create(ToolDto toolDto) {
        Tool tool = ToolMapper.toEntity(toolDto);
        return ToolMapper.toResponse(toolRepository.save(tool));
    }

    @Override
    public ToolResponse update(Long id, ToolDto toolDto) {
        Tool existingTool = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found"));
        Tool updatedTool = ToolMapper.toEntity(toolDto);
        updatedTool.setId(existingTool.getId());
        return ToolMapper.toResponse(toolRepository.save(updatedTool));
    }

    @Override
    public void delete(Long id) {
        var tool = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found"));
        toolRepository.deleteById(id);
    }
}
