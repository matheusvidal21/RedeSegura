package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.models.Protocol;
import br.rnp.redesegura.repositories.ProtocolRepository;
import br.rnp.redesegura.services.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtocolServiceImpl implements ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;


    @Override
    public List<Protocol> findAll() {
        return protocolRepository.findAll();
    }
}
