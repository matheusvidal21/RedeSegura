package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.SystemDto;
import br.rnp.redesegura.dto.response.SystemResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.SystemMapper;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.System;
import br.rnp.redesegura.models.User;
import br.rnp.redesegura.repositories.InstitutionRepository;
import br.rnp.redesegura.repositories.SystemRepository;
import br.rnp.redesegura.repositories.UserRepository;
import br.rnp.redesegura.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SystemResponse> findAll() {
        return systemRepository.findAll().stream()
                .map(SystemMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SystemResponse findById(Long id) {
        System systemEntity = systemRepository.findById(id).orElseThrow(() -> new RuntimeException("System not found"));
        return SystemMapper.toResponse(systemEntity);
    }

    @Override
    public SystemResponse create(SystemDto systemDto) {
        User user = userRepository.findById(systemDto.getResponsibleId()).orElseThrow(() -> new NotFoundException("User not found"));
        Institution institution = institutionRepository.findById(systemDto.getInstitutionId()).orElseThrow(() -> new NotFoundException("Institution not found"));
        System systemEntity = SystemMapper.toEntity(systemDto, institution, user);
        return SystemMapper.toResponse(systemRepository.save(systemEntity));
    }

    @Override
    public SystemResponse update(Long id, SystemDto systemDto) {
        System existingSystem = systemRepository.findById(id).orElseThrow(() -> new RuntimeException("System not found"));
        User user = userRepository.findById(systemDto.getResponsibleId()).orElseThrow(() -> new NotFoundException("User not found"));
        Institution institution = institutionRepository.findById(systemDto.getInstitutionId()).orElseThrow(() -> new NotFoundException("Institution not found"));
        System updatedSystem = SystemMapper.toEntity(systemDto, institution, user);
        updatedSystem.setId(existingSystem.getId());
        return SystemMapper.toResponse(systemRepository.save(updatedSystem));
    }

    @Override
    public void delete(Long id) {
        systemRepository.deleteById(id);
    }

}
