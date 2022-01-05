package cz.vse.bagger.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class LoginCredentials {
    private IntegerProperty idLoginCredentials;
    private StringProperty loginName;
    private StringProperty password;

    public LoginCredentials() {
        this.idLoginCredentials = new SimpleIntegerProperty();
        this.loginName = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    public int getIdLoginCredentials() {
        return idLoginCredentials.get();
    }

    public IntegerProperty idLoginCredentialsProperty() {
        return idLoginCredentials;
    }

    public void setIdLoginCredentials(int idLoginCredentials) {
        this.idLoginCredentials.set(idLoginCredentials);
    }

    public String getLoginName() {
        return loginName.get();
    }

    public StringProperty loginNameProperty() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName.set(loginName);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
