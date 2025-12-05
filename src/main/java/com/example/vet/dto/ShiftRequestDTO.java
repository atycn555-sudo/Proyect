package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class ShiftRequestDTO {

    @NotBlank(message = "El día de la semana no puede estar vacío")
    private String dayOfWeek;

    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalTime startTime;

    @NotNull(message = "La hora de fin no puede ser nula")
    private LocalTime endTime;

    @NotNull(message = "El ID del veterinario no puede ser nulo")
    private Integer idVeterinarian;

    // ... tus getters y setters manuales ...
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public Integer getIdVeterinarian() { return idVeterinarian; }
    public void setIdVeterinarian(Integer idVeterinarian) { this.idVeterinarian = idVeterinarian; }
}