package cz.vse.bagger.models;

import javafx.beans.property.*;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Employee {
    private IntegerProperty Id_Employee;
    private IntegerProperty Id_Team;
    private IntegerProperty Id_Login_Credentials;
    private StringProperty Name;
    private StringProperty Surname;
    private StringProperty Position;

    public Employee() {
        this.Id_Employee = new SimpleIntegerProperty();
        this.Id_Team = new SimpleIntegerProperty();
        this.Id_Login_Credentials = new SimpleIntegerProperty();
        this.Name = new SimpleStringProperty();
        this.Surname = new SimpleStringProperty();
        this.Position = new SimpleStringProperty();
    }

    public int getId_Employee() {
        return Id_Employee.get();
    }

    public void setId_Employee(int Id_Employee) {
        this.Id_Employee.set(Id_Employee);
    }

    public IntegerProperty id_EmployeeProperty() {
        return Id_Employee;
    }
    
    public int getId_Team() {
        return Id_Team.get();
    }

    public void setId_Team(int Id_Team) {
        this.Id_Team.set(Id_Team);
    }

    public IntegerProperty id_TeamProperty() {
        return Id_Team;
    }

    public int getId_Login_Credentials() {
        return Id_Login_Credentials.get();
    }

    public void setId_Login_Credentials(int Id_Login_Credentials) {
        this.Id_Login_Credentials.set(Id_Login_Credentials);
    }

    public IntegerProperty id_Login_CredentialsProperty() {
        return Id_Login_Credentials;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getSurname() {
        return Surname.get();
    }

    public StringProperty surnameProperty() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname.set(surname);
    }

    public String getPosition() {
        return Position.get();
    }

    public StringProperty positionProperty() {
        return Position;
    }

    public void setPosition(String position) {
        this.Position.set(position);
    }
}
