package com.mindthecode.CompanyDirectory.strategy;

import com.mindthecode.CompanyDirectory.models.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByUnitStrategy implements SearchEmployeeStrategy {
    @Override
    public List<Employee> execute(Iterable<Employee> allEmployees, Long criteriaId) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getPosition().getUnit().getId() == criteriaId) {
                employees.add(employee);
            }
        }
        return employees;
    }
}
