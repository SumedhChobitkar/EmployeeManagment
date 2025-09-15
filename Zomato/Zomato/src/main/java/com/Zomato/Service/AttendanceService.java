package com.Zomato.Service;

import com.Zomato.Entity.AttendanceEntity;
import java.util.List;

public interface AttendanceService {
    AttendanceEntity saveAttendance(AttendanceEntity attendance);
    List<AttendanceEntity> getAllAttendances();
    AttendanceEntity getAttendanceById(Long id);
    AttendanceEntity updateAttendance(Long id, AttendanceEntity attendanceDetails);
    void deleteAttendance(Long id);
}
