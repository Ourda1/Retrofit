package com.example.retrofit.model;

import android.location.Address;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Address address;

    public static class Address {
        private String city;

        public String getCity() {
            return city;
        }
    }

    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    // Getter pour la ville qui accède à address.city
    public String getCity() {
        return address != null ? address.getCity() : "N/A";
    }
}
