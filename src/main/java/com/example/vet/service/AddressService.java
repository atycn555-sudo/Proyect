package com.example.vet.service;

import com.example.vet.model.Address;
import com.example.vet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> findAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> updateAddress(Integer id, Address addressDetails) {
        return addressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setStreet(addressDetails.getStreet());
                    existingAddress.setExternalNumber(addressDetails.getExternalNumber());
                    existingAddress.setNeighborhood(addressDetails.getNeighborhood());
                    existingAddress.setCity(addressDetails.getCity());
                    existingAddress.setZipCode(addressDetails.getZipCode());
                    return addressRepository.save(existingAddress);
                });
    }

    public boolean deleteAddressById(Integer id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}