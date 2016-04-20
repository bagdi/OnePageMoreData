package com.bogdan.employees.general;

import com.bogdan.employees.dao.EmployeeDao;
import com.bogdan.employees.dao.EmployeeDaoImpl;
import com.bogdan.employees.model.Employee;

/**
 * Created by Bogdan on 17.04.2016.
 */
public class Factory {

    private static Factory instance = new Factory();
    private static EmployeeDao<Employee> employeeDao = null;

    private Factory() {
    }

    public static Factory getInstance() {
        return Factory.instance;
    }

    public EmployeeDao getEmployeeDao() {
        if(employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        } return employeeDao;
    }
}
