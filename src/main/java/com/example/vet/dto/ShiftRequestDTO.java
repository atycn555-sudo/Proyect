package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class ShiftRequestDTO {

    @NotBlank(message = "The day of the week cannot be empty")
    private String dayOfWeek;

    @NotNull(message = "The start time cannot be zero.")
    private LocalTime startTime;

    @NotNull(message = "The end time cannot be zero.")
    private LocalTime endTime;

    @NotNull(message = "The veterinarian's ID cannot be null")
    private Integer idVeterinarian;

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public Integer getIdVeterinarian() { return idVeterinarian; }
    public void setIdVeterinarian(Integer idVeterinarian) { this.idVeterinarian = idVeterinarian; }
}
