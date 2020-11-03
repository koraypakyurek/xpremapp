package com.xprem.support.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TICKET")
public class TicketEntity extends BaseEntity {

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
