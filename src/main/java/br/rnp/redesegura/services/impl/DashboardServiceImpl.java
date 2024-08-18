package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.repositories.VulnerabilityRepository;
import br.rnp.redesegura.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private VulnerabilityRepository vulnerabilityRepository;

}
