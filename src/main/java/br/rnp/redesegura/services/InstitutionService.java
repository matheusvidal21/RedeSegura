package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.models.Institution;

import java.util.List;

public interface InstitutionService {

    List<InstitutionResponse> findAll();

    InstitutionResponse findById(Long id);

    InstitutionResponse create(InstitutionDto institutionDto);

    InstitutionResponse update(Long id, InstitutionDto institutionDto);

    void delete(Long id);

}
