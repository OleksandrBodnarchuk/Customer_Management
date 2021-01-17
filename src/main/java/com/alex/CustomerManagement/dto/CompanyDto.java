package com.alex.CustomerManagement.dto;

import java.util.UUID;

public final class CompanyDto {
    private final UUID id;
    private final String name;
    private final String vatNumber;

    public CompanyDto(UUID id, String name, String vatNumber) {
        this.id = id;
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public UUID getId() {
        return id;
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

        CompanyDto that = (CompanyDto) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + vatNumber.hashCode();
        return result;
    }
}
