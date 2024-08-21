package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.ServiceDto;
import br.rnp.redesegura.dto.response.ServiceResponse;
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
