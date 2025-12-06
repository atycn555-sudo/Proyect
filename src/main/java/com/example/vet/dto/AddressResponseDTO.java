package com.example.vet.dto;

public class AddressResponseDTO {
    private Integer idAddress;
    private String street;
    private String externalNumber;
    private String neighborhood;
    private String city;
    private String zipCode;

    public Integer getIdAddress() { return idAddress; }
    public void setIdAddress(Integer idAddress) { this.idAddress = idAddress; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getExternalNumber() { return externalNumber; }
    public void setExternalNumber(String externalNumber) { this.externalNumber = externalNumber; }
    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}
