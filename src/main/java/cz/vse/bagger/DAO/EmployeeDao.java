package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    public static Employee searchEmployee (int empId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Employee WHERE Id_Employee="+empId;

        try {
            ResultSet resultEmployee = DBUtil.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(resultEmployee);
            return employee;
        } catch (SQLException exception) {
            System.out.println("While searching an employee with " + empId + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    private static Employee getEmployeeFromResultSet(ResultSet resultEmployee) throws SQLException
    {
        Employee employee = null;
        if (resultEmployee.next()) {
            employee = new Employee();
            employee.setId_Employee(resultEmployee.getInt("Id_Employee"));
            employee.setId_Team(resultEmployee.getInt("Id_Team"));
            employee.setId_Login_Credentials(resultEmployee.getInt("Id_Login_Credentials"));
            employee.setName(resultEmployee.getString("Name"));
            employee.setSurname(resultEmployee.getString("Surname"));
            employee.setPosition(resultEmployee.getString("Position"));
        }
        return employee;
    }

    public static ObservableList<Employee> searchEmployees () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Employee";

        try {
            ResultSet resultEmployees = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Employee> employeeList = getEmployeeList(resultEmployees);
            return employeeList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    private static ObservableList<Employee> getEmployeeList(ResultSet resultEmployees) throws SQLException, ClassNotFoundException {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        while (resultEmployees.next()) {
            Employee employee = new Employee();
            employee.setId_Employee(resultEmployees.getInt("Id_Employee"));
            employee.setId_Team(resultEmployees.getInt("Id_Team"));
            employee.setId_Login_Credentials(resultEmployees.getInt("Id_Login_Credentials"));
            employee.setName(resultEmployees.getString("Name"));
            employee.setSurname(resultEmployees.getString("Surname"));
            employee.setPosition(resultEmployees.getString("Position"));
            employeeList.add(employee);
        }
        return employeeList;
    }

    public static ObservableList<Employee> searchEmployeesByTeamId (int Id_Team) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Employee where Id_Team = "+Id_Team;

        try {
            ResultSet resultEmployees = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Employee> employeeList = getEmployeeListByTeamId(resultEmployees);
            return employeeList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    private static ObservableList<Employee> getEmployeeListByTeamId(ResultSet resultEmployees) throws SQLException, ClassNotFoundException {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        while (resultEmployees.next()) {
            Employee employee = new Employee();
            employee.setId_Employee(resultEmployees.getInt("Id_Employee"));
            employee.setId_Team(resultEmployees.getInt("Id_Team"));
            employee.setId_Login_Credentials(resultEmployees.getInt("Id_Login_Credentials"));
            employee.setName(resultEmployees.getString("Name"));
            employee.setSurname(resultEmployees.getString("Surname"));
            employee.setPosition(resultEmployees.getString("Position"));
            employeeList.add(employee);
        }
        return employeeList;
    }

}
