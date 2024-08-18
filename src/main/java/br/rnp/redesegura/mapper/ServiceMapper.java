package br.rnp.redesegura.mapper;

import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.models.Service;
import br.rnp.redesegura.models.System;
import br.rnp.redesegura.models.enums.ServiceStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class ServiceMapper {

    private ServiceMapper() {
    }

    public static ServiceResponse toResponse(Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .ip(service.getIp())
                .port(service.getPort())
                .status(service.getStatus().getValue())
                .protocol(service.getProtocols().stream().map(Protocol::getName).collect(Collectors.toSet()))
                .systemName(service.getSystem().getName())
                .build();
    }

    public static Service toEntity(ServiceDto serviceDto, System system, Set<Protocol> protocols) {
        return Service.builder()
                .name(serviceDto.getName())
                .ip(serviceDto.getIp())
                .port(serviceDto.getPort())
                .status(ServiceStatus.UNKNOWN)
                .protocols(protocols)
                .system(system)
                .build();
    }

}
