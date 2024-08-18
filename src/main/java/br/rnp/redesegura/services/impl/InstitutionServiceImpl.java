package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.InstitutionDto;
import br.rnp.redesegura.dto.response.InstitutionResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.AddressMapper;
import br.rnp.redesegura.mapper.InstitutionMapper;
import br.rnp.redesegura.models.Address;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.repositories.AddressRepository;
import br.rnp.redesegura.repositories.InstitutionRepository;
import br.rnp.redesegura.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<InstitutionResponse> findAll() {
        return institutionRepository.findAll().stream()
                .map(InstitutionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstitutionResponse findById(Long id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution not found"));
        return InstitutionMapper.toResponse(institution);
    }

    @Override
    public InstitutionResponse create(InstitutionDto institutionDto) {
        Address address = AddressMapper.toEntity(institutionDto.getAddress());
        address = addressRepository.save(address);
        Institution institution = InstitutionMapper.toEntity(institutionDto, address);
        return InstitutionMapper.toResponse(institutionRepository.save(institution));
    }

    @Override
    public InstitutionResponse update(Long id, InstitutionDto institutionDto) {
        Institution existingInstitution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution not found"));

        Address address = AddressMapper.toEntity(institutionDto.getAddress());
        address = addressRepository.save(address);

        Institution updatedInstitution = InstitutionMapper.toEntity(institutionDto, address);
        updatedInstitution.setId(existingInstitution.getId());

        return InstitutionMapper.toResponse(institutionRepository.save(updatedInstitution));
    }

    @Override
    public void delete(Long id) {
        var institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution not found"));
        institutionRepository.deleteById(id);
    }

}
