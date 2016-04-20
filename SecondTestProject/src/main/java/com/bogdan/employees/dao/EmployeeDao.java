package com.bogdan.employees.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bogdan on 17.04.2016.
 */
public interface EmployeeDao<Employee> {

    public void add(Employee employee) throws SQLException;
    public void delete(Employee employee) throws SQLException;
    public Employee get(int id) throws SQLException;
    public List<Employee> list() throws SQLException;
    public List<Employee> listByName(String firstName, String lastName)  throws SQLException;
}
