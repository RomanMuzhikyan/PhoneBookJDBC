package am.aca.phonebook.repository.interfaces;

import am.aca.phonebook.common.bean.PhoneType;
import am.aca.phonebook.common.bean.TelNumber;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 9/17/2016.
 */
public interface TelInterface {
    TelNumber addTelNumber(TelNumber telNumber, Integer id) throws SQLException;
    List<String> getTelNumbers(Integer id) throws SQLException;
    void deleteTelNumberByNumber(String number)throws SQLException;
    TelNumber editNumber(TelNumber telNumber,String oldNumber) throws SQLException;
}
