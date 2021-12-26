package cz.vse.bagger.Start;

import cz.vse.bagger.DAO.DBUtil;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
    }
}



