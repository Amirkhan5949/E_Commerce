package com.example.e_commerce_user.model;

public class Address {
    String address;
    String address_type;
    String city;
    String landmark;
    String mob_no;
    String name;
    String pincode;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", address_type='" + address_type + '\'' +
                ", city='" + city + '\'' +
                ", landmark='" + landmark + '\'' +
                ", mob_no='" + mob_no + '\'' +
                ", name='" + name + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String state;

    public Address(String address, String address_type, String city, String landmark, String mob_no, String name, String pincode, String state) {
        this.address = address;
        this.address_type = address_type;
        this.city = city;
        this.landmark = landmark;
        this.mob_no = mob_no;
        this.name = name;
        this.pincode = pincode;
        this.state = state;
    }
}
