package cz.vse.bagger.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Login_Credentials {
    private IntegerProperty Id_Login_Credentials;
    private StringProperty Login_Name;
    private StringProperty Password;

    public Login_Credentials() {
        this.Id_Login_Credentials = new SimpleIntegerProperty();
        this.Login_Name = new SimpleStringProperty();
        this.Password = new SimpleStringProperty();
    }

    public int getId_Login_Credentials() {
        return Id_Login_Credentials.get();
    }

    public IntegerProperty id_Login_CredentialsProperty() {
        return Id_Login_Credentials;
    }

    public void setId_Login_Credentials(int id_Login_Credentials) {
        this.Id_Login_Credentials.set(id_Login_Credentials);
    }

    public String getLogin_Name() {
        return Login_Name.get();
    }

    public StringProperty login_NameProperty() {
        return Login_Name;
    }

    public void setLogin_Name(String login_Name) {
        this.Login_Name.set(login_Name);
    }

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }
}
