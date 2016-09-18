package am.aca.phonebook.repository.implementation;

import am.aca.phonebook.common.bean.User;
import am.aca.phonebook.common.database.DataBaseCon;
import am.aca.phonebook.repository.interfaces.UserInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static am.aca.phonebook.common.util.Util.getMessage;

/**
 * Created by Roman on 9/16/2016.
 */
public class UserRepository implements UserInterface {
    boolean isChecked = false;
    private static UserInterface userInstance;

    public static UserInterface getInstance() {
        if (userInstance == null)
            userInstance = new UserRepository();
        return userInstance;
    }

    private UserRepository() {

    }

    @Override
    public User addUser(User user) throws  SQLException {
        String sql = "insert into user " + "(first_name,last_name,email)" +
                "values ('" + user.getFirstName() + "'," +
                "'" + user.getLastName() + "'," +
                "'" + user.getEmail() + "')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);) {
            myStmt.executeUpdate(sql);
        }
        return user;
    }

    @Override
    public User getUser(User user) throws  SQLException {
        String sql = "select * from user ";
        User newUser = null;
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);) {
            ResultSet myResult = myStmt.executeQuery();
            while (myResult.next()) {
                if (myResult.getString("first_name").equals(user.getFirstName()) &&
                        myResult.getString("last_name").equals(user.getLastName()) &&
                        myResult.getString("email").equals(user.getEmail())) {
                    newUser = new User(myResult.getInt("id"),
                            myResult.getString("first_name"),
                            myResult.getString("last_name"),
                            myResult.getString("email"));
                    isChecked = true;
                    break;
                }
            }
            if (isChecked)
                return newUser;
            else
                return null;
        }
    }

    @Override
    public void deleteUser(Integer id) throws SQLException {
        String sql = "delete from user where id=('"+id+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            int row = myStmt.executeUpdate(sql);
            if (row > 0)
                getMessage("user.delete");
            else
                getMessage("incorrect.command");
            myStmt.close();
        }
    }

    @Override
    public User editUser(User user1, User user2) throws SQLException {
        User oldUser = user1;
        String sql = "update user " +
                "set first_name=('"+user2.getFirstName()+"'),"+
                "last_name=('"+user2.getLastName()+"'),"+
                "email=('"+user2.getEmail()+"')"+
                "where id=('"+user1.getId()+"')";
        String sql1 = "update friends " +
                "set name=('"+user2.getFirstName()+"')"+
                "where name=('"+oldUser.getFirstName()+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            int rowAffect = myStmt.executeUpdate(sql);
            if (rowAffect>0)
            {
                try (Connection connection1 = DataBaseCon.getInstance().getDataBaseConnect();
                     PreparedStatement myStmt1 = connection1.prepareStatement(sql1);) {
                    myStmt1.executeUpdate(sql1);
                }
                return new User(user1.getId(), user2.getFirstName(),
                        user2.getLastName(), user2.getEmail());
            }
            else
                return null;
        }
    }

    @Override
    public User addFriend(User user, String friendName) throws SQLException {
        String sql = "insert into friends " + "(name,user_id)"+
                "values ('"+friendName+"'," +
                "'"+user.getId()+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            myStmt.executeUpdate(sql);
        }
        return user;
    }

    @Override
    public User getUser(String name) throws SQLException {
        String sql = "select * from user ";
        User user = null;
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            ResultSet myResult = myStmt.executeQuery();
            while (myResult.next())
            {
                if (myResult.getString("first_name").equals(name))
                {
                    user = new User(myResult.getInt("id"),
                            myResult.getString("first_name"),
                            myResult.getString("last_name"),
                            myResult.getString("email"));
                    isChecked=true;
                    break;
                }
            }
        }
        if (isChecked)
            return user;
        else
            return null;
    }

    @Override
    public List<String> getFriendNames(Integer id) throws SQLException {
        List<String> friends = new ArrayList<>();
        String sql="select * from friends ";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            ResultSet myResult = myStmt.executeQuery();
            while (myResult.next()){
                if(myResult.getInt("user_id") == id)
                {
                    friends.add(myResult.getString("name"));
                    isChecked = true;
                }
            }
        }
        if (isChecked)
            return friends;
        return null;
    }

    @Override
    public void deleteFriend(String friendName) throws SQLException {
        String sql = "delete from friends where name=('"+friendName+"')";
        try (Connection connection = DataBaseCon.getInstance().getDataBaseConnect();
             PreparedStatement myStmt = connection.prepareStatement(sql);)
        {
            int row = myStmt.executeUpdate(sql);
            if (row>0)
                getMessage("friend.delete");
            else
                return;
        }
    }
}
