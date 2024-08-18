package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.ServerDto;
import br.rnp.redesegura.dto.response.ServerResponse;

import java.util.List;

public interface ServerService {

    List<ServerResponse> findAll();

    ServerResponse findById(Long id);

    ServerResponse create(ServerDto serverDto);

    ServerResponse update(Long id, ServerDto serverDto);

    void delete(Long id);

}
