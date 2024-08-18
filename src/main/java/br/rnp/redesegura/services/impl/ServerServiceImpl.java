package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.dto.response.ServerResponse;
import br.rnp.redesegura.exception.NotFoundException;
import br.rnp.redesegura.mapper.ServerMapper;
import br.rnp.redesegura.mapper.ServiceMapper;
import br.rnp.redesegura.models.Institution;
import br.rnp.redesegura.models.Server;
import br.rnp.redesegura.models.User;
import br.rnp.redesegura.repositories.InstitutionRepository;
import br.rnp.redesegura.repositories.ServerRepository;
import br.rnp.redesegura.repositories.ServiceRepository;
import br.rnp.redesegura.repositories.UserRepository;
import br.rnp.redesegura.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServerResponse> findAll() {
        return serverRepository.findAll().stream()
                .map(ServerMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public ServerResponse findById(Long id) {
        Server serverEntity = serverRepository.findById(id).orElseThrow(() -> new NotFoundException("Server not found"));
        return ServerMapper.toResponse(serverEntity);
    }

    @Override
    public ServerResponse create(ServerDto serverDto) {
        User user = userRepository.findById(serverDto.getResponsibleId()).orElseThrow(() -> new NotFoundException("User not found"));
        Institution institution = institutionRepository.findById(serverDto.getInstitutionId()).orElseThrow(() -> new NotFoundException("Institution not found"));
        Server serverEntity = ServerMapper.toEntity(serverDto, institution, user);
        return ServerMapper.toResponse(serverRepository.save(serverEntity));
    }

    @Override
    public ServerResponse update(Long id, ServerDto serverDto) {
        Server existingServer = serverRepository.findById(id).orElseThrow(() -> new NotFoundException("Server not found"));
        User user = userRepository.findById(serverDto.getResponsibleId()).orElseThrow(() -> new NotFoundException("User not found"));
        Institution institution = institutionRepository.findById(serverDto.getInstitutionId()).orElseThrow(() -> new NotFoundException("Institution not found"));
        Server updatedServer = ServerMapper.toEntity(serverDto, institution, user);
        updatedServer.setId(existingServer.getId());
        return ServerMapper.toResponse(serverRepository.save(updatedServer));
    }

    @Override
    public void delete(Long id) {
        var server = serverRepository.findById(id).orElseThrow(() -> new NotFoundException("Server not found"));
        serverRepository.deleteById(id);
    }

}
