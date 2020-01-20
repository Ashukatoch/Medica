package com.example.razorpayintegration.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Hospital implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("govt")
    @Expose
    private String govt;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("states")
    @Expose
    private String states;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("doctors")
    @Expose
    private String doctors;
    @SerializedName("phones")
    @Expose
    private String phones;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("hmisEnable")
    @Expose
    private String hmisEnable;
    @SerializedName("avgOpd")
    @Expose
    private String avgOpd;
    @SerializedName("numOfDoc")
    @Expose
    private String numOfDoc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGovt() {
        return govt;
    }

    public void setGovt(String govt) {
        this.govt = govt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctors() {
        return doctors;
    }

    public void setDoctors(String doctors) {
        this.doctors = doctors;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHmisEnable() {
        return hmisEnable;
    }

    public void setHmisEnable(String hmisEnable) {
        this.hmisEnable = hmisEnable;
    }

    public String getAvgOpd() {
        return avgOpd;
    }

    public void setAvgOpd(String avgOpd) {
        this.avgOpd = avgOpd;
    }

    public String getNumOfDoc() {
        return numOfDoc;
    }

    public void setNumOfDoc(String numOfDoc) {
        this.numOfDoc = numOfDoc;
    }

    public static Comparator<Hospital> getAsc() {
        return asc;
    }

    public static Comparator<Hospital> getDesc() {
        return desc;
    }

    public static final Comparator<Hospital> asc = new Comparator<Hospital>() {
        @Override
        public int compare(Hospital hospital, Hospital t1) {
            return hospital.getName().compareTo(t1.getName());
        }
    };

    public static final Comparator<Hospital> desc = new Comparator<Hospital>() {
        @Override
        public int compare(Hospital hospital, Hospital t1) {
            return t1.getName().compareTo(hospital.getName());
        }
    };
}
