package com.taybooking.guest.dto;

public class GuestDTO {

    private String name;

    public GuestDTO() {
    }

    public GuestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
