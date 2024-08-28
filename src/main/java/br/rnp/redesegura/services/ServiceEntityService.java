package br.rnp.redesegura.services;

import br.rnp.redesegura.dtos.ServiceDto;
import br.rnp.redesegura.dtos.response.ServiceResponse;
import br.rnp.redesegura.models.enums.ServiceStatus;

import java.util.List;

public interface ServiceEntityService {

    List<ServiceResponse> findAll();

    ServiceResponse findById(Long id);

    ServiceResponse create(ServiceDto serviceDto);

    void delete(Long id);

    ServiceResponse update(Long id, ServiceDto serviceDto);

    void serviceStatusUpdate(Long id, ServiceStatus status);

}
