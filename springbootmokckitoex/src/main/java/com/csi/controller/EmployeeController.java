package com.csi.controller;

import com.csi.constant.EndPointConstant;
import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    @PostMapping(EndPointConstant.SAVE_DATA)
    public ResponseEntity<Employee> saveData(@Valid  @RequestBody Employee employee){

        return new ResponseEntity<>(employeeServiceImpl.saveData(employee), HttpStatus.CREATED);
    }

    @GetMapping(EndPointConstant.GET_ALL_DATA)
    public ResponseEntity<List<Employee>> getAllData(){

        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping(EndPointConstant.UPDATE_DATA)
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee){

        Employee employee1 = employeeRepositoryImpl.findById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Does not Exist!!!!"));

        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpName(employee1.getEmpName());

        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1), HttpStatus.CREATED);
    }

    @DeleteMapping(EndPointConstant.DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable int empId){

        employeeServiceImpl.deleteById(empId);

        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
