package am.aca.phonebook.common.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Roman on 9/16/2016.
 */
public class DataBaseCon {
    private static final String URL = "jdbc:mysql://localhost:33060/phonebook?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "roman";
    private static DataBaseCon databaseInstance;
    public static DataBaseCon getInstance() {
        if (databaseInstance==null)
            return new DataBaseCon();
        return databaseInstance;
    }
    private DataBaseCon()
    {

    }
    public Connection getDataBaseConnect(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(URL,USER,PASSWORD);
            return connect;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
