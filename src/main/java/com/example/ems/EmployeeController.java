package com.example.ems;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "Shiva", "Developer"));
        list.add(new Employee(2, "Ameer", "Tester"));
        return list;
    }
}
