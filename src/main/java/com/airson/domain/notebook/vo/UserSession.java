package com.airson.domain.notebook.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserSession {

    private Long   uid;
    private Date   logonTime;
    private String token;
    private String wsSessionId;

    public UserSession(Long uid) {
        this.uid = uid;
        this.logonTime = new Date();
    }

}
