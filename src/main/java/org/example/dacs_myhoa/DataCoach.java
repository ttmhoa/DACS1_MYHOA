package org.example.dacs_myhoa;

public class DataCoach {
    private Integer id;
    private String coachid;
    private String name;
    private String address;
    private String gender;
    private Integer phone;
    private String status;

    public DataCoach(Integer id, String coachid, String name, String address, String gender, Integer phone, String status) {
        this.id = id;
        this.coachid = coachid;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getCoachid() {
        return coachid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }


}
