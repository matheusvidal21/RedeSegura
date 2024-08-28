package br.rnp.redesegura.services.impl;

import br.rnp.redesegura.dtos.AddressDto;
import br.rnp.redesegura.dtos.response.AddressResponse;
import br.rnp.redesegura.exceptions.NotFoundException;
import br.rnp.redesegura.mappers.AddressMapper;
import br.rnp.redesegura.models.Address;
import br.rnp.redesegura.repositories.AddressRepository;
import br.rnp.redesegura.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressResponse> findAll() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse findById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address not found"));
        return AddressMapper.toResponse(address);
    }

    @Override
    public AddressResponse create(AddressDto addressDto) {
        Address address = AddressMapper.toEntity(addressDto);
        return AddressMapper.toResponse(addressRepository.save(address));
    }

    @Override
    public AddressResponse update(Long id, AddressDto addressDto) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address not found"));
        Address updatedAddress = AddressMapper.toEntity(addressDto);
        updatedAddress.setId(existingAddress.getId());
        return AddressMapper.toResponse(addressRepository.save(updatedAddress));
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}
