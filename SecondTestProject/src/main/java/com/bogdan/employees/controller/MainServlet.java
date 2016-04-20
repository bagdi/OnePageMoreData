package com.bogdan.employees.controller;

import com.bogdan.employees.dao.EmployeeDao;
import com.bogdan.employees.general.Factory;
import com.bogdan.employees.model.Employee;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bogdan on 17.04.2016.
 */
public class MainServlet extends HttpServlet {

    private Factory factory;
    private EmployeeDao<Employee> employeeDao;
    private List<Employee> employeeList;

    @Override
    public void init() {
        this.factory = Factory.getInstance();
        this.employeeDao = factory.getEmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String loadFlag = req.getParameter("flag");
        String add = req.getParameter("add");
        String delete = req.getParameter("findRow");
        String search = req.getParameter("search");
        if(add != null) {
            add(req);
            list(resp);
        }
        if(delete != null) {
            delete(req);
            list(resp);
        }
        if(search != null) {
            listByName(req, resp);
        }
        if(loadFlag != null) {
            list(resp);
        }
    }

    public List<Employee> list(HttpServletResponse response) {
        try {
            employeeList = employeeDao.list();
            Gson gson = new Gson();
            String result = gson.toJson(employeeList);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void add(HttpServletRequest request) {
        Employee employee;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        employee = new Employee(firstName, lastName, position, department);
        try {
            employeeDao.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(HttpServletRequest request) {
        Employee employee;
        try {
            employee = get(request);
            employeeDao.delete(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee get(HttpServletRequest request) {
        Employee employee = new Employee();
        int id = Integer.parseInt(request.getParameter("findRow"));
        try {
            employee = employeeDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> listByName(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        try {
            employeeList = employeeDao.listByName(firstName, lastName);
            Gson gson = new Gson();
            String result = gson.toJson(employeeList);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
