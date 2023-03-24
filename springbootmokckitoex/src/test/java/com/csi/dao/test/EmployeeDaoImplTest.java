package com.csi.dao.test;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @MockBean
    EmployeeRepository employeeRepositoryImpl;

    @Test
    public void saveDataTest(){

        Employee employee = new Employee(121,"Sumit","Pune",20000,98989898,new Date(),"sumit@gmail.com");

        employeeDaoImpl.saveData(employee);

        verify(employeeRepositoryImpl, times(1)).save(employee);

    }

    @Test
    public void getAllDataTest(){

    when(employeeRepositoryImpl.findAll()).thenReturn(Stream.of(new Employee(121,"Ram","Pune",65000,98989898,new Date(),"ram@gamil.com"),
            new Employee(122,"Amit","Pune",65000,98989898,new Date(),"amit@gamil.com"),
            new Employee(123,"om","Pune",65000,98989898,new Date(),"om@gamil.com"),
            new Employee(124,"piu","Pune",65000,98989898,new Date(),"piu@gamil.com"),
            new Employee(125,"mona","Pune",65000,98989898,new Date(),"mona@gamil.com")).collect(Collectors.toList()));

        Assert.assertEquals(5,employeeDaoImpl.getAllData().size());
    }

    @Test
    public void updateDataTest(){

        Employee employee = new Employee(121,"Sumit","Pune",20000,98989898,new Date(),"sumit@gmail.com");

        employeeDaoImpl.updateData(employee);

        verify(employeeRepositoryImpl, times(1)).save(employee);


    }

    @Test
    public void deleteByIdTest(){

        Employee employee = new Employee(121,"Sumit","Pune",20000,98989898,new Date(),"sumit@gmail.com");

        employeeDaoImpl.deleteById(employee.getEmpId());

        verify(employeeRepositoryImpl, times(1)).deleteById(employee.getEmpId());


    }
}
