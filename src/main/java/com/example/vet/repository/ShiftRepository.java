package com.example.vet.repository;

import com.example.vet.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    List<Shift> findByVeterinarianIdVeterinarian(Integer veterinarianId);

    List<Shift> findByDayOfWeekIgnoreCase(String dayOfWeek);

}