package br.rnp.redesegura.services;

import br.rnp.redesegura.dto.AddressDto;
import br.rnp.redesegura.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAll();

    AddressResponse findById(Long id);

    AddressResponse create(AddressDto addressDto);

    AddressResponse update(Long id, AddressDto addressDto);

    void delete(Long id);

}
