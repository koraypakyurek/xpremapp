package com.xprem.support.entity;


import com.xprem.support.enums.MemberStatus;
import com.xprem.support.enums.MemberType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "MEMBER")
public class MemberEntity extends BaseEntity {

    @Column(name = "MEMBER_NAME")
    private String name;

    @Column(name = "MEMBER_LASTNAME")
    private String lastName;

    @Column(name = "MEMBER_MAIL")
    private String mail;

    @Column(name = "MEMBER_PASSWORD")
    private String password;

    @Column(name = "MEMBER_TYPE")
    private MemberType memberType;

    @Column(name = "MEMBER_STATUS")
    private MemberStatus memberStatus;

    @Column(name = "MEMBER_DATE")
    private Instant memberDate;

    @Column(name = "MEMBER_PREMIUM_START_DATE")
    private Instant premiumMemberStartDate;

    @Column(name = "MEMBER_PREMIUM_END_DATE")
    private Instant premiumMemberEndDate;

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
