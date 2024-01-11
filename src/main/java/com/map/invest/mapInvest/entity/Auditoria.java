package com.map.invest.mapInvest.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Auditoria {
    @CreatedBy
    private String user;
    @CreatedDate
    private LocalDateTime createdDate;
}
