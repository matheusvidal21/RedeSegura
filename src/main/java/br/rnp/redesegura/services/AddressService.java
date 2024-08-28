package br.rnp.redesegura.services;

import br.rnp.redesegura.dtos.AddressDto;
import br.rnp.redesegura.dtos.response.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAll();

    AddressResponse findById(Long id);

    AddressResponse create(AddressDto addressDto);

    AddressResponse update(Long id, AddressDto addressDto);

    void delete(Long id);

}
