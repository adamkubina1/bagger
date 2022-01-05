package cz.vse.bagger.dao;

import cz.vse.bagger.models.LoginCredentials;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Issue
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class LoginCredentialsDao {

    /**
     *  Tato metoda vyhledá v databázi Login_Credentials které mají konkrétní Id
     *  @param idLoginCredentials Id říká jaký Login_credentials chceme vrátit
     */
    public static LoginCredentials searchLoginCredentials (int idLoginCredentials) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT Login_Credentials.Id_Login_Credentials, Login_Credentials.Login_Name, Login_Credentials.Password from Login_Credentials inner join Employee on Login_Credentials.Id_Login_Credentials = Employee.Id_Login_Credentials where Employee.Id_Employee = "+idLoginCredentials;

        try {
            ResultSet resultLoginCredentials = DBUtil.dbExecuteQuery(SELECT_STMT);
            LoginCredentials login_credentials = getLoginCredentialsFromResultSet(resultLoginCredentials);

            return login_credentials;
        } catch (SQLException exception) {
            System.out.println("While searching an Login_Credentials with " + idLoginCredentials + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda vyhledá v databázi Login_Credentials kterým odpovídá daný username a heslo
     *  @param username jméno podle kterého vracíme záznam z databáze
     *  @param password heslo které musí souhlasit v databázi aby to vrátilo záznam
     */
    public static LoginCredentials login(String username, String password) throws SQLException, ClassNotFoundException {
        final String VALIDATE = "SELECT Login_Credentials.Id_Login_Credentials, Login_Credentials.Login_Name, Login_Credentials.Password from Login_Credentials WHERE Login_Credentials.Login_Name = \"" + username + "\" AND Login_Credentials.Password = \"" + password + "\"";

        try {
            ResultSet resultLoginCredentials = DBUtil.dbExecuteQuery(VALIDATE);
            LoginCredentials login_credentials = getLoginCredentialsFromResultSet(resultLoginCredentials);
            return login_credentials;
        } catch (SQLException exception) {
            System.out.println("While searching an Login_Credentials with an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze login metody a záznamy které ta metoda vrátila dává do proměnné typu Login_Credentials.
     *  @param resultLoginCredentials je výsledek který vrátila databáze na naše querry.
     */
    private static LoginCredentials getLoginCredentialsFromResultSet(ResultSet resultLoginCredentials) throws SQLException
    {
        LoginCredentials login_credentials = null;
        if (resultLoginCredentials.next()) {
            login_credentials = new LoginCredentials();
            login_credentials.setId_Login_Credentials(resultLoginCredentials.getInt("Id_Login_Credentials"));
            login_credentials.setLogin_Name(resultLoginCredentials.getString("Login_Name"));
            login_credentials.setPassword(resultLoginCredentials.getString("Password"));

        }
        return login_credentials;
    }
    /**
     *  Tato metoda updatuje existující záznam o hesle v databázi
     *  @param idLoginCredentials Id podle kterého najdeme záznam který chceme updatovat
     *  @param password nové heslo kterým updatujeme ten starý záznam
     */
 public static void updateLoginCredentialsPassword (int idLoginCredentials, String password) throws SQLException, ClassNotFoundException {
     final String UPDATE_STMT =
     "   UPDATE Login_Credentials\n" +
     "      SET Password = '" + password + "'\n" +
     "    WHERE Id_Login_Credentials = " + idLoginCredentials + ";";
    try {
        DBUtil.dbExecuteUpdate(UPDATE_STMT);
    }
    catch (SQLException exception) {
        System.out.print("Error occurred while UPDATE Operation: " + exception);
        throw exception;
        }
    }
}
