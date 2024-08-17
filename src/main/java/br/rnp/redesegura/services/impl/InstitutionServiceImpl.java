package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.repositories.InstitutionRepository;
import br.rnp.redesegura.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }
}
