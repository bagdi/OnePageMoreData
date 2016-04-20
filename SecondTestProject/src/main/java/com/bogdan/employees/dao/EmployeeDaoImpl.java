package com.bogdan.employees.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import com.bogdan.employees.model.Employee;
import com.bogdan.employees.util.HibernateUtil;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bogdan on 17.04.2016.
 */
public class EmployeeDaoImpl implements EmployeeDao<Employee> {

    @Override
    public void add(Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public Employee get(int id) throws SQLException {
        Employee employee = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employee = session.get(Employee.class, id);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return employee;
    }

    @Override
    public List<Employee> list() throws SQLException {
        List<Employee> employeeList = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employeeList = session.createCriteria(Employee.class).list();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return employeeList;
    }

    @Override
    public List<Employee> listByName(String firstName, String lastName) {
        List<Employee> employeeList = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT * FROM employee WHERE firstName LIKE :personFirstName AND " +
                    "lastName LIKE :personLastName");
            query.setParameter("personFirstName", firstName + "%");
            query.setParameter("personLastName", lastName + "%");
            employeeList = (List<Employee>) query.list();
            if(employeeList.isEmpty()) {
                employeeList = (List<Employee>) session.createSQLQuery("SELECT * FROM employee WHERE firstName LIKE :personName")
                        .setParameter("personName", firstName + "%").list();
            }
            if(employeeList.isEmpty()) {
                employeeList = (List<Employee>) session.createSQLQuery("SELECT * FROM employee WHERE lastName LIKE :personName")
                        .setParameter("personName", lastName + "%").list();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return employeeList;
    }
}
