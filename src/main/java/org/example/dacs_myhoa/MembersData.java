package org.example.dacs_myhoa;

import java.util.Date;

public class MembersData {
    private Integer id;
    private String memberid;
    private String name;
    private String address;
    private String phone;
    private String gender;
    private String schedule;
    private String status;
    private Date enddate;
    private Date startdate;
    private Float price;

    public MembersData(Integer id, String memberid, String name, String address,
                       String phone, String gender, String schedule, String status, Date enddate, Date startdate,Float price ){
        this.id = id;
        this.memberid = memberid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.schedule = schedule;
        this.status = status;
        this.enddate = enddate;
        this.startdate = startdate;
        this.price= price;
    }

    public Integer getId() {
        return id;
    }

    public String getMemberid() {
        return memberid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStatus() {
        return status;
    }

    public Date getEnddate() {
        return enddate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Float getPrice() {
        return price;
    }
}

