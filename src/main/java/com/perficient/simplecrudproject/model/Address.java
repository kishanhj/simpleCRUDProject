package com.perficient.simplecrudproject.model;

public class Address {
    private String street_name;
    private String street_address;
    private String city;
    private String state;
    private String country;
    private String postcode;

    public Address(String street_name, String street_address, String city, String state, String country, String postcode) {
        this.street_name = street_name;
        this.street_address = street_address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street_name='" + street_name + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", post_code='" + postcode + '\'' +
                '}';
    }
}
