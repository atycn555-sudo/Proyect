package com.example.vet.controller;

import com.example.vet.dto.AddressRequestDTO;
import com.example.vet.dto.AddressResponseDTO;
import com.example.vet.model.Address;
import com.example.vet.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/addresses")
@Tag(name = "Addresses", description = "API para gestionar Direcciones")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressRequestDTO requestDTO) {
        Address address = modelMapper.map(requestDTO, Address.class);
        Address newAddress = addressService.saveAddress(address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAddress.getIdAddress()).toUri();
        return ResponseEntity.created(location).body(modelMapper.map(newAddress, AddressResponseDTO.class));
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {
        List<Address> addresses = addressService.findAllAddresses();
        List<AddressResponseDTO> dtos = addresses.stream().map(address -> modelMapper.map(address, AddressResponseDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Integer id) {
        return addressService.findAddressById(id)
                .map(address -> ResponseEntity.ok(modelMapper.map(address, AddressResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Integer id, @Valid @RequestBody AddressRequestDTO requestDTO) {
        Address addressDetails = modelMapper.map(requestDTO, Address.class);
        return addressService.updateAddress(id, addressDetails)
                .map(updatedAddress -> ResponseEntity.ok(modelMapper.map(updatedAddress, AddressResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        return addressService.deleteAddressById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}