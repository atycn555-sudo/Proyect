package com.example.vet.dto;

import java.time.LocalTime;

public class ShiftResponseDTO {
    private Integer idShift;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private VeterinarianSimpleResponseDTO veterinarian;

    public Integer getIdShift() {
        return idShift;
    }

    public void setIdShift(Integer idShift) {
        this.idShift = idShift;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public VeterinarianSimpleResponseDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(VeterinarianSimpleResponseDTO veterinarian) {
        this.veterinarian = veterinarian;
    }
}
