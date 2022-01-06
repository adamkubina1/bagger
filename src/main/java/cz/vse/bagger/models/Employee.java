package cz.vse.bagger.models;

import javafx.beans.property.*;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Employee {
    private IntegerProperty idEmployee;
    private IntegerProperty idTeam;
    private IntegerProperty idLoginCredentials;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty position;

    public Employee() {
        this.idEmployee = new SimpleIntegerProperty();
        this.idTeam = new SimpleIntegerProperty();
        this.idLoginCredentials = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.position = new SimpleStringProperty();
    }

    public int getIdEmployee() {
        return idEmployee.get();
    }

    public void setIdEmployee(int Id_Employee) {
        this.idEmployee.set(Id_Employee);
    }

    public IntegerProperty idEmployeeProperty() {
        return idEmployee;
    }
    
    public int getIdTeam() {
        return idTeam.get();
    }

    public void setIdTeam(int Id_Team) {
        this.idTeam.set(Id_Team);
    }

    public IntegerProperty idTeamProperty() {
        return idTeam;
    }

    public int getIdLoginCredentials() {
        return idLoginCredentials.get();
    }

    public void setIdLoginCredentials(int Id_Login_Credentials) {
        this.idLoginCredentials.set(Id_Login_Credentials);
    }

    public IntegerProperty idLoginCredentialsProperty() {
        return idLoginCredentials;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }
}
