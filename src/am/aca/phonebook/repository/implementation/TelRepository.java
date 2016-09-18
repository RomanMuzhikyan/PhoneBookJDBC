package am.aca.phonebook.repository.implementation;
import am.aca.phonebook.common.bean.TelNumber;
import am.aca.phonebook.common.database.DataBaseCon;
import am.aca.phonebook.repository.interfaces.TelInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static am.aca.phonebook.common.util.Util.getMessage;

/**
 * Created by Roman on 9/17/2016.
 */
public class TelRepository implements TelInterface{
    private static TelInterface telInstance;

    public static TelInterface getInstance(){
        if(telInstance==null){
            telInstance=new TelRepository();
        }
        return telInstance;
    }

    private TelRepository(){

    }

    @Override
    public TelNumber addTelNumber(TelNumber telNumber, Integer id) throws SQLException {
        String sql = "insert into phonenumbers (phone_type, number, user_id)"+
                "values ('"+telNumber.getPhoneType()+"'," +
                "'"+telNumber.getNumber()+"'," +
                "'"+id+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            myStmt.executeUpdate(sql);
        }
        return telNumber;
    }
    @Override
    public List<String> getTelNumbers(Integer id) throws SQLException {
        List<String> telNumbers = new ArrayList<>();
        String sql="select * from phonenumbers ";
        boolean isChecked =false;
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
           ResultSet myResult = myStmt.executeQuery();
           while (myResult.next())
           {
                if(myResult.getInt("user_id") == id)
                {
                    telNumbers.add(myResult.getString("phone_type")+": "+myResult.getString("number"));
                    isChecked = true;
                }
           }

        }
        if (isChecked)
            return telNumbers;
         return null;
    }

    @Override
    public void deleteTelNumberByNumber(String number) throws SQLException {
        String sql = "delete from phonenumbers where number=('"+number+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            int row = myStmt.executeUpdate(sql);
            if (row>0)
                getMessage("number.deleted");
            else
                return;
        }
    }

    @Override
    public TelNumber editNumber(TelNumber telNumber,String oldNumber) throws SQLException {
        String sql = "update phonenumbers " +
                "set number=('"+telNumber.getNumber()+"'),"+
                "phone_type=('"+telNumber.getPhoneType()+"')"+
                "where number=('"+oldNumber+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            int rowAffect = myStmt.executeUpdate(sql);
            if (rowAffect>0)
                return telNumber;
            else
                return null;
        }
    }
}
