package br.rnp.redesegura.services;

import br.rnp.redesegura.dtos.InstitutionDto;
import br.rnp.redesegura.dtos.response.InstitutionResponse;

import java.util.List;

public interface InstitutionService {

    List<InstitutionResponse> findAll();

    InstitutionResponse findById(Long id);

    InstitutionResponse findByName(String name);

    InstitutionResponse create(InstitutionDto institutionDto);

    InstitutionResponse update(Long id, InstitutionDto institutionDto);

    void delete(Long id);

}
