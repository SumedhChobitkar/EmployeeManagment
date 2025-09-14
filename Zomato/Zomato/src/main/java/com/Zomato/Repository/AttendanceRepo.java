package com.Zomato.Repository;

import com.Zomato.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository <AttendanceEntity, Long> {
}
