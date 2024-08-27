package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ServiceMapper;
import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.Server;
import br.rnp.redesegura.models.enums.ServiceStatus;
import br.rnp.redesegura.repositories.ProtocolRepository;
import br.rnp.redesegura.repositories.ServiceRepository;
import br.rnp.redesegura.repositories.ServerRepository;
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
    private ServerRepository serverRepository;

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
        Server server = serverRepository.findById(serviceDto.getServerId())
                .orElseThrow(() -> new NotFoundException("Server not found"));

        List<Protocol> protocols = serviceDto.getProtocols().stream().map(protocol -> protocolRepository.findByName(protocol).
                orElseThrow(() -> new NotFoundException("Protocol not found"))).collect(Collectors.toList());

        Service service = ServiceMapper.toEntity(serviceDto, server, new HashSet<>(protocols));
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

        Server server = serverRepository.findById(serviceDto.getServerId())
                .orElseThrow(() -> new NotFoundException("Server not found"));

        List<Protocol> protocols = serviceDto.getProtocols().stream().map(protocol -> protocolRepository.findByName(protocol).
                orElseThrow(() -> new NotFoundException("Protocol not found"))).collect(Collectors.toList());

        service.setName(serviceDto.getName());
        service.setProtocols(new HashSet<>(protocols));
        service.setIp(serviceDto.getIp());
        service.setPort(serviceDto.getPort());
        service.setStatus(ServiceStatus.fromString(serviceDto.getStatus()));
        service.setServer(server);

        return ServiceMapper.toResponse(serviceRepository.save(service));
    }

    public void serviceStatusUpdate(Long id, ServiceStatus status) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found"));
        service.setStatus(status);
        serviceRepository.save(service);
    }

}
