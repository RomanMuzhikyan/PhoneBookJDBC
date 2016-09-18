package am.aca.phonebook;
import am.aca.phonebook.common.exception.InvalidCommandException;
import am.aca.phonebook.controller.Controller;
import java.sql.SQLException;
/**
 * Created by Roman on 9/16/2016.
 */
public class Main {
    public static void main(String[] args) throws SQLException, InvalidCommandException
    {
        Controller.getInstance().begin();
    }
}
