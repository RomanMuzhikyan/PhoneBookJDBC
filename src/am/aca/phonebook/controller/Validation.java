package am.aca.phonebook.controller;

import am.aca.phonebook.common.bean.TelNumber;
import am.aca.phonebook.common.bean.User;
import am.aca.phonebook.common.exception.InvalidCommandException;
import java.sql.SQLException;
import static am.aca.phonebook.common.util.Util.getMessage;

/**
 * Created by Roman on 9/18/2016.
 */
public class Validation {
    public  void inputValidation(String input) throws InvalidCommandException,SQLException {

        while(input!="e")
        {
            switch (input)
            {
                case "1":
                    Controller.getInstance().signUp();
                    Controller.getInstance().begin();
                    break;
                case "2":
                    User user = Controller.getInstance().signIn();
                    if(user == null)
                        Controller.getInstance().begin();
                    else
                        Controller.getInstance().beginNumPoint(user);
                    break;
                case "h":
                    getMessage("help.text");
                    inputValidation(input);
                    break;
                case "e":
                    Controller.getInstance().exit();
                    break;
                default:
                    throw new InvalidCommandException("invalid.command");
            }
        }
    }

    public void inputValidation(String input, User user) throws InvalidCommandException,SQLException {
        while(input!="e")
        {
            switch (input)
            {
                case "1":
                    Controller.getInstance().addNum(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "2":
                    Controller.getInstance().showNumbers(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "3":
                    Controller.getInstance().createFriend(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "4":
                    Controller.getInstance().showFriendNames(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "5":
                    Controller.getInstance().deleteFriend(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "6":
                    Controller.getInstance().deleteNumber(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "7":
                    Controller.getInstance().updateTelNumber(user);
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "8":
                    Controller.getInstance().deleteUserAccaunt(user);
                    Controller.getInstance().begin();
                    break;
                case "9":
                    Controller.getInstance().updateUser(user);
                    Controller.getInstance().beginNumPoint(user);
                case "10":
                    TelNumber telNumber = Controller.getInstance().
                            updateTelNumber(user);
                    if (telNumber==null)
                        getMessage("no.number.list");
                    else
                    {
                        getMessage("number.edited");
                        Controller.getInstance().beginNumPoint(user);
                    }
                case "h":
                    getMessage("help.text");
                    Controller.getInstance().beginNumPoint(user);
                    break;
                case "11":
                    Controller.getInstance().signOut();
                    break;
                case "e":
                    Controller.getInstance().exit();
                    break;
                default:
                    throw new InvalidCommandException("invalid.command");
            }
        }
    }
}
