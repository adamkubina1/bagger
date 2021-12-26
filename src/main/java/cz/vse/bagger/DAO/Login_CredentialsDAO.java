package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Login_Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_CredentialsDAO {
    //select, update
    public static Login_Credentials searchLoginCredentials (String Id_Login_Credentials) throws SQLException, ClassNotFoundException {
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

 public static void updateLoginCredentialsPassword (int Id_Login_Credentials, String Password) throws SQLException, ClassNotFoundException {
     String updateStmt =
     "BEGIN\n" +
     "   UPDATE Login_Credentials\n" +
     "      SET Password = '" + Password + "'\n" +
     "    WHERE Id_Login_Credentials = " + Id_Login_Credentials + ";\n" +
     "   COMMIT;\n" +
     "END;";

    try {
        DBUtil.dbExecuteUpdate(updateStmt);
    }
    catch (SQLException exception) {
        System.out.print("Error occurred while UPDATE Operation: " + exception);
        throw exception;
        }
    }
}