package com.xprem.support.model;


import com.xprem.support.enums.MemberStatus;
import com.xprem.support.enums.MemberType;

import java.time.Instant;

public class MemberModel extends BaseModel {

    private String name;
    private String lastName;
    private String mail;
    private String password;
    private MemberType memberType;
    private MemberStatus memberStatus;
    private Instant memberDate;
    private Instant premiumMemberStartDate;
    private Instant premiumMemberEndDate;

    public MemberModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Instant getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(Instant memberDate) {
        this.memberDate = memberDate;
    }

    public Instant getPremiumMemberStartDate() {
        return premiumMemberStartDate;
    }

    public void setPremiumMemberStartDate(Instant premiumMemberStartDate) {
        this.premiumMemberStartDate = premiumMemberStartDate;
    }

    public Instant getPremiumMemberEndDate() {
        return premiumMemberEndDate;
    }

    public void setPremiumMemberEndDate(Instant premiumMemberEndDate) {
        this.premiumMemberEndDate = premiumMemberEndDate;
    }
}
