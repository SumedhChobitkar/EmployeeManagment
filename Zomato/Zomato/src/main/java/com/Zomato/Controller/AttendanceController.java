package com.Zomato.Controller;

import com.Zomato.Entity.AttendanceEntity;
import com.Zomato.Exceptions.EmployeeNotFoundException;
import com.Zomato.Exceptions.InvalideDataException;
import com.Zomato.Service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAttendance(@RequestBody AttendanceEntity attendance) {
        try {
            AttendanceEntity savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.ok(savedAttendance);
        } catch (InvalideDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        try {
            AttendanceEntity attendance = attendanceService.getAttendanceById(id);
            return ResponseEntity.ok(attendance);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttendances() {
        try {
            List<AttendanceEntity> attendances = attendanceService.getAllAttendances();
            return ResponseEntity.ok(attendances);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody AttendanceEntity attendanceDetails) {
        try {
            AttendanceEntity updatedAttendance = attendanceService.updateAttendance(id, attendanceDetails);
            return ResponseEntity.ok(updatedAttendance);
        } catch (InvalideDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        try {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.ok("Attendance deleted successfully with ID: " + id);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
