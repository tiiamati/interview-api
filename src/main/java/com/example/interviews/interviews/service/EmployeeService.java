package com.example.interviews.interviews.service;

import com.example.interviews.interviews.dao.EmployeeDAO;
import com.example.interviews.interviews.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeService {

    @GetMapping
    public List<Employee> getEmployee() {
        return EmployeeDAO.select();
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return EmployeeDAO.select(Employee.builder().id(id).build());
    }

    @PostMapping
    public String setEmployee(@RequestBody Employee employee) {
        return EmployeeDAO.insert(employee);
    }

    @PostMapping(path = "/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return EmployeeDAO.update(id, employee);
    }
}
