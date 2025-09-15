package com.Zomato.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    public class AttendanceEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long employeeId;
        private String employeeName;
        private LocalDateTime loginTime;
        private LocalDateTime logoutTime;

        @Enumerated(EnumType.STRING)
        private AttendanceStatus status;
    }


