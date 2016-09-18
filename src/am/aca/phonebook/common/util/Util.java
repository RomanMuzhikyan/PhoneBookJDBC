package am.aca.phonebook.common.util;

import am.aca.phonebook.common.database.DataBaseCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by seriozhik on 9/16/2016.
 */
public class Util {
    //for my Exception
    public static String getMessageValue(String key) throws SQLException {
        String sql = "select * from messages where message='"+key+"'";
        try(Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
            PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            ResultSet myResult = myStmt.executeQuery();
            while (myResult.next())
            {
                return myResult.getString("discription");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    //for messages
    public static void getMessage(String key) throws SQLException
    {
        System.out.println(getMessageValue(key));
    }
}
