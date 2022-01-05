package cz.vse.bagger.dao;

import cz.vse.bagger.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Employee
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class EmployeeDao {


    /**
     *  Tato metoda vyhledá v databázi uživatele s konkrétním Id
     *  @param empId Id zaměstnance říká kterého zaměstnance chceme vyhledat
     */
    public static Employee searchEmployee (int empId) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Employee WHERE Id_Employee="+empId;

        try {
            ResultSet resultEmployee = DBUtil.dbExecuteQuery(SELECT_STMT);
            Employee employee = getEmployeeFromResultSet(resultEmployee);
            return employee;
        } catch (SQLException exception) {
            System.out.println("While searching an employee with " + empId + " id, an error occurred: " + exception);
            throw exception;
        }
    }

    /**
     *  Tato metoda zpracovává výsledek ze searchEmployee metody a záznamy které ta metoda vrátila dává do proměnné typu Employee.
     *  @param resultEmployee je výsledek který vrátila databáze na naše querry.
     */
    private static Employee getEmployeeFromResultSet(ResultSet resultEmployee) throws SQLException
    {
        Employee employee = null;
        if (resultEmployee.next()) {
            employee = new Employee();
            employee.setIdEmployee(resultEmployee.getInt("Id_Employee"));
            employee.setIdTeam(resultEmployee.getInt("Id_Team"));
            employee.setIdLoginCredentials(resultEmployee.getInt("Id_Login_Credentials"));
            employee.setName(resultEmployee.getString("Name"));
            employee.setSurname(resultEmployee.getString("Surname"));
            employee.setPosition(resultEmployee.getString("Position"));
        }
        return employee;
    }
    
    /**
     *  Tato metoda vyhledá v databázi všechny uživatele s konkrétním tým Id
     *  @param idTeam Id týmu podle kterého si vypíšeme všechny zaměstnance
     */
    public static ObservableList<Employee> searchEmployeesByTeamId (int idTeam) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Employee where Id_Team = "+idTeam;

        try {
            ResultSet resultEmployees = DBUtil.dbExecuteQuery(SELECT_STMT);
            ObservableList<Employee> employeeList = getEmployeeListByTeamId(resultEmployees);
            return employeeList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    /**
     *  Tato metoda zpracovává výsledek ze searchEmployeesByTeamId metody a záznamy které ta metoda vrátila dává do Listu.
     *  @param resultEmployees je výsledek který vrátila databáze na naše querry.
     */
    private static ObservableList<Employee> getEmployeeListByTeamId(ResultSet resultEmployees) throws SQLException {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        while (resultEmployees.next()) {
            Employee employee = new Employee();
            employee.setIdEmployee(resultEmployees.getInt("Id_Employee"));
            employee.setIdTeam(resultEmployees.getInt("Id_Team"));
            employee.setIdLoginCredentials(resultEmployees.getInt("Id_Login_Credentials"));
            employee.setName(resultEmployees.getString("Name"));
            employee.setSurname(resultEmployees.getString("Surname"));
            employee.setPosition(resultEmployees.getString("Position"));
            employeeList.add(employee);
        }
        return employeeList;
    }

}
