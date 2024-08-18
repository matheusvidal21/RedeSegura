package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ServiceMapper;
import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.System;
import br.rnp.redesegura.repositories.ProtocolRepository;
import br.rnp.redesegura.repositories.ServiceRepository;
import br.rnp.redesegura.repositories.SystemRepository;
import br.rnp.redesegura.services.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceEntityServiceImpl implements ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private ProtocolRepository protocolRepository;

    @Override
    public List<ServiceResponse> findAll() {
        return serviceRepository.findAll().stream()
                .map(ServiceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse findById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found"));
        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponse create(ServiceDto serviceDto) {
        System system = systemRepository.findById(serviceDto.getSystemId())
                .orElseThrow(() -> new NotFoundException("System not found"));

        List<Protocol> protocols = serviceDto.getProtocols().stream().map(protocol -> protocolRepository.findByName(protocol).
                orElseThrow(() -> new NotFoundException("Protocol not found"))).collect(Collectors.toList());

        Service service = ServiceMapper.toEntity(serviceDto, system, new HashSet<>(protocols));
        return ServiceMapper.toResponse(serviceRepository.save(service));
    }

    @Override
    public void delete(Long id) {
        var service = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found"));
        serviceRepository.deleteById(id);
    }

    @Override
    public ServiceResponse update(Long id, ServiceDto serviceDto) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found"));

        System system = systemRepository.findById(serviceDto.getSystemId())
                .orElseThrow(() -> new NotFoundException("System not found"));

        List<Protocol> protocols = serviceDto.getProtocols().stream().map(protocol -> protocolRepository.findByName(protocol).
                orElseThrow(() -> new NotFoundException("Protocol not found"))).collect(Collectors.toList());

        service.setName(serviceDto.getName());
        service.setProtocols(new HashSet<>(protocols));
        service.setIp(serviceDto.getIp());
        service.setPort(serviceDto.getPort());
        service.setStatus(service.getStatus());
        service.setSystem(system);

        return ServiceMapper.toResponse(serviceRepository.save(service));
    }

}
