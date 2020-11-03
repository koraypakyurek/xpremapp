package com.xprem.support.model;

import java.util.UUID;

public class TicketModel extends BaseModel {

    private String topic;
    private String message;
    private Long memberId;

    public TicketModel() {
    }

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
