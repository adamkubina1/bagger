package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Login_Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Issue
 */
public class Login_CredentialsDAO {
    /**
     *  Tato metoda vyhledá v databázi Login_Credentials které mají konkrétní Id
     *  @param Id_Login_Credentials Id říká jaký Login_credentials chceme vrátit
     */
    public static Login_Credentials searchLoginCredentials (int Id_Login_Credentials) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT Login_Credentials.Id_Login_Credentials, Login_Credentials.Login_Name, Login_Credentials.Password from Login_Credentials inner join Employee on Login_Credentials.Id_Login_Credentials = Employee.Id_Login_Credentials where Employee.Id_Employee = "+Id_Login_Credentials;

        try {
            ResultSet resultLoginCredentials = DBUtil.dbExecuteQuery(selectStmt);
            Login_Credentials login_credentials = getLoginCredentialsFromResultSet(resultLoginCredentials);

            return login_credentials;
        } catch (SQLException exception) {
            System.out.println("While searching an Login_Credentials with " + Id_Login_Credentials + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda vyhledá v databázi Login_Credentials kterým odpovídá daný username a heslo
     *  @param username jméno podle kterého vracíme záznam z databáze
     *  @param password heslo které musí souhlasit v databázi aby to vrátilo záznam
     */
    public static Login_Credentials login(String username, String password) throws SQLException, ClassNotFoundException {
        String validate = "SELECT Login_Credentials.Id_Login_Credentials, Login_Credentials.Login_Name, Login_Credentials.Password from Login_Credentials WHERE Login_Credentials.Login_Name = \"" + username + "\" AND Login_Credentials.Password = \"" + password + "\"";
        try {
            ResultSet resultLoginCredentials = DBUtil.dbExecuteQuery(validate);
            Login_Credentials login_credentials = getLoginCredentialsFromResultSet(resultLoginCredentials);
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
    private static Login_Credentials getLoginCredentialsFromResultSet(ResultSet resultLoginCredentials) throws SQLException
    {
        Login_Credentials login_credentials = null;
        if (resultLoginCredentials.next()) {
            login_credentials = new Login_Credentials();
            login_credentials.setId_Login_Credentials(resultLoginCredentials.getInt("Id_Login_Credentials"));
            login_credentials.setLogin_Name(resultLoginCredentials.getString("Login_Name"));
            login_credentials.setPassword(resultLoginCredentials.getString("Password"));

        }
        return login_credentials;
    }
    /**
     *  Tato metoda updatuje existující záznam o hesle v databázi
     *  @param Id_Login_Credentials Id podle kterého najdeme záznam který chceme updatovat
     *  @param Password nové heslo kterým updatujeme ten starý záznam
     */
 public static void updateLoginCredentialsPassword (int Id_Login_Credentials, String Password) throws SQLException, ClassNotFoundException {
     String updateStmt =
     "   UPDATE Login_Credentials\n" +
     "      SET Password = '" + Password + "'\n" +
     "    WHERE Id_Login_Credentials = " + Id_Login_Credentials + ";";
    try {
        DBUtil.dbExecuteUpdate(updateStmt);
    }
    catch (SQLException exception) {
        System.out.print("Error occurred while UPDATE Operation: " + exception);
        throw exception;
        }
    }
}
