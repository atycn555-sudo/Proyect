package com.example.vet.service;

import com.example.vet.model.ClinicService;
import com.example.vet.repository.ClinicServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceService {

    @Autowired
    private ClinicServiceRepository clinicServiceRepository;


    public ClinicService saveClinicService(ClinicService clinicService) {
        return clinicServiceRepository.save(clinicService);
    }


    public List<ClinicService> findAllClinicServices() {
        return clinicServiceRepository.findAll();
    }


    public Optional<ClinicService> findClinicServiceById(Integer id) {
        return clinicServiceRepository.findById(id);
    }


    public List<ClinicService> findClinicServicesByName(String serviceName) {
        return clinicServiceRepository.findByServiceNameContainingIgnoreCase(serviceName);
    }


    public Optional<ClinicService> updateClinicService(Integer id, ClinicService serviceDetails) {
        return clinicServiceRepository.findById(id)
                .map(existingService -> {
                    existingService.setServiceName(serviceDetails.getServiceName());
                    existingService.setPrice(serviceDetails.getPrice());
                    return clinicServiceRepository.save(existingService);
                });
    }


    public boolean deleteClinicServiceById(Integer id) {
        if (clinicServiceRepository.existsById(id)) {
            clinicServiceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}