package com.alex.CustomerManagement.dto;

public final class CreateCompanyDto {

    private final String name;
    private final String vatNumber;


    public CreateCompanyDto(String name, String vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public String getName() {
        return name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateCompanyDto that = (CreateCompanyDto) o;

        if (!name.equals(that.name)) return false;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + vatNumber.hashCode();
        return result;
    }
}
