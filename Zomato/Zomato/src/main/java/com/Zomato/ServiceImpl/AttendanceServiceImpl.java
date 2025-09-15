package com.Zomato.ServiceImpl;

import com.Zomato.Entity.AttendanceEntity;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Repository.AttendanceRepo;
import com.Zomato.Service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private static final Logger l = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    @Autowired
    private AttendanceRepo attendanceRepository;

    @Override
    public AttendanceEntity saveAttendance(AttendanceEntity attendance) {
        if (attendance.getEmployeeId() == null) {
            throw new InvalideDataException("Employee ID is required");
        }
        if (attendance.getEmployeeName() == null || attendance.getEmployeeName().isBlank()) {
            throw new InvalideDataException("Employee name is required");
        }
        if (attendance.getStatus() == null) {
            throw new InvalideDataException("Add status");
        }
        l.info("Saving attendance for employee: {}", attendance.getEmployeeName());
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<AttendanceEntity> getAllAttendances() {
        List<AttendanceEntity> attendances = attendanceRepository.findAll();
        if (attendances.isEmpty()) {
            throw new EmployeeNotFoundException("No attendance records found!");
        }
        return attendances;
    }

    @Override
    public AttendanceEntity getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Attendance record with id " + id + " not found"));
    }

    @Override
    public AttendanceEntity updateAttendance(Long id, AttendanceEntity attendanceDetails) {
        AttendanceEntity attendance = getAttendanceById(id);
        attendance.setEmployeeId(attendanceDetails.getEmployeeId());
        attendance.setEmployeeName(attendanceDetails.getEmployeeName());
        attendance.setLoginTime(attendanceDetails.getLoginTime());
        attendance.setLogoutTime(attendanceDetails.getLogoutTime());
        attendance.setStatus(attendanceDetails.getStatus());

        l.info("Updated attendance for employee: {}", attendance.getEmployeeName());
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Attendance record not found with id: " + id);
        }
        l.warn("Deleting attendance record with id {}", id);
        attendanceRepository.deleteById(id);
    }
}
