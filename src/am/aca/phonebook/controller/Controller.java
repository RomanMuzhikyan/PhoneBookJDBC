package am.aca.phonebook.controller;

import am.aca.phonebook.common.bean.PhoneType;
import am.aca.phonebook.common.bean.TelNumber;
import am.aca.phonebook.common.bean.User;
import am.aca.phonebook.common.exception.InvalidCommandException;
import am.aca.phonebook.repository.implementation.TelRepository;
import am.aca.phonebook.repository.implementation.UserRepository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static am.aca.phonebook.common.util.Util.getMessage;
import static am.aca.phonebook.common.util.RegullarExpressions.*;

/**
 * Created by Roman on 9/17/2016.
 */
public class Controller {
    private Scanner in = new Scanner(System.in);
    private int wrongUserCount = 0;
    private static  Controller controllerInstance;
    public static Controller getInstance()
    {
        if (controllerInstance == null)
            controllerInstance = new Controller();
        return controllerInstance;
    }

    public void begin() throws SQLException, InvalidCommandException {
        Validation validate = new Validation();
        getMessage("welcome");
        String input = in.nextLine();
        validate.inputValidation(input);
    }
    public void beginNumPoint(User user) throws SQLException, InvalidCommandException {
        Validation validate = new Validation();
        getMessage("commands.number");
        String input = in.nextLine();
        validate.inputValidation(input,user);
    }
    private User createNewUser() throws SQLException {
        getMessage("provide.firstName");
        String fName = in.nextLine();
        getMessage("provide.lastName");
        String lName = in.nextLine();
        getMessage("provide.email");
        String email = in.nextLine();
        if (isNameValid(fName)&&
                isNameValid(lName)&&
                isEmailValid(email))
            return new User(fName,lName,email);
        else
        {
            getMessage("invalid.validate");
            return createNewUser();
        }
    }

    public void signUp() throws SQLException {
        User user = createNewUser();
        UserRepository.getInstance().addUser(user);
        getMessage("account.created");
    }
    public User signIn() throws SQLException {
        User user = createNewUser();
        User newUser = UserRepository.getInstance().getUser(user);
        if (newUser!= null)
        {
            getMessage("logged.successfully");
            return newUser;
        }
        else
        {
            getMessage("wrong.add");
            wrongUserCount++;
            if (wrongUserCount<3)
                signIn();
            else
            {
                getMessage("bot.message");
                exit();
            }
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        showUserList(user);
        User user2 = createNewUser();
        user = UserRepository.getInstance().editUser(user,user2);
        if (user==null)
            getMessage("edit.incorrect");
        else
            getMessage("edit.created");
    }
    private void showUserList(User user) throws SQLException
    {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.stream().forEach(x-> System.out.println(x.toString()));
    }
    public void deleteUserAccaunt(User user) throws SQLException {
        UserRepository.getInstance().deleteUser(user.getId());
    }

    public void createFriend(User user) throws SQLException{
        getMessage("provide.friend");
        String friendName =in.nextLine();
        if (isNameValid(friendName))
        {
            User newUser = UserRepository.getInstance().getUser(friendName);
            if (newUser==null)
            {
                getMessage("no.friend");
            }
            else
            {
                UserRepository.getInstance().addFriend(user,friendName);
                getMessage("friend.created");
            }
        }
        else
        {
            getMessage("invalid.validate");
            createFriend(user);
        }
    }
    public List<String> showFriendNames(User user) throws SQLException{
        List<String> friends = UserRepository.getInstance().getFriendNames(user.getId());
        if (friends==null)
            getMessage("no.friend.name");
        else
            friends.forEach(x-> System.out.println("Friends: " + x));
        return friends;
    }

    public void deleteFriend(User user) throws SQLException{
        if(showFriendNames(user)==null)
            return;
        else
        {
            getMessage("provide.friend.to.delete");
            String friendDeleteName = in.nextLine();
            if (isNameValid(friendDeleteName))
                UserRepository.getInstance().deleteFriend(friendDeleteName);
            else
            {
                getMessage("invalid.validate");
                deleteFriend(user);
            }
        }
    }
    private TelNumber createTelNumber() throws SQLException
    {
        TelNumber telNumbers = null;
        PhoneType type =null;
        getMessage("provide.phone.number");
        String number = in.nextLine();
        if (numberStarts(number))
        {
            if (numberContains(number))
                type=PhoneType.Home;
            else
                type=PhoneType.Mobile;
            telNumbers = new TelNumber(number,type);
            return telNumbers;
        }
        else
            {
                getMessage("invalid.number.format");
                return createTelNumber();
            }
    }

    public void addNum(User user) throws SQLException {
        TelNumber telNumber  = createTelNumber();
        TelRepository.getInstance().addTelNumber(telNumber,user.getId());
        getMessage("tel.created");
    }

    public List<String> showNumbers(User user) throws SQLException {
        List<String> telNumbers = TelRepository.getInstance().getTelNumbers(user.getId());
        if (telNumbers==null)
            getMessage("no.number.list");
        else
            telNumbers.forEach(x-> System.out.println(x));
        return telNumbers;
    }

    public TelNumber updateTelNumber(User user) throws SQLException{
        if (showNumbers(user) == null)
            return null;
        else
        {
            TelNumber newTelnum = null;
            getMessage("provide.old.telNum");
            String oldNumber = in.nextLine();
            if (numberStarts(oldNumber))
            {
                TelNumber telNumber = createTelNumber();
                newTelnum = TelRepository.getInstance().editNumber(telNumber,oldNumber);
                if (newTelnum!=null)
                {
                    getMessage("number.edited");
                    return newTelnum;
                }
                return null;
            }
            else
            {
                getMessage("invalid.number.format");
                updateTelNumber(user);
            }
        }
        return null;
    }

    public void deleteNumber(User user) throws SQLException{
        if(showNumbers(user)==null)
            return;
        else
        {
            getMessage("provide.number.to.delete");
            String number = in.nextLine();
            if (numberStarts(number))
                TelRepository.getInstance().deleteTelNumberByNumber(number);
            else
            {
                getMessage("invalid.number.format");
                deleteNumber(user);
            }
        }
    }
    public void signOut() throws SQLException, InvalidCommandException {
        begin();
    }
    public void exit() throws SQLException {
        getMessage("exit.mes");
        System.exit(4);
    }

}
